<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>计划信息</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script>
        $(function () {
            $("#delBtn").click(function () {
                //修改表单的提交路径
                $("#form1").attr("action","deletePlans.do");
                $("#form1").submit(); //提交表单
            })
        })
    </script>
</head>

<body><!-- #BeginLibraryItem "/Library/topbanner.lbi" -->
<c:if test="${!empty sessionScope.msg}">
    <script>
        alert('${msg}')
    </script>
    <c:remove var="msg" scope="session"></c:remove>
</c:if>
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
        <p>您现在的位置 &gt;&gt; 计划管理 &gt;&gt; 计划信息</p>
        <form id="form1" name="form1" method="post" action="newpro.jsp">
		<h1>任务详细信息:</h1>
            <input type="hidden" name="showTaskId" value="${taskInfo.task.task_Id}">
          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">任务名称</td>
              <td width="579" colspan="3">${taskInfo.task.task_Name}</td>
            </tr>
            <tr>
              <td class="tdcolor">任务描述</td>
              <td colspan="3">&nbsp;${taskInfo.task.task_Desc}</td>
            </tr>
            <tr>
              <td class="tdcolor">开始时间</td>
              <td width="579"><fmt:formatDate value="${taskInfo.task.begin_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
              <td width="579" class="tdcolor">结束时间</td>
              <td width="579"><p><fmt:formatDate value="${taskInfo.task.end_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></p>              </td>
            </tr>
            
            <tr>
              <td class="tdcolor">实施人</td>
              <td>${loginEmployee.employee_Name}</td>
              <td class="tdcolor">任务状态</td>
              <td>
               
                  <select name="status">
                    <option <c:if test="${taskInfo.task.status=='待实施'}">selected</c:if> value="0">待实施</option>
                    <option <c:if test="${taskInfo.task.status=='实施中'}">selected</c:if> value="1">实施中</option>
                    <option <c:if test="${taskInfo.task.status=='已完成'}">selected</c:if> value="2">已完成</option>
                  </select>              </td>
            </tr>
        </table>


		  <h1>计划信息：</h1>
		  <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
              <tr>
                  <td width="15%" class="tdcolor">计划名称</td>
                  <td width="15%" class="tdcolor">完成状态</td>
                  <td class="tdcolor">是否反馈</td>
                  <td class="tdcolor">开始时间</td>
                  <td class="tdcolor">结束时间</td>
                  <td class="tdcolor">&nbsp;</td>
              </tr>


              <c:forEach items="${taskInfo.plans}" var="plan" begin="${requestScope.start-1}" end="${requestScope.end-1}">
                  <tr>
                      <td><a href="feedback.do?planId=${plan.plan_Id}&task_Id=${taskInfo.task.task_Id}" target="_self">${plan.plan_Name}</a></td>
                      <td>${plan.status}</td>
                      <td>${plan.is_Feedback}</td>
                      <td><fmt:formatDate value="${plan.begin_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                      <td><fmt:formatDate value="${plan.end_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                      <td><label>
                          <c:if test="${plan.status eq '待实施'}">
                              <input type="checkbox" name="deleteIds" value="${plan.plan_Id}" />
                          </c:if>

                      </label></td>
                  </tr>
              </c:forEach>
              <tr>
              <td colspan="6" style="text-align: right">
                  <ul style="list-style: none">
                      <li style="display: inline;margin-left: 10px"><a href="taskview.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${sessionScope.pageBean.firstPage}">首页</a></li>
                      <c:if test="${sessionScope.pageBean.pageNo>1}">
                          <li style="display: inline;margin-left: 10px"><a href="taskview.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${sessionScope.pageBean.pageUp}">上一页</a></li>
                      </c:if>
                      <c:forEach begin="1" end="${sessionScope.pageBean.totalPage}" var="i">
                          <li style="display: inline;margin-left: 10px"> <a style="<c:if test='${sessionScope.pageBean.pageNo==i}'>color: red</c:if>" href="taskview.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${i}">${i}</a></li>
                      </c:forEach>
                      <c:if test="${sessionScope.pageBean.pageNo<sessionScope.pageBean.totalPage}">
                          <li style="display: inline;margin-left: 10px"><a href="taskview.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${sessionScope.pageBean.pageDown}">下一页</a></li>
                      </c:if>
                      <li style="display: inline;margin-left: 10px"><a href="taskview.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${sessionScope.pageBean.endPage}">尾页</a></li>
                  </ul>
              </td>
              </tr>
          </table>
		  <p>
              <c:choose>
                  <c:when test="${taskInfo.task.status eq'已完成'}">
                      <input name="Submit2" disabled type="submit" class="menu2" value="新建" />
                      <input id="delBtn" disabled name="Submit" type="button" onclick="return confirm('确认删除吗？')" class="menu1" value="删除" />
                  </c:when>
                  <c:when test="${taskInfo.task.status !='已完成'}">
                      <input name="Submit2"  type="submit" class="menu2" value="新建" />
                      <input id="delBtn"  name="Submit" type="button" onclick="return confirm('确认删除吗？')" class="menu1" value="删除" />
                  </c:when>
              </c:choose>

          </p>
      </form>
    </div>    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
