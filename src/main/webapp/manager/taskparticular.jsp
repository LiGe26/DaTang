<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>任务详细信息</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
  <script src="../js/jquery.js"></script>
  <script>
    $(function () {
      $("#xiangxi").click(function () {
          if($(":radio:checked").length==0){
            alert('请先选中单选按钮查看详情！')
            return false;
          }
      })
    })
  </script>
</head>

<body><!-- #BeginLibraryItem "/Library/topbanner.lbi" -->
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<div id="logo"><img src="../images/top.jpg" width="1002" height="258" /></div>
<!-- #EndLibraryItem --><table width="1002" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="rightimg"><!-- #BeginLibraryItem "/Library/left.lbi" --><div id="left">
      <p><a href="taskview.do" target="_self">查看任务</a></p>
      <p><a href="task.do" target="_self">制定任务</a></p>
      <p><a href="taskundone.do" target="_self">调整任务</a></p>
      <p><a href="intendance.do" target="_self">跟踪任务</a></p>
      <p><a href="checkper.do" target="_self">查看人员</a></p>
      <p><a href="../logout.do" target="_self">退出系统</a></p>
</div><!-- #EndLibraryItem --><div id="right">
        <p>您现在的位置 &gt;&gt; 查看任务 &gt;&gt; 任务详细信息</p>
        <h1>任务详细信息:</h1>

          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">任务名称</td>
              <td width="579" colspan="5">${taskInfo.task.task_Name}</td>
            </tr>
            <tr>
              <td class="tdcolor">任务描述</td>
              <td colspan="5">${taskInfo.task.task_Desc}</td>
            </tr>
            <tr>
              <td class="tdcolor">开始时间</td>
              <td width="15%"><fmt:formatDate value="${taskInfo.task.begin_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
              <td width="20%" class="tdcolor">结束时间</td>
              <td width="50%" colspan="3"><p>&nbsp;<fmt:formatDate value="${taskInfo.task.end_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></p>              </td>
            </tr>
            <tr>
              <td class="tdcolor">实际开始时间</td>
              <td>&nbsp;<fmt:formatDate value="${taskInfo.task.real_Begin_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
              <td class="tdcolor">实际结束时间</td>
              <td colspan="3"><fmt:formatDate value="${taskInfo.task.real_End_Date}" pattern="yyyy-MM-dd"></fmt:formatDate>&nbsp;</td>
            </tr>
            <tr>
              <td class="tdcolor">实施人</td>
              <td>${taskInfo.task.implementor.reaL_Name}&nbsp;</td>
              <td class="tdcolor">任务状态</td>
              <td>${taskInfo.task.status}</td>
              <td class="tdcolor">计划数目</td>
              <td>${sessionScope.pageBean.list.get(0).get(taskInfo.task.task_Id).plans.size()}</td>
            </tr>

        </table>
	    <form id="form1" name="form1" method="post" action="program.do">

		  <h1>实施计划：</h1>
		  <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">

            <tr>
              <td width="15%" class="tdcolor">计划名称</td>
              <td width="15%" class="tdcolor">完成状态</td>
              <td width="20%" class="tdcolor">是否反馈</td>
              <td class="tdcolor">结束时间</td>
              <td class="tdcolor">结束时间</td>
              <td class="tdcolor">&nbsp;</td>
            </tr>
            <c:forEach items="${taskInfo.plans}" var="plan" begin="${start-1}" end="${end-1}">
              <tr>
                <td>${plan.plan_Name}</td>
                <td>${plan.status}</td>
                <td>${plan.is_Feedback}</td>
                <td><fmt:formatDate value="${plan.begin_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                <td><fmt:formatDate value="${plan.end_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                <td><label>
                  <input type="radio" name="queryPlanId" value="${plan.plan_Id}" />
                </label></td>
              </tr>
            </c:forEach>
            <tr>
              <td colspan="6" style="text-align: right">
                <ul style="list-style: none">
                  <li style="display: inline;margin-left: 10px"><a href="taskparticular.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${sessionScope.pageBean.firstPage}">首页</a></li>
                  <c:if test="${sessionScope.pageBean.pageNo>1}">
                    <li style="display: inline;margin-left: 10px"><a href="taskparticular.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${sessionScope.pageBean.pageUp}">上一页</a></li>
                  </c:if>
                  <c:forEach begin="1" end="${sessionScope.pageBean.totalPage}" var="i">
                    <li style="display: inline;margin-left: 10px"> <a style="<c:if test='${sessionScope.pageBean.pageNo==i}'>color: red</c:if>" href="taskparticular.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${i}">${i}</a></li>
                  </c:forEach>
                  <c:if test="${sessionScope.pageBean.pageNo<sessionScope.pageBean.totalPage}">
                    <li style="display: inline;margin-left: 10px"><a href="taskparticular.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${sessionScope.pageBean.pageDown}">下一页</a></li>
                  </c:if>
                  <li style="display: inline;margin-left: 10px"><a href="taskparticular.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${sessionScope.pageBean.endPage}">尾页</a></li>
                </ul>
              </td>
            </tr>


          </table>
		  <p>
              <label>
              <input id="xiangxi" name="Submit" type="submit" class="menu3" value="详细信息" />
            </label>
          </p>
      </form>
      </div>
    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
