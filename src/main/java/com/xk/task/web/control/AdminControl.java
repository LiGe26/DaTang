package com.xk.task.web.control;



import com.xk.task.data.pojo.TEmployee;
import com.xk.task.data.pojo.TRole;
import com.xk.task.service.ITEmployeeService;
import com.xk.task.service.ITRoleService;
import com.xk.task.web.util.LoginFormLoad;
import com.xk.task.web.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//系統管理员控制器
@Controller
public class AdminControl {
    @Resource(name = "empService")
    private ITEmployeeService empService;

    @Resource(name="roleService")
    private ITRoleService roleService;



    @RequestMapping("admin/allEmployees.do")
    public String getEmployees(HttpSession session,@ModelAttribute("pb") PageBean pageBean){
        if(pageBean.getPageNo()==0){
            pageBean.setPageNo(1); //为0 第一次访问默认第一页
        }
        if(pageBean.getPageSize()==0){
            pageBean.setPageSize(4);
        }
        //调用业务层高级查询
        //计算页面大小pageSize
        int start=(pageBean.getPageNo()-1)*pageBean.getPageSize()+1;
        int end=pageBean.getPageNo()*pageBean.getPageSize();
        List<TEmployee> allEmployees= empService.getAllEmployessByPaging(start,end);
        pageBean.setList(allEmployees);
        pageBean.setTotalRecords(empService.queryTotalEmployeeRecords());
        System.out.println(allEmployees);

        session.setAttribute("pageBean",pageBean);
        List<TRole> roles= (List<TRole>) session.getAttribute("roles");
        //取出 maprole 映射角色 判断是否为空
        Map<Integer,String> mapRole= (Map<Integer, String>) session.getAttribute("maperRole");
        if(mapRole==null){
            if(roles==null){
                roles=roleService.queryAllRoles();
            }
            mapRole=new HashMap<>();
            for (TRole r:roles
            ) {
                mapRole.put(r.getRole_Id(),r.getRole_Name());
            }
            //将map存入session中
            session.setAttribute("maperRole",mapRole);
            System.out.println(mapRole);
        }
        System.out.println(mapRole);


//        //将角色信息存入session
//        session.setAttribute("map",mapRole);
        return "redirect:/admin/useradmin.jsp";
    }

    @RequestMapping("admin/beforeInsertEmp.do")
    public String insertEmployee(HttpSession session){
        //判断session中查询的角色集合
        List<TRole> roles= (List<TRole>) session.getAttribute("roles");
        if(roles==null){
            roles=roleService.queryAllRoles();
        }
        session.setAttribute("roles",roles);
        return "redirect:/admin/personadd.jsp";
    }

    @RequestMapping("admin/insertEmp.do")
    public String insertEmployee(@ModelAttribute("emp") TEmployee employee,Integer role_Id,HttpSession session){
        employee.setRole(new TRole(role_Id));
        System.out.println(employee);
        int row= empService.addEmployees(employee);
        String view="";
        if(row>0){
            if(role_Id==2){
                //更新session中主管
                List<TEmployee> allDents= empService.getDents();//获得当前所有主管
                //将主管存入session
                session.setAttribute("allDents",allDents);
            }
            //添加员工成功
            view="redirect:allEmployees.do"; //返回用户管理界面显示所有用户
        }else{
            view="admin/error";
            session.setAttribute("error","添加用户失败");
        }
        return view;
    }


    @RequestMapping("admin/yuanGong.do")
    public String getYuanGong(HttpSession session,@ModelAttribute("pb") PageBean pageBean){
        if(pageBean.getPageNo()==0){
            pageBean.setPageNo(1); //为0 第一次访问默认第一页
        }
        if(pageBean.getPageSize()==0){
            pageBean.setPageSize(4);
        }
        //调用业务层高级查询
        //计算页面大小pageSize
        int start=(pageBean.getPageNo()-1)*pageBean.getPageSize()+1;
        int end=pageBean.getPageNo()*pageBean.getPageSize();
        List<TEmployee> allEmployees= empService.getAllEmployessByPaging(start,end);
        pageBean.setList(allEmployees);
        pageBean.setTotalRecords(empService.queryTotalEmployeeRecords());
        System.out.println(allEmployees);
        session.setAttribute("pageBean",pageBean);
        List<TRole> roles= (List<TRole>) session.getAttribute("roles");
        //取出 maprole 映射角色 判断是否为空
        Map<Integer,String> mapRole= (Map<Integer, String>) session.getAttribute("maperRole");
        if(mapRole==null){
            if(roles==null){
                roles=roleService.queryAllRoles();
            }
            mapRole=new HashMap<>();
            for (TRole r:roles
            ) {
                mapRole.put(r.getRole_Id(),r.getRole_Name());
            }
            //将map存入session中
            session.setAttribute("maperRole",mapRole);
        }
        return "redirect:/admin/empadmin.jsp";
    }

    @RequestMapping("admin/deleteEmployee.do")
    public String leaveOffice(Integer deleteId,HttpSession session){
        System.out.println("进入了删除employee");
        int row=empService.leaveOffice(deleteId);
        String view="";
        if(row>0){

            //更新session中主管
            List<TEmployee> allDents= empService.getDents();//获得当前所有主管
            //将主管存入session
            session.setAttribute("allDents",allDents);

            //添加员工成功
            view="redirect:yuanGong.do"; //返回用户管理界面显示所有用户
        }else{
            view="admin/error";
            session.setAttribute("error","删除员工失败!");
        }
        return  view;
    }

    @RequestMapping("admin/empdistribute.do")
    public void fenPeiRenYuan(HttpSession session,@ModelAttribute("pb") PageBean pageBean){
        System.out.println("分配人员");
        if(pageBean.getPageNo()==0){
            pageBean.setPageNo(1); //为0 第一次访问默认第一页
        }
        if(pageBean.getPageSize()==0){
            pageBean.setPageSize(4);
        }
        //调用业务层高级查询
        //计算页面大小pageSize
        int start=(pageBean.getPageNo()-1)*pageBean.getPageSize()+1;
        int end=pageBean.getPageNo()*pageBean.getPageSize();
        List<TEmployee> allEmployees= empService.getAllPersonByPaging(start,end);
        pageBean.setList(allEmployees);
        pageBean.setTotalRecords(empService.queryTotalPersonRecords());
        session.setAttribute("pageBean",pageBean);
        List<TRole> roles= (List<TRole>) session.getAttribute("roles");
        //取出 maprole 映射角色 判断是否为空
        Map<Integer,String> mapRole= (Map<Integer, String>) session.getAttribute("maperRole");
        if(mapRole==null){
            if(roles==null){
                roles=roleService.queryAllRoles();
            }
            mapRole=new HashMap<>();
            for (TRole r:roles
            ) {
                mapRole.put(r.getRole_Id(),r.getRole_Name());
            }
            //将map存入session中
            session.setAttribute("maperRole",mapRole);
        }

    }

    @RequestMapping("admin/persondesc.do")
    public void queryDents(Integer queryId,HttpSession session){
        //根据当前查询的编号 查询详细信息
        TEmployee employee=empService.queryEmployeeById(queryId);
        //将查询的用户存入session
        session.setAttribute("queryEmployee",employee);
        if(session.getAttribute("allDents")==null){
            List<TEmployee> allDents= empService.getDents();//获得当前所有主管
            //将主管存入session
            session.setAttribute("allDents",allDents);
        }
        //取出 maprole 映射角色 判断是否为空
        Map<Integer,String> mapRole= (Map<Integer, String>) session.getAttribute("maperRole");
        if(mapRole==null){
            List<TRole> roles= (List<TRole>) session.getAttribute("roles"); //判断session中存储的roles集合是否为空
            if(roles==null){
                roles=roleService.queryAllRoles(); //为空重新查询
            }
            mapRole=new HashMap<>(); //将集合的角色信息放入map中
            for (TRole r:roles
            ) {
                mapRole.put(r.getRole_Id(),r.getRole_Name());
            }
            //将map存入session中
            session.setAttribute("maperRole",mapRole);
        }
    }

    @RequestMapping("admin/updateEmployee.do")
    public String updateEmployee(Integer empId,Integer parent_id,HttpSession session){
        if(parent_id==0){
            parent_id=null;
        }
        //调用修改员工上级的业务方法
        int row=empService.setDents(empId,parent_id);
        //清空存储的详细员工信息
        session.removeAttribute("queryEmployee");
        String view="";
        if(row>0){
            view="redirect:/admin/empdistribute.do";
        }else{
            view="error";
        }
        return view;
    }





}
