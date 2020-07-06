<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../commons/mytaglib.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>用户管理</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>

<body><!-- #BeginLibraryItem "/Library/topbanner.lbi" -->
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<div id="logo"><img src="../images/top.jpg" width="1002" height="258" /></div>
<!-- #EndLibraryItem --><table width="1002" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="rightimg"><!-- #BeginLibraryItem "/Library/left3.lbi" -->
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<div id="left2">
      <p><a href="allEmployees.do" target="_self" >用户管理</a></p>
      <p><a href="yuanGong.do" target="_self">员工管理</a></p>
      <p><a href="empdistribute.do" target="_self">分配人员</a></p>
      <p ><a href="../logout.do" target="_self">退出系统</a></p>
</div><!-- #EndLibraryItem --><div id="right">
        <p>您现在的位置 &gt;&gt; 员工管理 &gt;&gt; 员工列表</p>
        <h1>用户详细信息:</h1>
	  
	    <form id="form1" name="form1" method="post" action="deleteEmployee.do">
          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">用户名称</td>
              <td class="tdcolor">用户密码</td>
              <td class="tdcolor">用户角色</td>
              <td class="tdcolor">真实姓名</td>
              <td class="tdcolor">入职时间</td>
              <td class="tdcolor">职位信息</td>
              <td class="tdcolor">&nbsp;</td>
            </tr>
            <c:forEach items="${sessionScope.pageBean.list}" var="emp">
              <tr>
                <td>${emp.employee_Name}</td>
                <td>${emp.password}</td>
                <td>${sessionScope.maperRole.get(emp.role.role_Id)}</td>
                <td>${emp.reaL_Name}</td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${emp.enrolldate}"  ></fmt:formatDate></td>
                <td>${emp.duty}</td>
                <td><input type="radio" value="${emp.employee_Id}" name="deleteId"></td>
              </tr>
            </c:forEach>
              <tr>
                  <td colspan="6" style="text-align: right">
                      <ul style="list-style: none">
                          <li style="display: inline;margin-left: 10px"><a href="yuanGong.do?pageNo=${sessionScope.pageBean.firstPage}">首页</a></li>
                          <c:if test="${sessionScope.pageBean.pageNo>1}">
                              <li style="display: inline;margin-left: 10px"><a href="yuanGong.do?pageNo=${sessionScope.pageBean.pageUp}">上一页</a></li>
                          </c:if>
                          <c:forEach begin="1" end="${sessionScope.pageBean.totalPage}" var="i">
                              <li style="display: inline;margin-left: 10px"> <a style="<c:if test='${sessionScope.pageBean.pageNo==i}'>color: red</c:if>" href="yuanGong.do?pageNo=${i}">${i}</a></li>
                          </c:forEach>
                          <c:if test="${sessionScope.pageBean.pageNo<sessionScope.pageBean.totalPage}">
                              <li style="display: inline;margin-left: 10px"><a href="yuanGong.do?pageNo=${sessionScope.pageBean.pageDown}">下一页</a></li>
                          </c:if>
                          <li style="display: inline;margin-left: 10px"><a href="yuanGong.do?pageNo=${sessionScope.pageBean.endPage}">尾页</a></li>
                      </ul>
                  </td>
              </tr>

          </table>
	      <p>
            <label>
            <input name="Submit" type="submit" onclick="return confirm('确认删除选中的员工吗？')" class="menu3"  value="删除人员"  />
            </label>
            <label></label>
	      </p>
        </form>
	    <p>&nbsp;</p>
</div></td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
