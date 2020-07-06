<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="commons/mytaglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>登录页面</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="logo"><img src="images/logo.jpg" alt="大唐电信首页" width="1002" height="392" /></div>
<div class="login" id="middle">
    <c:if test="${!empty msg}">
        <script type="text/javascript">
            alert('${msg}');
        </script>

    </c:if>
    <c:remove var="msg"  ></c:remove>
    <spf:form method="post" name="form001" id="form001" action="login.do" modelAttribute="loadForm">
    <p>
      <label>
      用户名：   <spf:input path="employee.employee_Name"></spf:input>
<%--          <input name="username" type="text" /> --%>
    </label></p>
    <p>  密 码：     
      <label>
<%--    <input type="text" name="password" />--%>
          <spf:password  path="employee.password"></spf:password>
    </label></p>
    <p>角 色：
      <label>
        <spf:select path="employee.role.role_Id">
            <spf:options items="${sessionScope.roles}" itemValue="role_Id" itemLabel="role_Name"></spf:options>
        </spf:select>
<%--      <select name="admin">--%>
<%--        <option value="manager" selected="selected">主管</option>--%>
<%--        <option value="admin">系统管理员</option>--%>
<%--        <option value="employees">员工</option>--%>
<%--      </select>--%>
      </label>
    </p>
    <p>
      <label class="left007-bg">
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
      <input name="Submit" type="submit" class="menu1" value="提交" />
      </label>
    </p>
      </spf:form>
</div>
<div id="nutton"><img src="images/button.jpg" width="1002" height="17" /></div>
</body>
</html>
