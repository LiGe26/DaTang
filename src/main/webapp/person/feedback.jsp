<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>反馈信息</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
    <script src="../js/jquery.js"></script>
    <script>
        $(function () {
            $("#fanhui").click(function () {
                history.back();
            })
        })
    </script>
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
        <p>您现在的位置 &gt;&gt; 计划管理 &gt;&gt; 反馈信息</p>
        <form id="form1" name="form1" method="post" action="updatePlan.do">
		<h1>输入反馈信息:</h1>
            <input type="hidden" value="${requestScope.upPlan.task_Id}" name="task_Id">
            <input type="hidden" value="${requestScope.upPlan.plan_Id}" name="plan_Id">
          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">计划名称</td>
              <td width="579" colspan="3">${upPlan.plan_Name}</td>
            </tr>
            <tr>
              <td class="tdcolor">计划描述</td>
              <td colspan="3">${upPlan.plan_Desc}</td>
            </tr>
            <tr>
              <td class="tdcolor">开始时间</td>
              <td width="579"><fmt:formatDate value="${requestScope.upPlan.begin_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
              <td width="150" class="tdcolor">结束时间</td>
              <td width="579"><p><fmt:formatDate value="${requestScope.upPlan.end_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></p>              </td>
            </tr>
            
            <tr>
              <td class="tdcolor">计划状态</td>
              <td colspan="3">
               
                  <select name="status">

                    <option <c:if test="${requestScope.upPlan.status=='未实施'}">selected</c:if> value="0">未实施</option>
                    <option <c:if test="${requestScope.upPlan.status=='实施中'}">selected</c:if> value="1">实施中</option>
                    <option <c:if test="${requestScope.upPlan.status=='已完成'}">selected</c:if> value="2">已完成</option>
                  </select>              </td>
            </tr>
            <tr>
              <td class="tdcolor">是否反馈</td>
              <td colspan="3"><select name="is_Feedback">
                <option value="no" <c:if test="${requestScope.upPlan.is_Feedback=='否'}">selected</c:if>>未反馈</option>
                <option value="yes" <c:if test="${requestScope.upPlan.is_Feedback=='是'}">selected</c:if>>已反馈</option>
              </select></td>
            </tr>
            <tr>
              <td class="tdcolor">反馈信息</td>
              <td colspan="3"><label>
                <textarea name="feedback_Info" cols="60" rows="4">${requestScope.upPlan.feedback_Info}</textarea>
              </label></td>
            </tr>
        </table>


		  <p>

              		<input name="Submit" type="reset" id="fanhui"  class="menu2" value="返回" />

					<input name="Submit" <c:if test="${requestScope.upPlan.status=='已完成'}">disabled</c:if>  type="submit" class="menu1" value="提交" />
          </p>
      </form>
      </div>
    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
