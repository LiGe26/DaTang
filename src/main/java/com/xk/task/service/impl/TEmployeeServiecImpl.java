package com.xk.task.service.impl;

import com.xk.task.data.dao.ITEmployeeDAO;
import com.xk.task.data.pojo.TEmployee;
import com.xk.task.service.ITEmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("empService")
public class TEmployeeServiecImpl  implements ITEmployeeService {

    @Resource(name = "empDao")
    private ITEmployeeDAO dao;

    @Override
    public TEmployee login(String name, String password) {
        return dao.login(new TEmployee(name,password));
    }

    @Override
    public List<TEmployee> getAllEmployess() {
        return dao.getAllEmployess();
    }

    @Override
    public List<TEmployee> getDents() {
        return dao.getDents(2);
    }

    @Override
    public int addEmployees(TEmployee employee) {
        return dao.insertEmployee(employee);
    }

    @Override
    public int leaveOffice(int id) {
        return dao.deleteEmployee(id);
    }

    @Override
    public TEmployee queryEmployeeById(int id) {
        List<TEmployee> employees= dao.queryEmployeeById(id);
        if(employees.size()>0){
            //查询到员工
            return employees.get(0);
        }
        return null;//没有查询到
    }

    @Override
    public int setDents(int empId, Integer parentId) {
        return dao.updateEmployeeParent(empId,parentId);
    }

    @Override
    public List<TEmployee> queryPersonsByManagerId(int mangerId) {
        return dao.queryPersonsByManagerId(mangerId);
    }

    @Override
    public List<TEmployee> queryPersonsByManagerIdByPaging(int mangerId, int start, int end) {
        String sql="select * from (select ROWNUM rm,t.* from T_EMPLOYEE t where PARENT_ID=? and WORKSTATE=1 ) temp where temp.rm between ? and ?";
        return dao.queryAllEmployees(sql,new Object[]{mangerId,start,end});
    }

    @Override
    public int queryPersonsByManagerIdTotalRecords(int managerId) {
        String sql="select count(*) from T_EMPLOYEE where PARENT_ID=? and WORKSTATE=1";
        return dao.queryForInt(sql,new Object[]{managerId});
    }

    @Override
    public List<TEmployee> getAllEmployessByPaging(int start, int end) {
        String sql="select * from (select rownum rm,te.* from T_EMPLOYEE te where ROLE_ID!=1 and WORKSTATE=1 order by te.EMPLOYEE_ID desc)temp where temp.rm between ? and ?";
        return dao.queryAllEmployees(sql,new Object[]{start,end});
    }

    @Override
    public int queryTotalEmployeeRecords() {
        String sql="select count(*) from T_EMPLOYEE  where ROLE_ID!=1 and WORKSTATE=1";
        return dao.queryForInt(sql,null);
    }

    @Override
    public List<TEmployee> getAllPersonByPaging(int start, int end) {
        String sql="select * from (select rownum rm,te.* from T_EMPLOYEE te where ROLE_ID=3 and WORKSTATE=1 order by te.EMPLOYEE_ID desc)temp where temp.rm between ? and ?";
        return dao.queryAllEmployees(sql,new Object[]{start,end});
    }

    @Override
    public int queryTotalPersonRecords() {
        String sql="select count(*) from T_EMPLOYEE  where ROLE_ID=3 and WORKSTATE=1";
        return dao.queryForInt(sql,null);
    }


}
