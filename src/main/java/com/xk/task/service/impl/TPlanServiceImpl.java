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
        return dao.advanceQueryPlan(dto);
    }

    @Override
    public List<TPlan> advanceQueryPlanByPaging(PlanDTO dto, int start, int end) {
        try {
            if (dto!=null){
                return dao.advanceQueryPlanByPaging(dto,start,end);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int advanceQueryPlanTotalCount(PlanDTO dto) {
        return dao.advanceQueryPlanTotalCount(dto);
    }
}
