
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../commons/mytaglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>未实施任务</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
    <script src="../js/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#delBtn").click(function () {
                var flag= confirm('确认删除吗？')
                if(flag){
                    //修改表单的提交路径
                    $("#form1").attr("action","deleteTask.do");
                    $("#form1").submit(); //提交表单
                }


            })
        })
    </script>
</head>

<c:if test="${!empty msg}">
    <script>
        alert('${msg}');
    </script>
</c:if>
<c:remove var="msg" scope="session"></c:remove>
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
        <p>您现在的位置 &gt;&gt; 查看任务 &gt;&gt; 未实施任务信息</p>
        <h1>未实施任务信息:</h1>
        <form id="form1" name="form1" method="post" action="taskundone.jsp">
          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor"><a href="#" target="_self">任务名称</a></td>
              <td class="tdcolor"><a href="#" target="_self">实施人</a></td>
              <td class="tdcolor"><a href="#" target="_self">开始时间</a></td>
              <td class="tdcolor"><a href="#" target="_self">结束时间</a></td>
              <td class="tdcolor"><a href="#" target="_self">任务状态</a></td>
              <td class="tdcolor">&nbsp;</td>
            </tr>
            <c:forEach items="${sessionScope.pageBean.list}" var="task">
              <tr>
                <td><a href="adjust.do?taskId=${task.task_Id}">${task.task_Name}</a></td>
                <td>${task.implementor.reaL_Name}</td>
                <td><fmt:formatDate value="${task.begin_Date}" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
                <td><fmt:formatDate value="${task.end_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                <td>${task.status}</td>
                <td><label>
                  <input type="checkbox" name="deleteTaskIds" value="${task.task_Id}" />
                </label></td>
              </tr>

            </c:forEach>
              <tr>
              <td colspan="6" style="text-align: right">
                  <ul style="list-style: none">
                      <li style="display: inline;margin-left: 10px"><a href="taskundone.do?pageNo=${sessionScope.pageBean.firstPage}">首页</a></li>
                      <c:if test="${sessionScope.pageBean.pageNo>1}">
                          <li style="display: inline;margin-left: 10px"><a href="taskundone.do?pageNo=${sessionScope.pageBean.pageUp}">上一页</a></li>
                      </c:if>
                      <c:forEach begin="1" end="${sessionScope.pageBean.totalPage}" var="i">
                          <li style="display: inline;margin-left: 10px"> <a style="<c:if test='${sessionScope.pageBean.pageNo==i}'>color: red</c:if>" href="taskundone.do?pageNo=${i}">${i}</a></li>
                      </c:forEach>
                      <c:if test="${sessionScope.pageBean.pageNo<sessionScope.pageBean.totalPage}">
                          <li style="display: inline;margin-left: 10px"><a href="taskundone.do?pageNo=${sessionScope.pageBean.pageDown}">下一页</a></li>
                      </c:if>
                      <li style="display: inline;margin-left: 10px"><a href="taskundone.do?pageNo=${sessionScope.pageBean.endPage}">尾页</a></li>
                  </ul>
              </td>
             </tr>
          </table>
            <p>
            <label>
            <input name="Submit" id="delBtn" type="submit" class="menu4" value="删除"  />
            </label>
          </p>
      </form>
    </div>    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
