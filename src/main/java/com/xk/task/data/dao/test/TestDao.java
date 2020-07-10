package com.xk.task.data.dao.test;

import com.xk.task.data.dao.ITEmployeeDAO;
import com.xk.task.data.dao.ITPlanDAO;
import com.xk.task.data.pojo.TEmployee;
import com.xk.task.data.pojo.TPlan;
import com.xk.task.data.pojo.TRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ather ZhiXuan Hu
 * @date 2020/7/8 - 9:25
 */
@RunWith(SpringJUnit4ClassRunner.class)//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestDao {
//    @Resource(name = "empDao")
    //ITEmployeeDAO dao;
    @Resource(name = "planDao")
    ITPlanDAO dao;
    @Test
    //这是一个测试方法
    public void Test(){

        TPlan plan=dao.queryPlanById(2);
        System.out.println(plan.toString());
//        plan.setPlan_Name("计划1_4");
//        plan.setBegin_Date(new Date());
//        plan.setEnd_Date(new Date());
//        plan.setPlan_Id(2);
//        plan.setPlan_Desc(null);
//        int countplan=dao.addPlan(plan);
//        System.out.println("返回："+countplan);
//        List<TPlan> plans=dao.queryPlanByTaskId(5);
//        System.out.println("输出："+plans.toString());

//        List<TEmployee> emp=dao.queryPersonsByManagerId(5);
//        System.out.println("数据："+emp.toString());
//        TEmployee employee=dao.queryEmployeeById(41);
//        System.out.println("数据："+employee);
//        List<TEmployee> allEmp=dao.getDents(3);
//        System.out.println("所有的数据："+allEmp.toString());
//        TEmployee employee=new TEmployee();
//        employee.setEmployee_Name("叼毛");
//        employee.setPassword("123");
//        employee.setReaL_Name("毛龙斌");
//        employee.setSex("m");
//        employee.setBirthday(new Date());
//        employee.setDuty("会所少爷");
//        employee.setEnrolldate(new Date());
//        employee.setEducation("大学生");
//        employee.setMajor("装逼");
//        employee.setExperience("站着睡觉");
//        employee.setRole(new TRole(3));
//        employee.setParent(new TEmployee(0));
//        int count=dao.insertEmployee(employee);

//        int count=dao.deleteEmployee(22);
//        System.out.println("查询到的数据："+count);
//        System.out.println(dao.login(employee));

        System.out.println(  dao.queryPlanByTaskId(95));
    }
}
