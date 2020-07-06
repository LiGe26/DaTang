package com.xk.task.web.control;

import com.xk.task.data.dao.ITPlanDAO;
import com.xk.task.data.pojo.TEmployee;
import com.xk.task.data.pojo.TPlan;
import com.xk.task.data.pojo.TTask;
import com.xk.task.service.ITEmployeeService;
import com.xk.task.service.ITPlanService;
import com.xk.task.service.ITRoleService;
import com.xk.task.service.ITTaskService;
import com.xk.task.web.util.PageBean;
import com.xk.task.web.util.PlanDTO;
import com.xk.task.web.util.TaskInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//員工控制器
@Controller
public class StaffControl {
    @Resource(name = "empService")
    private ITEmployeeService empService;

    @Resource(name="roleService")
    private ITRoleService roleService;

    @Resource(name = "taskService")
    private ITTaskService taskService;

    @Resource(name = "planService")
    private ITPlanService planService;

    @RequestMapping("person/task.do")
    public void getAllTask(Integer pageNo,Integer pageSize,HttpSession session){
        System.out.println("任务记录..");

        //取出登录的员工
        TEmployee loginEmployee= (TEmployee) session.getAttribute("loginEmployee");

        //loginEmployee.getEmployee_Id() --》登录用户
//       List<TTask> allTasks= taskService.queryTaskListAndAssigner(loginEmployee.getEmployee_Id());
        //获得分页--当前传入的页面数 和页面大小
        if(pageNo==null){
            pageNo=1; //默认第一页
        }
        if(pageSize==null){
            pageSize=2; //页面大小默认为2
        }
        //计算页面大小
        int start=(pageNo-1)*pageSize+1;
        int end=pageNo*pageSize;
        //查询分页的数据
        List<TTask> allTasks= taskService.queryTaskListAndAssignerByPaging(loginEmployee.getEmployee_Id(),start,end);
        System.out.println("-----------------");
        System.out.println(allTasks);
        //实例化存储任务和计划的对象集合
        Map<Integer,TaskInfo> mapperTask=new HashMap<>();
        //遍历所有查询到的任务
        for (TTask t:allTasks){
            List<TPlan> plans= planService.queryPlanByTaskId(t.getTask_Id());
            System.out.println("根据任务编号："+t.getTask_Id()+"查询的计划集合");
            System.out.println(plans);
            mapperTask.put(t.getTask_Id(),new TaskInfo(t,plans));
        }
        System.out.println(mapperTask);
        List list=new ArrayList();
        //将映射的mapper存入准备放入pagebean的集合中
        list.add(mapperTask);
        int totalRecordCount=taskService.queryTaskListAndAssignerTotalCount(loginEmployee.getEmployee_Id());//查询记录总数
        PageBean pageBean= new PageBean<>(pageNo,pageSize,totalRecordCount, list);

        //將所有的任务存入session中
        session.setAttribute("pageBean",pageBean);



    }

    //queryTaskId
    @RequestMapping("person/taskview.do")
    public void queryPlanView(Integer queryTaskId,Integer planInfoPageNo,Integer planInfoPageSize, HttpSession session,HttpServletRequest request){
        if(queryTaskId==null){
            queryTaskId= (Integer) request.getAttribute("queryTaskId");
        }

        if(planInfoPageNo==null){
            //像请求体传递详细计划 默认当前第一页
            planInfoPageNo=1;
        }
        if(planInfoPageSize==null){
            planInfoPageSize=3;
        }
        //计算分页的开始和结束起始位置
        int start =(planInfoPageNo-1)*planInfoPageSize+1;
        int end=planInfoPageNo*planInfoPageSize;
        request.setAttribute("start",start);
        System.out.println("start:" +start);
        request.setAttribute("end",end);

        request.setAttribute("planInfoPageNo",planInfoPageNo);
        request.setAttribute("planInfoPageSize",planInfoPageSize);
        //计算计划开始和结束
        //获得页面对象中存储在list中的map
        PageBean pageBean= (PageBean) session.getAttribute("pageBean");
        pageBean.setPageNo(planInfoPageNo);
        pageBean.setPageSize(planInfoPageSize);

        Map<Integer,TaskInfo> mapperTask= (Map<Integer, TaskInfo>) pageBean.getList().get(0);
        pageBean.setTotalRecords(mapperTask.get(queryTaskId).getPlans().size());
        System.out.println("转发的任务map："+mapperTask.get(queryTaskId));
        //取出mapper通过编号找到该任务所有集合通过请求传递到页面显示
        session.setAttribute("taskInfo",mapperTask.get(queryTaskId));

    }

    @RequestMapping("person/addPlan.do")
    public String insertPlan(@ModelAttribute("plan") TPlan plan, HttpServletRequest request,String task_status){
        System.out.println(task_status);
        System.out.println(plan);
        int row=planService.addPlan(plan);

        if(row>0){
            //修改该计划对应的任务
            PageBean pageBean= (PageBean) request.getSession().getAttribute("pageBean");
        Map<Integer,TaskInfo> mapperTask= (Map<Integer, TaskInfo>) pageBean.getList().get(0);
            if(task_status.equals("待实施")){
                //将添加计划的任务状态修改为实施中
                row= taskService.updateTaskStatusBegin(plan.getTask_Id());
            }
            if(row>0){
                //状态修改成功 直接
                //修改展示的任务状态为实施中--》
                mapperTask.get(plan.getTask_Id()).getTask().setStatus("实施中");

            }

            //更新session中的值-->通过任务id取出 taskInfo对象重新查询 改任务对应的记录
            mapperTask.get(plan.getTask_Id()).setPlans(planService.queryPlanByTaskId(plan.getTask_Id()));
            request.setAttribute("msg","新增计划成功!");
            request.setAttribute("queryTaskId",plan.getTask_Id());
        }else{
            request.setAttribute("msg","新增计划失败.");
        }
        return "forward:/person/taskview.do";
    }

    @RequestMapping(value = "person/feedback.do",method = RequestMethod.GET)
    public void queryPlanById(Integer planId,Model model){
        TPlan plan= planService.queryPlanById(planId);
        //将改计划存入
        model.addAttribute("upPlan",plan);
        System.out.println("单个显示的计划---》"+plan);
    }

    /**
     *
     * @param deleteIds 删除的计划id
     * @param request
     * @param showTaskId 隐藏表单域中 当前显示的任务id
     * @return
     */
    @RequestMapping("person/deletePlans.do")
    public String deletePlans(Integer[] deleteIds,HttpServletRequest request,Integer showTaskId){
        System.out.println(deleteIds);
        int row=planService.deletePlan(deleteIds);
        if(row>0){
            PageBean pageBean= (PageBean) request.getSession().getAttribute("pageBean");
        Map<Integer,TaskInfo> mapperTask= (Map<Integer, TaskInfo>) pageBean.getList().get(0);
            //更新session中的值-->通过任务id取出 taskInfo对象重新查询 改任务对应的记录
            mapperTask.get(showTaskId).setPlans(planService.queryPlanByTaskId(showTaskId));
            request.setAttribute("msg","删除计划成功!");
            request.setAttribute("queryTaskId",showTaskId);
        }else{
            request.setAttribute("msg","删除失败!");
        }
        return "forward:/person/taskview.do";
    }

    @RequestMapping("person/updatePlan.do")
    public String updatePlanAdd(@ModelAttribute("plan") TPlan plan,HttpServletRequest request){
        System.out.println("添加反馈修改的员工--》"+plan);
        int row=planService.updatePlanById(plan);
        String view="forward:/person/taskview.do";
        //updatePlan.do
        if(row>0){
            request.getSession().setAttribute("msg","修改计划成功！");
            PageBean pageBean= (PageBean) request.getSession().getAttribute("pageBean");
             Map<Integer,TaskInfo> mapperTask= null;
            try {
                mapperTask= (Map<Integer, TaskInfo>) pageBean.getList().get(0);
                //更新session中的值-->通过任务id取出 taskInfo对象重新查询 改任务对应的记录
                mapperTask.get(plan.getTask_Id()).setPlans(planService.queryPlanByTaskId(plan.getTask_Id()));
                request.setAttribute("msg","计划反馈成功!");
                request.setAttribute("queryTaskId",plan.getTask_Id());
            }catch (Exception e){
                view="redirect:/person/checkpro.do";  //session中没有map（任务信息集合）  必定是高级查询时修改  修改成功返回高级查询计划界面
            }

        }else{
            request.setAttribute("msg","反馈失败!");
        }
        return view;
    }

    @RequestMapping("person/checkpro.do")
    public void advcanQueryPaln(@ModelAttribute("dto")PlanDTO dto, Integer pageNo, Integer pageSize, Integer flag, HttpSession session){
        //根据flag判断是表单提交再次进入计划查询界面
        System.out.println();
        System.out.println(dto);
        if(flag==null){
            //删除原来高级查询记录
            session.removeAttribute("pageBean");
            //第一次进入高级查询界面
            session.removeAttribute("dto");
            Map<Integer,TTask> map= (Map<Integer, TTask>) session.getAttribute("mapperTask");
            //当映射任务的集合为空则重新查询任务 并存入session
            if(map==null){
                //判断当前用户的任务集合是否存在session中
                List<TTask> tasks= (List<TTask>) session.getAttribute("allTasks");
                if(tasks==null) {
                    TEmployee employee = (TEmployee) session.getAttribute("loginEmployee");
                    //获得登录员工所有的任务
                    tasks = taskService.queryTaskListAndAssigner(employee.getEmployee_Id());
                    map=new HashMap();
                    //将查询到的任务集合存入map
                    for(TTask t:tasks){
                        map.put(t.getTask_Id(),t);
                    }
                    session.setAttribute("mapperTask",map);
                }
            }
        }else{
            if(flag==2){
                //点击分页页面进来的  直接获得session中查询的值
                dto=(PlanDTO) session.getAttribute("dto");
            }
            //从session中获得dto
            PageBean pb=new PageBean();
            if(pageNo==null){
                pageNo=1;
            }
            if(pageSize==null){
                pageSize=3;
            }
            pb.setPageNo(pageNo);
            pb.setPageSize(pageSize);
            System.out.println(pb);


            //不是表单提交时 清空查询的dto重新进入高级查询界面
            System.out.println("接受传输的pagebean:"+pb);
            //判断高级查询是否直接指向选中某个任务
            if(dto.getSearch_Task_Id()==0){
                //取出登录的员工
                TEmployee loginEmployee= (TEmployee) session.getAttribute("loginEmployee");
                //若没有选中任务去查询计划则传入登录员工的id去高级查询计划
                dto.setSearch_loginId(loginEmployee.getEmployee_Id());
            }
            int start=(pb.getPageNo()-1)*pb.getPageSize()+1;
            int end=pb.getPageNo()*pb.getPageSize();
            List <TPlan> allPlans=planService.advanceQueryPlanByPaging(dto,start,end);
            int totalCount=planService.advanceQueryPlanTotalCount(dto);
            System.out.println("数量总数"+totalCount);
            pb.setTotalRecords(totalCount);
            pb.setList(allPlans);
            session.setAttribute("pageBean",pb);
            System.out.println(pb.getList());
            session.setAttribute("dto",dto);
        }

        //第一次进入不做处理直接返回界面


    }
}
