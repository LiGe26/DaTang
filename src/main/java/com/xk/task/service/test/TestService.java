package com.xk.task.service.test;


import com.xk.task.data.dao.ITRoleDAO;
import com.xk.task.data.dao.ITTaskDAO;
import com.xk.task.service.ITEmployeeService;
import com.xk.task.service.ITPlanService;
import com.xk.task.service.ITTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mvc.xml")
public class TestService {

    @Resource(name = "empService")
    ITEmployeeService service;

    @Resource(name="roleDao")
    ITRoleDAO dao;

    @Resource(name = "planService")
    private ITPlanService planService;
    @Resource(name = "taskService")
    private ITTaskService taskService;
    @Resource(name = "taskDao")
    ITTaskDAO dao1;

    @Test
    public void test(){
        System.out.println(service.login("zhangsan","123456"));
        System.out.println(dao.queryAllRoles());

    }


}
