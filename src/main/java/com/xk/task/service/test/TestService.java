package com.xk.task.service.test;


import com.xk.task.data.dao.ITPlanDAO;
import com.xk.task.data.dao.ITRoleDAO;
import com.xk.task.data.dao.ITTaskDAO;
import com.xk.task.service.ITEmployeeService;
import com.xk.task.service.ITPlanService;
import com.xk.task.service.ITTaskService;
import com.xk.task.web.util.PlanDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

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

    //    @Autowired
//    SqlSessionTemplate sqlSession;
    @Test
    public void test() throws Exception {
        System.out.println(service.login("zhangsan","123456"));
        System.out.println(dao.queryAllRoles());


        System.out.println("根据编号查询角色"+ dao.queryRoleById(2));
        System.out.println("查询所有角色:"+dao.queryAllRoles());

        System.out.println(taskService.queryTaskListAndAssigner(6));
        System.out.println(taskService.queryTaskListAndAssignerByPaging(6,1,5));
        System.out.println("6号员工任务总数"+taskService.queryTaskListAndAssignerTotalCount(6));
        System.out.println("修改任务状态为实施受影响行数："+taskService.updateTaskStatusBegin(61));
        System.out.println("6员工全部任务"+taskService.queryTaskListAndImplementor(2));
        System.out.println("分页："+taskService.queryTaskListAndImplementorByPaging(2,1,5));
        System.out.println("未实施："+taskService.queryTaskUnDoneByManagerIdByPaging(2,1,5));
        System.out.println("实施中："+taskService.queryTaskDoneByManagerIdByPaging(2,1,5));
        System.out.println("待实施的记录数"+taskService.queryTaskUnDoneByManagerTotalRecords(2));
        System.out.println("实施中的记录数"+taskService.queryTaskDoneByManagerTotalRecords(2));
        System.out.println("查詢任務2："+taskService.queryTaskAndImplementorByTaskId(2));

        System.out.println(taskService.queryTaskListAndImplementorTotalCount(2));
        System.out.println(taskService.deleteTaskByIds(new Integer[]{102,101}));

        System.out.println("完成任务影响行："+taskService.finishTask(47));





//        System.out.println("根据编号查询角色"+ dao.queryRoleById(2));
//        System.out.println("查询所有角色:"+dao.queryAllRoles());
//
//        System.out.println(taskService.queryTaskListAndAssigner(6));
//        System.out.println(taskService.queryTaskListAndAssignerByPaging(6,1,5));
//        System.out.println("6号员工任务总数"+taskService.queryTaskListAndAssignerTotalCount(6));
//        System.out.println("修改任务状态为实施受影响行数："+taskService.updateTaskStatusBegin(61));
        PlanDTO dto=new PlanDTO();

        dto.setSearch_Plan_Name("1");
        System.out.println(planService.advanceQueryPlanByPaging(dto,1,5));;

    }


}
