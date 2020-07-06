package com.xk.task.web.util;

import java.util.List;

public class  PageBean  <O>{
    private int pageNo;//当前页
    private int pageSize;//页面大小
    private int totalRecords;//记录总数
    private int totalPage;
    private List<O> list; //任务信息集合

    //获得页面总数
    public int getTotalPage(){
        return (this.totalRecords+pageSize-1)/pageSize;
    }

    //获得首页
    public int getFirstPage(){
        return 1;
    }
    //获得尾页
    public int getEndPage(){
        if(getTotalPage()==0){
            return 1;
        }
        return getTotalPage();
    }
    //上一页
    public int getPageUp(){
        if(pageNo==1){
            return 1;
        }
        return pageNo-1;
    }

    //下一页
    public int getPageDown(){
        //当前页为最大页数 不能继续下一页返回当前
        if(pageNo==getTotalPage()){
            return pageNo;
        }
        return pageNo+1;
    }


    public PageBean() {
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalRecords=" + totalRecords +
                ", list=" + list +
                '}';
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<O> getList() {
        return list;
    }

    public void setList(List<O> list) {
        this.list = list;
    }

    public PageBean(int pageNo, int pageSize, int totalRecords, List<O> list) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        this.list = list;
        this.totalPage=getTotalPage();
    }
}
