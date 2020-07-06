package com.xk.task.web.control;

import com.xk.task.data.pojo.TEmployee;
import com.xk.task.data.pojo.TRole;
import com.xk.task.service.ITEmployeeService;
import com.xk.task.service.ITRoleService;
import com.xk.task.web.util.LoginFormLoad;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginControl {
    @Resource(name = "empService")
    private ITEmployeeService empService;

    @Resource(name="roleService")
    private ITRoleService roleService;


    /**
     * 装载实体类 进入登录界面表单登录
     * @return
     */
    @RequestMapping("/load.do")
    public String loadForm(Model model, HttpSession session){
        //  spring表单装载实例
        LoginFormLoad loadForm=new LoginFormLoad(new TEmployee(),new TRole());
        //查询所有的角色放入请求范围
        session.setAttribute("roles",roleService.queryAllRoles());
        model.addAttribute("loadForm",loadForm);
        return "login";
    }

    @RequestMapping("login.do")
    public String login(@ModelAttribute("loadForm") LoginFormLoad form, HttpServletRequest request){
        String name=form.getEmployee().getEmployee_Name();
//        System.out.println("表单输入的用户名："+name+"--->密码："+form.getEmployee().getPassword()+"-->选择的角色："+form.getEmployee().getRole().getRole_Id());
        String password=form.getEmployee().getPassword();
        //调用业务层判断用户登录用户名和密码是否正确
        TEmployee employee= empService.login(name,password);
        String view="";
        if(employee!=null){
            System.out.println(employee);
            //密码用户名正确
            if(employee.getRole().getRole_Id()==form.getEmployee().getRole().getRole_Id()){
                request.getSession().setAttribute("loginEmployee",employee);
                //选中的角色与登录员工选择角色一致
                switch (employee.getRole().getRole_Id()){
                    case 1://系统管理员
                        view="redirect:admin/welcome.jsp";
                        break;
                    case 2://主管
                        view="redirect:manager/welcome.jsp";
                        break;
                    case 3://员工
                        view="redirect:person/welcome.jsp";
                        break;
                }

            }else{
                //与正确角色不一致
                request.setAttribute("msg","登录用户与角色不符！");
                view= "forward:/load.do"; //跳回登录界面
            }
        }else{
            //用户名或密码错误
            request.setAttribute("msg","用户名或密码错误！");
            view="forward:load.do";//登录界面
        }
        return view;
    }

    //员工退出
    @RequestMapping("/logout.do")
    public String logout(HttpSession session){
        System.out.println("失效");
        session.invalidate(); //会话失效
        return "redirect:load.do"; //返回登录界面

    }

}
