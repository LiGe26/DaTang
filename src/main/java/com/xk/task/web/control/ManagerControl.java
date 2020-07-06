package com.xk.task.web.control;

import com.xk.task.data.pojo.TEmployee;
import com.xk.task.data.pojo.TPlan;
import com.xk.task.data.pojo.TRole;
import com.xk.task.data.pojo.TTask;
import com.xk.task.service.ITEmployeeService;
import com.xk.task.service.ITPlanService;
import com.xk.task.service.ITRoleService;
import com.xk.task.service.ITTaskService;
import com.xk.task.web.util.PageBean;
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

@Controller
public class ManagerControl {
    @Resource(name = "empService")
    private ITEmployeeService empService;

    @Resource(name="roleService")
    private ITRoleService roleService;

    @Resource(name = "taskService")
    private ITTaskService taskService;

    @Resource(name = "planService")
    private ITPlanService planService;


    @RequestMapping("/manager/task.do")
    public void addTask(HttpSession session){

        //获得登录主管的编号
        TEmployee loginEmployee= (TEmployee) session.getAttribute("loginEmployee");
        //查询出改主管的所有下级
        List<TEmployee> allPerson= empService.queryPersonsByManagerId(loginEmployee.getEmployee_Id());
        session.setAttribute("allPerson",allPerson);

    }

    @RequestMapping("/manager/addTask.do")
    public String addTask(@RequestParam("implementor_id") Integer implementor_id, @ModelAttribute("addTask") TTask task, HttpSession session){
        System.out.println(task);
        task.setImplementor(new TEmployee(implementor_id));
        //获得登录主管的编号
        TEmployee loginEmployee= (TEmployee) session.getAttribute("loginEmployee");
        //设置即将添加任务实例的分配人
        task.setAssigner(new TEmployee(loginEmployee.getEmployee_Id()));
        int row=taskService.addTask(task);
        String view="";
        if(row>0){
            session.setAttribute("msg","添加任务成功");
            view="forward:taskview.do"; //回到任务展示界面
        }else{
            session.setAttribute("msg","添加失败");
            view="forward:task.do";
        }
        return view;
    }

    @RequestMapping("/manager/taskview.do")
    public void getAllTask(HttpSession session, @ModelAttribute("pb")PageBean pageBean){
        if(pageBean.getPageNo()==0){
            pageBean.setPageNo(1); //为0 第一次访问默认第一页
        }
        if(pageBean.getPageSize()==0){
            pageBean.setPageSize(4);
        }
        //获得登录主管的编号
        TEmployee loginEmployee= (TEmployee) session.getAttribute("loginEmployee");
        //调用业务层高级查询
        //计算页面大小pageSize
        int start=(pageBean.getPageNo()-1)*pageBean.getPageSize()+1;
        int end=pageBean.getPageNo()*pageBean.getPageSize();
        //分页查询任务
        List<TTask> tasks= taskService.queryTaskListAndImplementorByPaging(loginEmployee.getEmployee_Id(),start,end);
        //查询记录总数
        int totalCount= taskService.queryTaskListAndImplementorTotalCount(loginEmployee.getEmployee_Id());
        pageBean.setTotalRecords(totalCount);
        Map <Integer, TaskInfo> mapper=new HashMap();
        for (TTask t:tasks
             ) {

            List<TPlan> plans= planService.queryPlanByTaskId(t.getTask_Id());
            mapper.put(t.getTask_Id(),new TaskInfo(t,plans));
        }
        List list= new ArrayList<>();
        list.add(mapper);
        pageBean.setList(list);
        //将查询的任务放入集合
        session.setAttribute("pageBean",pageBean);
        System.out.println("页面对象：."+pageBean);
    }

    @RequestMapping("/manager/taskparticular.do")
    public void taskInfo(Integer queryTaskId,Integer planInfoPageNo,Integer planInfoPageSize, HttpSession session, HttpServletRequest request){
//        if(queryTaskId==null){
//            queryTaskId= (Integer) request.getAttribute("queryTaskId");
//        }
//        PageBean pageBean= (PageBean) request.getSession().getAttribute("pageBean");
//        Map<Integer,TaskInfo> mapperTask= (Map<Integer, TaskInfo>) pageBean.getList().get(0);
//        System.out.println("转发的任务map："+mapperTask.get(queryTaskId));
//
//        //取出mapper通过编号找到该任务所有集合通过请求传递到页面显示
//        session.setAttribute("taskInfo",mapperTask.get(queryTaskId));
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
        request.getSession().setAttribute("start",start);
        System.out.println("start:" +start);
        request.getSession().setAttribute("end",end);

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

    @RequestMapping(value = "/manager/program.do",method = RequestMethod.POST)
    public void planInfo(Integer queryPlanId, HttpServletRequest request){
        TPlan plan= planService.queryPlanById(queryPlanId);
        request.setAttribute("planInfo",plan);
    }


    /**
     * 查看主管下級
     */
    @RequestMapping(value = "/manager/checkper.do")

    public void selectPerson(HttpSession session, @ModelAttribute("pb")PageBean pageBean){
        if(pageBean.getPageNo()==0){
            pageBean.setPageNo(1); //为0 第一次访问默认第一页
        }
        if(pageBean.getPageSize()==0){
            pageBean.setPageSize(4);
        }
        //获得登录主管的编号
        TEmployee loginEmployee= (TEmployee) session.getAttribute("loginEmployee");
        //调用业务层高级查询
        //计算页面大小pageSize
        int start=(pageBean.getPageNo()-1)*pageBean.getPageSize()+1;
        int end=pageBean.getPageNo()*pageBean.getPageSize();

        List<TEmployee> allPerson= empService.queryPersonsByManagerIdByPaging(loginEmployee.getEmployee_Id(),start,end);//loginEmployee.getEmployee_Id()
        pageBean.setList(allPerson);
        pageBean.setTotalRecords(empService.queryPersonsByManagerIdTotalRecords(loginEmployee.getEmployee_Id()));
        session.setAttribute("pageBean",pageBean);


//        Map<Integer,TRole> mapRole = (Map<Integer, TRole>) session.getAttribute("mapperRole");
//        //session中没有存储映射角色的map
//        if(mapRole==null){
//            //获得登录表单装载时的角色集合
//            List<TRole> roles= (List<TRole>) session.getAttribute("roles");
//            mapRole=new HashMap();
//            //遍历所有角色集合将其封装到map集合中
//            for (TRole role:roles){
//                mapRole.put(role.getRole_Id(),role);
//            }
//            session.setAttribute("mapperRole",mapRole);
//        }


    }

    //personinfo
    @RequestMapping(value = "/manager/personinfo.do")
    public void personInfo(Integer queryPersonId, Model model){
        TEmployee employee= empService.queryEmployeeById(queryPersonId);
        model.addAttribute("personInfo",employee);

    }

    //taskundone.jsp 未实施任务页面
    @RequestMapping(value = "/manager/taskundone.do")
    public void taskUndone(HttpSession session ,@ModelAttribute("pb")PageBean pageBean){
        if(pageBean.getPageNo()==0){
            pageBean.setPageNo(1);
        }
        if(pageBean.getPageSize()==0){
            pageBean.setPageSize(3);
        }
        //计算页面大小pageSize
        int start=(pageBean.getPageNo()-1)*pageBean.getPageSize()+1;
        int end=pageBean.getPageNo()*pageBean.getPageSize();
        //获得登录主管的编号
        TEmployee loginEmployee= (TEmployee) session.getAttribute("loginEmployee");
        //根据登录员工编号查询所有未实施任务
        List<TTask> tasks= taskService.queryTaskUnDoneByManagerIdByPaging(loginEmployee.getEmployee_Id(),start,end);
        pageBean.setList(tasks);
        pageBean.setTotalRecords(taskService.queryTaskUnDoneByManagerTotalRecords(loginEmployee.getEmployee_Id()));
        session.setAttribute("pageBean",pageBean);
    }

    //taskundone.jsp 未实施任务页面
    @RequestMapping(value = "/manager/adjust.do")
    public void updateTask(Integer taskId,HttpSession session,Model model){
        //获得登录主管的编号
        TEmployee loginEmployee= (TEmployee) session.getAttribute("loginEmployee");
        //从session获得存储主管所有员工的集合
        List<TEmployee> employees= (List<TEmployee>) session.getAttribute("allPerson");
        //判断是否为空 为空则直接查询
        if(employees==null){
            //查询出主管的所有下级
            employees= empService.queryPersonsByManagerId(loginEmployee.getEmployee_Id());
            session.setAttribute("allPerson",employees);
        }
        //查询展示的任务详情
        TTask task= taskService.queryTaskAndImplementorByTaskId(taskId);
        model.addAttribute("taskInfo",task);


    }

    /**
     *
     * @param implementor_id 实施人编号
     * @param task
     * @param session
     * @return
     */
    @RequestMapping(value = "/manager/updateTask.do")
    public String updateTask(Integer implementor_id,@ModelAttribute("updateTask") TTask task,HttpSession session){
        //获得登录主管的编号
        TEmployee loginEmployee= (TEmployee) session.getAttribute("loginEmployee");
        task.setImplementor(new TEmployee((implementor_id)));
        System.out.println("执行修改的任务信息....\n"+task);
        int row= taskService.updateTaskBytaskId(task);
        if(row>0){
            System.out.println("修改任務成功。。。");
            session.setAttribute("msg","任务修改成功");
        }else{
            System.out.println("任務修改失敗。。。");
            session.setAttribute("msg","任务修改失败");
        }
        return "redirect:/manager/taskundone.do";
    }

    @RequestMapping(value = "/manager/deleteTask.do")
    public String deleteTask(Integer [] deleteTaskIds,HttpSession session){
        int row=taskService.deleteTaskByIds(deleteTaskIds);
        if(row>0){
            session.setAttribute("msg","删除成功！");
        }else{
            session.setAttribute("msg","删除失败");
        }
        return "redirect:/manager/taskundone.do";
    }

    @RequestMapping("/manager/intendance.do")
    public void queryStartTask(HttpSession session,@ModelAttribute("pb")PageBean pageBean){
        if(pageBean.getPageNo()==0){
            pageBean.setPageNo(1);
        }
        if(pageBean.getPageSize()==0){
            pageBean.setPageSize(3);
        }
        //计算页面大小pageSize
        int start=(pageBean.getPageNo()-1)*pageBean.getPageSize()+1;
        int end=pageBean.getPageNo()*pageBean.getPageSize();
        //获得登录主管的编号
        TEmployee loginEmployee= (TEmployee) session.getAttribute("loginEmployee");
//实     例化存储任务和计划的对象集合
        Map<Integer,TaskInfo> mapperTask=new HashMap<>();
        //根据登录员工编号查询所有未实施任务
        List<TTask> tasks= taskService.queryTaskDoneByManagerIdByPaging(loginEmployee.getEmployee_Id(),start,end);
        //遍历所有查询到的任务
        for (TTask t:tasks){
            List<TPlan> plans= planService.queryPlanByTaskId(t.getTask_Id());
            System.out.println("根据任务编号："+t.getTask_Id()+"查询的计划集合");
            System.out.println(plans);
            mapperTask.put(t.getTask_Id(),new TaskInfo(t,plans));
        }
        System.out.println(mapperTask);
        List list=new ArrayList();
        list.add(mapperTask);
        pageBean.setList(list);
        pageBean.setTotalRecords(taskService.queryTaskDoneByManagerTotalRecords(loginEmployee.getEmployee_Id()));
        session.setAttribute("pageBean",pageBean);
        System.out.println(pageBean);

    }

    //programinten.do
    @RequestMapping("/manager/programinten.do")
    public void followTask(Integer queryTaskId,Integer planInfoPageNo,Integer planInfoPageSize, HttpSession session, HttpServletRequest request){
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
        request.getSession().setAttribute("start",start);
        System.out.println("start:" +start);
        request.getSession().setAttribute("end",end);

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
        System.out.println("計劃個數："+mapperTask.get(queryTaskId).getPlans().size());
        System.out.println("页面对象页数："+pageBean.getTotalPage());

    }


    @RequestMapping("/manager/finish.do")
    public String finishTask(Integer task_Id,String status,HttpSession session){
        if(!status.equals("undone")){
            //已完成 调用修改任务完成的业务层
            int row=taskService.finishTask(task_Id);
            if(row>0){
                session.setAttribute("msg","修改成功！");
            }else{
                session.setAttribute("msg","修改失败！");
            }

        }
        return "redirect:/manager/intendance.do";
//undone
    }
}
