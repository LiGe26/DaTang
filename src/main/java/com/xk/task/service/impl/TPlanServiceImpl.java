package com.xk.task.service.impl;

import com.xk.task.data.dao.ITPlanDAO;
import com.xk.task.data.pojo.TPlan;
import com.xk.task.data.util.StringUtil;
import com.xk.task.service.ITPlanService;
import com.xk.task.web.util.PlanDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("planService")
public class TPlanServiceImpl implements ITPlanService {

    @Resource(name = "planDao")
    ITPlanDAO dao;

    @Override
    public List<TPlan> queryPlanByTaskId(int taskId) {
        return dao.queryPlanByTaskId(taskId);
    }

    @Override
    public int addPlan(TPlan plan) {
        return dao.addPlan(plan);
    }

    @Override
    public TPlan queryPlanById(int id) {
        return dao.queryPlanById(id);
    }

    @Override
    public int deletePlan(Integer[] objs) {
        return dao.deletePlan(objs);
    }

    @Override
    public int updatePlanById(TPlan plan) {
        return dao.updatePlanById(plan);
    }

    @Override
    public List<TPlan> advanceQueryPlan(PlanDTO dto) {
        StringBuffer sbf=new StringBuffer();

        List params=new ArrayList();
        if(dto!=null){
            if(dto.getSearch_loginId()!=0){
                //不带任务编号 连表查询
                sbf.append("select * from T_PLAN inner join T_TASK TT on T_PLAN.TASK_ID = TT.TASK_ID where IMPLEMENTOR_ID=? ");
                params.add(dto.getSearch_loginId());
            }else{
                //带任务编号 loginId则为0 直接进行查询即可
                sbf.append("select * from T_PLAN where 1=1 ");
            }
            //实例化字符串工具类判断是否为空
            StringUtil util=new StringUtil();
            //查询的计划名不为空
            if(!util.isEmpty(dto.getSearch_Plan_Name())){
                sbf.append(" and PLAN_NAME like ? ");
                params.add("%"+dto.getSearch_Plan_Name()+"%");
            }
            if(dto.getSearch_Task_Id()!=0){
                sbf.append(" and TASK_ID=? ");
                params.add(dto.getSearch_Task_Id());
            }
            if(dto.getSearch_Begin_Date1()!=null && dto.getSearch_Begin_Date2()!=null){
                //判断前一个时间需要大在后一个之前
                if(dto.getSearch_Begin_Date1().before(dto.getSearch_Begin_Date2())){
                    sbf.append(" and BEGIN_DATE between ? and ? ");
                    params.add(dto.getSearch_Begin_Date1());
                    params.add(dto.getSearch_Begin_Date2());
                }

            }
            if(dto.getSearch_End_Date1()!=null && dto.getSearch_End_Date2()!=null){
                //判断前一个时间需要大在后一个之前
                if(dto.getSearch_End_Date1().before(dto.getSearch_End_Date2())){
                    sbf.append(" and END_DATE between ? and ? ");
                    params.add(dto.getSearch_End_Date1());
                    params.add(dto.getSearch_End_Date2());
                }

            }
            if(!util.isEmpty(dto.getSearch_Is_Feedback())){
                sbf.append(" and IS_FEEDBACK=?");
                params.add(dto.getSearch_Is_Feedback());
            }
        }
        return dao.queryPlanList(sbf.toString(),params.toArray());
    }

    @Override
    public List<TPlan> advanceQueryPlanByPaging(PlanDTO dto, int start, int end) {
        StringBuffer sbf=new StringBuffer();

        List params=new ArrayList();
        if(dto!=null){
            if(dto.getSearch_loginId()!=0){
                //不带任务编号 连表查询
                sbf.append("select * from (select ROWNUM rm,tp.* from T_PLAN tp inner join T_TASK tt on tp.TASK_ID = tt.TASK_ID where tt.IMPLEMENTOR_ID=? ");
                params.add(dto.getSearch_loginId());
            }else{
                //带任务编号 loginId则为0 直接进行查询即可
                sbf.append("select * from (select ROWNUM rm,tp.* from T_PLAN tp where 1=1 ");
            }
            //实例化字符串工具类判断是否为空
            StringUtil util=new StringUtil();
            //查询的计划名不为空
            if(!util.isEmpty(dto.getSearch_Plan_Name())){
                sbf.append(" and PLAN_NAME like ? ");
                params.add("%"+dto.getSearch_Plan_Name()+"%");
            }
            if(dto.getSearch_Task_Id()!=0){
                sbf.append(" and TASK_ID=? ");
                params.add(dto.getSearch_Task_Id());
            }
            if(dto.getSearch_Begin_Date1()!=null && dto.getSearch_Begin_Date2()!=null){
                //判断前一个时间需要大在后一个之前
                if(dto.getSearch_Begin_Date1().before(dto.getSearch_Begin_Date2())){
                    sbf.append(" and tp.BEGIN_DATE between ? and ? ");
                    params.add(dto.getSearch_Begin_Date1());
                    params.add(dto.getSearch_Begin_Date2());
                }

            }
            if(dto.getSearch_End_Date1()!=null && dto.getSearch_End_Date2()!=null){
                //判断前一个时间需要大在后一个之前
                if(dto.getSearch_End_Date1().before(dto.getSearch_End_Date2())){
                    sbf.append(" and tp.END_DATE between ? and ? ");
                    params.add(dto.getSearch_End_Date1());
                    params.add(dto.getSearch_End_Date2());
                }

            }
            if(!util.isEmpty(dto.getSearch_Is_Feedback())){
                sbf.append(" and IS_FEEDBACK=? ");
                params.add(dto.getSearch_Is_Feedback());
            }

            sbf.append(") temp where temp.rm between ? and ?");
            params.add(start);
            params.add(end);
            System.out.println("高级查询sql："+sbf.toString());
        }
        return dao.queryPlanList(sbf.toString(),params.toArray());
    }

    @Override
    public int advanceQueryPlanTotalCount(PlanDTO dto) {
        StringBuffer sbf=new StringBuffer();

        List params=new ArrayList();
        if(dto!=null){
            if(dto.getSearch_loginId()!=0){
                //不带任务编号 连表查询
                sbf.append("select count(*) from T_PLAN tp inner join T_TASK tt on tp.TASK_ID = tt.TASK_ID where tt.IMPLEMENTOR_ID=? ");
                params.add(dto.getSearch_loginId());
            }else{
                //带任务编号 loginId则为0 直接进行查询即可
                sbf.append("select count(*)  from T_PLAN tp where 1=1 ");
            }
            //实例化字符串工具类判断是否为空
            StringUtil util=new StringUtil();
            //查询的计划名不为空
            if(!util.isEmpty(dto.getSearch_Plan_Name())){
                sbf.append(" and tp.PLAN_NAME like ? ");
                params.add("%"+dto.getSearch_Plan_Name()+"%");
            }
            if(dto.getSearch_Task_Id()!=0){
                sbf.append(" and tp.TASK_ID=? ");
                params.add(dto.getSearch_Task_Id());
            }
            if(dto.getSearch_Begin_Date1()!=null && dto.getSearch_Begin_Date2()!=null){
                //判断前一个时间需要大在后一个之前
                if(dto.getSearch_Begin_Date1().before(dto.getSearch_Begin_Date2())){
                    sbf.append(" and tp.BEGIN_DATE between ? and ? ");
                    params.add(dto.getSearch_Begin_Date1());
                    params.add(dto.getSearch_Begin_Date2());
                }

            }
            if(dto.getSearch_End_Date1()!=null && dto.getSearch_End_Date2()!=null){
                //判断前一个时间需要大在后一个之前
                if(dto.getSearch_End_Date1().before(dto.getSearch_End_Date2())){
                    sbf.append(" and tp.END_DATE between ? and ? ");
                    params.add(dto.getSearch_End_Date1());
                    params.add(dto.getSearch_End_Date2());
                }

            }
            if(!util.isEmpty(dto.getSearch_Is_Feedback())){
                sbf.append(" and tp.IS_FEEDBACK=? ");
                params.add(dto.getSearch_Is_Feedback());
            }


            System.out.println("高级查询sql："+sbf.toString());
        }
        return dao.queryPlanForInt(sbf.toString(),params.toArray());
    }
}
