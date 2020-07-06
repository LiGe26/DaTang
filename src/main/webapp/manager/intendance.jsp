
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../commons/mytaglib.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>跟踪任务</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>


<body><!-- #BeginLibraryItem "/Library/topbanner.lbi" -->
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<c:if test="${!empty sessionScope.msg}">
    <script>
        alert('${msg}')
    </script>
    <c:remove var="msg" scope="session"></c:remove>
</c:if>
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
        <p>您现在的位置 &gt;&gt; 跟踪任务 &gt;&gt; 跟踪任务信息</p>
        <h1>跟踪任务:</h1>
        <form id="form1" name="form1" method="post" action="programinten.do">
          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">任务名称</td>
              <td class="tdcolor">实施人</td>
              <td class="tdcolor">开始时间</td>
              <td class="tdcolor">结束时间</td>
              <td class="tdcolor">任务状态</td>
              <td class="tdcolor">&nbsp;</td>
            </tr>
            <c:forEach items="${sessionScope.pageBean.list.get(0)}" var="tInfo">
              <tr>
                <td>${tInfo.value.task.task_Name}</td>
                <td>${tInfo.value.task.implementor.reaL_Name}</td>
                <td><fmt:formatDate value="${tInfo.value.task.begin_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                <td><fmt:formatDate value="${tInfo.value.task.end_Date}" pattern="yyyy-MM-dd"></fmt:formatDate>&nbsp;</td>
                <td>${tInfo.value.task.status}</td>
                <td><label>
                  <input type="radio" name="queryTaskId" value="${tInfo.value.task.task_Id}" />
                </label></td>
              </tr>
            </c:forEach>
              <tr>
                  <td colspan="6" style="text-align: right">
                      <ul style="list-style: none">
                          <li style="display: inline;margin-left: 10px"><a href="intendance.do?pageNo=${sessionScope.pageBean.firstPage}">首页</a></li>
                          <c:if test="${sessionScope.pageBean.pageNo>1}">
                              <li style="display: inline;margin-left: 10px"><a href="intendance.do?pageNo=${sessionScope.pageBean.pageUp}">上一页</a></li>
                          </c:if>
                          <c:forEach begin="1" end="${sessionScope.pageBean.totalPage}" var="i">
                              <li style="display: inline;margin-left: 10px"> <a style="<c:if test='${sessionScope.pageBean.pageNo==i}'>color: red</c:if>" href="intendance.do?&pageNo=${i}">${i}</a></li>
                          </c:forEach>
                          <c:if test="${sessionScope.pageBean.pageNo<sessionScope.pageBean.totalPage}">
                              <li style="display: inline;margin-left: 10px"><a href="intendance.do?&pageNo=${sessionScope.pageBean.pageDown}">下一页</a></li>
                          </c:if>
                          <li style="display: inline;margin-left: 10px"><a href="intendance.do?pageNo=${sessionScope.pageBean.endPage}">尾页</a></li>
                      </ul>
                  </td>
              </tr>

          </table>
    <p>
            <label>
            <input name="Submit" type="submit" class="menu3" value="计划信息" />
            </label>
          </p>
      </form>
    </div>    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
