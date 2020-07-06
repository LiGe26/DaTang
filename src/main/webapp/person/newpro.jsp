<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>新建计划</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>

<body><!-- #BeginLibraryItem "/Library/topbanner.lbi" -->
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<div id="logo"><img src="../images/top.jpg" width="1002" height="258" /></div>
<!-- #EndLibraryItem --><table width="1002" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="rightimg"><!-- #BeginLibraryItem "/Library/left2.lbi" -->
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<div id="left">
      <p><a href="task.do" target="_self" >计划管理</a></p>
      <p><a href="checkpro.do" target="_self">查询计划</a></p>
      <p><a href="task.do" target="_self">我的任务</a></p>
      <p ><a href="../logout.do" target="_self">退出系统</a></p>
</div><!-- #EndLibraryItem --><div id="right">
        <p>您现在的位置 &gt;&gt; 计划管理 &gt;&gt; 新建计划</p>
                <h1>输入新计划信息</h1>

                <form id="form1" name="form1" method="post" action="addPlan.do">
                    <input type="hidden" name="task_status" value="${taskInfo.task.status}">
                    <input type="hidden" name="task_Id" value="${taskInfo.task.task_Id}">
          <p>计划名称：
            <label>
          <input name="plan_Name" type="text" size="50" />
          </label></p>
          <p>计划描述：
            <label>
            <textarea name="plan_Desc"></textarea>
            </label>
          </p>
            <p>开始时间：
              <label>
                <input name="begin_Date" type="text" size="16" />
              </label>
             <span class="marginleft1">结束时间：
              <label>
                <input name="end_Date" type="text" size="16" />
              </label></span>
          </p>
          <p>任务状态：
             未完成            </p>
          <p>是否反馈： 未反馈</p>
          <p>
              <label>
              <input name="Submit" type="reset" class="menu2" value="重置" />
              </label>
           &nbsp; 
              <label>
              <input name="Submit2" type="submit" class="menu1" value="提交" />
              </label>
           </p>
      </form>
      </div>
    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
