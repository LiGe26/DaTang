package com.xk.task.service;

import com.xk.task.data.pojo.TEmployee;


import java.util.List;

public interface ITEmployeeService {

    public TEmployee login(String name,String password);

    /**
     * 查询所有的员工
     * @return
     */
    public List<TEmployee> getAllEmployess();

    /**
     * 获得主管
     * @return
     */
    public List<TEmployee> getDents();

    public int addEmployees(TEmployee employee);

    //离职
    public int leaveOffice(int id);

    //根据员工编号查询员工
    public TEmployee queryEmployeeById(int empId);

    //设置上级主管业务
    public int setDents(int empId,Integer parentId);

    /**
     * 查询主管的所有下级员工业务
     * @return
     */
    public List<TEmployee> queryPersonsByManagerId(int mangerId);

    public List<TEmployee> queryPersonsByManagerIdByPaging(int managerId,int start,int end);

    /**
     * 查询主管下级总记录数
     * @param managerId 主管编号
     * @return
     */
    public int queryPersonsByManagerIdTotalRecords(int managerId);

    /**
     * 分页查询员工业务  -->除去系统管理
     * @param start
     * @param end
     * @return
     */
    public List<TEmployee> getAllEmployessByPaging(int start,int end);


    public int queryTotalEmployeeRecords();

    /**
     * 分页查询所有职位为员工的
     * @param start
     * @param end
     * @return
     */
    public List<TEmployee> getAllPersonByPaging(int start,int end);

    /**
     * 职位员工的记录数
     * @return
     */
    public int queryTotalPersonRecords();
}
