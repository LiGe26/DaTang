package com.xk.task.web.interceptor;

import com.xk.task.data.pojo.TEmployee;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizeInterceptor implements HandlerInterceptor {
    //放开登录
    private static final String[] IGNORE_URI =
            {"/load.do", "/login.do",".gif",".css",".jpg",".js","/logout.do"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag=false; //标志是否是放开资源
        String url=request.getServletPath();
        System.out.println("url:-->"+url);
        //若路径为忽略路径 则直接放开登录和加载登录的控制器路径
        for (String igUrl:IGNORE_URI){
            if(url.contains(igUrl)){
                flag= true;
                break;
            }
        }
        //不是放开资源
        if(!flag){
            //判断用户是否登录request.setAttribute("msg","登录用户与角色不符！");
            TEmployee employee= (TEmployee) request.getSession().getAttribute("loginEmployee");
            if(employee==null){
                request.getSession().setAttribute("msg","请先登录！");
                //未登录
                flag= false;
            }else{
                int id=employee.getRole().getRole_Id();
                System.out.println("访问的用户角色id为"+id);
                //判断登录用户访问权限
                if((id==1 && !url.contains("/admin")||!url.contains(".do")) || (id==2 && !url.contains("/manager")) ||
                        (id==3 && !url.contains("/person"))){
                    //访问路径不包括
                    request.getSession().setAttribute("msg","登录用户与角色不符！");
                    flag=false;//标识不通过
                }else{
                    flag=true;
                }
            }
        }

        if(!flag){
            System.out.println("当前不通过的url"+url);
            response.sendRedirect("/DaTang/load.do");
            System.out.println("重定向/DaTang/load.do");
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
