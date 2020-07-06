<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../commons/mytaglib.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>分配人员</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>

<body><!-- #BeginLibraryItem "/Library/topbanner.lbi" -->
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<div id="logo"><img src="../images/top.jpg" width="1002" height="258" /></div>
<!-- #EndLibraryItem --><table width="1002" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="rightimg"><!-- #BeginLibraryItem "/Library/left3.lbi" -->
      <script>
        function fanhui() {
          location.replace('empdistribute.do')
        }
      </script>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<div id="left2">
      <p><a href="allEmployees.do" target="_self" >用户管理</a></p>
      <p><a href="yuanGong.do" target="_self">员工管理</a></p>
      <p><a href="empdistribute.do" target="_self">分配人员</a></p>
      <p ><a href="../logout.do" target="_self">退出系统</a></p>
</div><!-- #EndLibraryItem --><div id="right">
        <p>您现在的位置 &gt;&gt; 分配人员</p>
        <h1>用户详细信息:</h1>
		<form id="form0" name="form1" method="post" action="updateEmployee.do">
          <input type="hidden" name="empId" value="${sessionScope.queryEmployee.employee_Id}">
        <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
          <tr>
            <td class="tdcolor">用户名称</td>
            <td>${sessionScope.queryEmployee.employee_Name}</td>
          </tr>
          <tr>
            <td width="15%" class="tdcolor">真实姓名</td>
            <td>${sessionScope.queryEmployee.reaL_Name}</td>
          </tr>
          <tr>
            <td class="tdcolor">行业角色</td>
            <td>${sessionScope.maperRole.get(sessionScope.queryEmployee.role.role_Id)}</td>
          </tr>
          <tr>
            <td class="tdcolor">性&nbsp;&nbsp;&nbsp; 别</td>
            <td>${sessionScope.queryEmployee.sex}</td>
          </tr>
          <tr>
            <td class="tdcolor">入职时间</td>
            <td><fmt:formatDate  value="${sessionScope.queryEmployee.enrolldate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
          </tr>
          <tr>
            <td class="tdcolor">职位信息</td>
            <td>${sessionScope.queryEmployee.duty}</td>
          </tr>
          <tr>
            <td class="tdcolor">出生年月</td>
            <td><fmt:formatDate  value="${sessionScope.queryEmployee.birthday}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
          </tr>
          <tr>
            <td class="tdcolor">学历信息</td>
            <td>&nbsp;${sessionScope.queryEmployee.education}</td>
          </tr>
          <tr>
            <td class="tdcolor">专业信息</td>
            <td>&nbsp;${sessionScope.queryEmployee.major}</td>
          </tr>
          <tr>
            <td class="tdcolor">行业经验</td>
            <td>&nbsp;${sessionScope.queryEmployee.experience}</td>
          </tr>
          <tr>
            <td class="tdcolor">上级主管</td>
            <td><label>
              <select name="parent_id">
                <option value="0">待分配</option>
                <c:forEach items="${sessionScope.allDents}" var="dents">
                <option   <c:if test="${sessionScope.queryEmployee.parent.employee_Id==dents.employee_Id}">selected</c:if> value="${dents.employee_Id}" >${dents.reaL_Name}</option>
                </c:forEach>


              </select>
            </label></td>
          </tr>
        </table>
        <p>&nbsp;</p>

          <p>
            <input onclick="fanhui()" name="Submit" type="reset" class="menu2" value="取消"  />

            <label>
            <input name="Submit2" type="submit" onclick="return confirm('确认修改吗？')" class="menu1" value="提交" />
            </label>
          </p>
      </form>
    </div>    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
