<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>查询计划</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
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
        <p>您现在的位置 &gt;&gt; 查询计划 &gt;&gt; 查询</p>
        <form id="form1" name="form1" method="post" action="checkpro.do">
            <input type="hidden" name="flag" value="1">
          <h1>查询信息:</h1>
          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">计划名称</td>
              <td width="579"><input type="text" name="search_Plan_Name" value="${sessionScope.dto.search_Plan_Name}"></td>
            </tr>
            <tr>
              <td class="tdcolor">所属任务</td>
              <td><select name="search_Task_Id">
                  <option value="0">请选择</option>
                  <c:forEach items="${mapperTask}" var="task">
                      <option <c:if test="${sessionScope.dto.search_Task_Id==task.key}">selected</c:if> value="${task.key}">${task.value.task_Name}</option>
                  </c:forEach>
                            </select></td>
            </tr>
            
            <tr>
              <td class="tdcolor">开始时间</td>
              <td>从
                <input name="search_Begin_Date1" type="text" value="<fmt:formatDate value="${sessionScope.dto.search_Begin_Date1}" pattern="yyyy-MM-dd"></fmt:formatDate>" size="15" />
              到
              <input name="search_Begin_Date2" type="text" value="<fmt:formatDate value="${sessionScope.dto.search_Begin_Date2}" pattern="yyyy-MM-dd"></fmt:formatDate>" size="15" />              </td>
            </tr>
            <tr>
              <td class="tdcolor">结束时间</td>
              <td>从
                <input name="search_End_Date1" value="<fmt:formatDate value="${sessionScope.dto.search_End_Date1}" pattern="yyyy-MM-dd"></fmt:formatDate>" type="text" size="15" />
到
<input name="search_End_Date2" type="text" value="<fmt:formatDate value="${sessionScope.dto.search_End_Date2}" pattern="yyyy-MM-dd"></fmt:formatDate>" size="15" /></td>
            </tr>
            <tr>
              <td class="tdcolor">是否反馈(是/否)</td>
              <td><label>
                <input name="search_Is_Feedback" value="${sessionScope.dto.search_Is_Feedback}" type="text" size="15" />
              </label></td>
            </tr>
          </table>
          <p>
            <input name="Submit" type="submit" class="menu3" value="查询计划" />
          </p>
		  
		  		  <h1>计划信息：</h1>
		  <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">计划名称</td>
              <td width="15%" class="tdcolor">所属任务</td>
              <td class="tdcolor">开始时间</td>
              <td class="tdcolor">结束时间</td>
              <td class="tdcolor">计划状态</td>
              <td class="tdcolor">是否反馈</td>
            </tr>
           <c:forEach items="${sessionScope.pageBean.list}" var="plan">
               <tr>
                   <td><a href="feedback.do?planId=${plan.plan_Id}&task_Id=${plan.task_Id}" target="_self">${plan.plan_Name}</a></td>
                   <td>${mapperTask.get(plan.task_Id).task_Name}</td>
                   <td><fmt:formatDate value="${plan.begin_Date}" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
                   <td><fmt:formatDate value="${plan.end_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                   <td>${plan.status}</td>
                   <td><label><c:choose>
                       <c:when test="${plan.is_Feedback=='是'}">已反馈</c:when>
                       <c:when test="${plan.is_Feedback=='否'}">未反馈</c:when>
                   </c:choose></label></td>
               </tr>
           </c:forEach>
              <tr>

                  <td colspan="6" style="text-align: right">
                      <ul style="list-style: none">
                          <li style="display: inline;margin-left: 10px"><a href="checkpro.do?flag=2&pageNo=${sessionScope.pageBean.firstPage}">首页</a></li>
                          <c:if test="${sessionScope.pageBean.pageNo>1}">
                              <li style="display: inline;margin-left: 10px"><a href="checkpro.do?flag=2&pageNo=${sessionScope.pageBean.pageUp}">上一页</a></li>
                          </c:if>
                          <c:forEach begin="1" end="${sessionScope.pageBean.totalPage}" var="i">
                              <li style="display: inline;margin-left: 10px"> <a style="<c:if test='${sessionScope.pageBean.pageNo==i}'>color: red</c:if>" href="checkpro.do?flag=2&pageNo=${i}">${i}</a></li>
                          </c:forEach>
                          <c:if test="${sessionScope.pageBean.pageNo<sessionScope.pageBean.totalPage}">
                              <li style="display: inline;margin-left: 10px"><a href="checkpro.do?flag=2&pageNo=${sessionScope.pageBean.pageDown}">下一页</a></li>
                          </c:if>
                          <li style="display: inline;margin-left: 10px"><a href="checkpro.do?flag=2&pageNo=${sessionScope.pageBean.endPage}">尾页</a></li>
                      </ul>
                  </td>
              </tr>

          </table>
        </form>
      </div>    </td>
  </tr>
</table>
<!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem --></body>
</html>
