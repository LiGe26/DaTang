<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../commons/mytaglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>跟踪计划信息</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>
<script src="../js/jquery.js"></script>
<script>
    $(function () {
        $("#fankuiBtn").click(function () {
            //所有显示详情标签隐藏
            $(".desc").hide()
            //获得所有复选框选中的值
            $(".check-box:checked").each(function () {
                $("#desc"+$(this).val()).show();
            })
            return false;
        })
    })
</script>
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
        <p>您现在的位置 &gt;&gt; 跟踪任务 &gt;&gt; 跟踪计划信息</p>
		 <form id="form1" name="form1" method="post" action="finish.do">
		<h1>任务详细信息:</h1>
            <input type="hidden" name="task_Id" value="${taskInfo.task.task_Id}">
          <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">任务名称</td>
              <td width="579" colspan="3">${taskInfo.task.task_Name}</td>
            </tr>
            <tr>
              <td class="tdcolor">任务描述</td>
              <td colspan="3">${taskInfo.task.task_Desc}&nbsp;</td>
            </tr>
            <tr>
              <td class="tdcolor">开始时间</td>
              <td width="579"><fmt:formatDate value="${taskInfo.task.begin_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
              <td width="579" class="tdcolor">结束时间</td>
              <td width="579"><p><fmt:formatDate value="${taskInfo.task.end_Date}" pattern="yyyy-MM-dd"></fmt:formatDate>&nbsp;</p>              </td>
            </tr>
            
            <tr>
              <td class="tdcolor">实施人</td>
              <td>${taskInfo.task.implementor.reaL_Name}&nbsp;</td>
              <td class="tdcolor">任务状态</td>
              <td>
               
                  <select name="status">
                    <option <c:if test="${taskInfo.task.status=='实施中'}">selected</c:if> value="undone" >实施中</option>
                    <option <c:if test="${taskInfo.task.status=='已完成'}">selected</c:if> value="end">已完成</option>
                  </select>              </td>
            </tr>
        </table>
		<input name="Submit" type="submit" class="menu4" value="提交" />
		</form>
             <form id="form2" name="form1" method="post" action="">
		  <h1>计划信息：</h1>
		  <table width="700" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%" class="tdcolor">计划名称</td>
              <td width="15%" class="tdcolor">完成状态</td>
              <td class="tdcolor">是否反馈</td>
              <td class="tdcolor">结束时间</td>
              <td class="tdcolor">结束时间</td>
              <td class="tdcolor">&nbsp;</td>
            </tr>
              <c:forEach items="${taskInfo.plans}" var="plan" begin="${start-1}" end="${end-1}">
                  <tr>
                      <td>${plan.plan_Name}</td>
                      <td>${plan.status}</td>
                      <td>
                          <c:choose>
                              <c:when test="${plan.is_Feedback=='是'}">已反馈</c:when>
                              <c:when test="${plan.is_Feedback=='否'}">未反馈</c:when>
                          </c:choose>
                      </td>
                      <td><fmt:formatDate value="${plan.begin_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                      <td><fmt:formatDate value="${plan.end_Date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                      <td><label>
                          <c:if test="${plan.is_Feedback=='是'}">
                              <input class="check-box"  type="checkbox" name="radiobutton" value="${i.index}" />
                          </c:if>
                      </label></td>
                  </tr>
                  <tr>
                      <td class="desc" colspan="6" id="desc${i.index}" style="display: none">${plan.plan_Desc}</td>
                  </tr>

              </c:forEach>
              <tr>
                  <td colspan="6" style="text-align: right">
                      <ul style="list-style: none">
                          <li style="display: inline;margin-left: 10px"><a href="programinten.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${sessionScope.pageBean.firstPage}">首页</a></li>
                          <c:if test="${sessionScope.pageBean.pageNo>1}">
                              <li style="display: inline;margin-left: 10px"><a href="programinten.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${sessionScope.pageBean.pageUp}">上一页</a></li>
                          </c:if>
                          <c:forEach begin="1" end="${sessionScope.pageBean.totalPage}" var="i">
                              <li style="display: inline;margin-left: 10px"> <a style="<c:if test='${sessionScope.pageBean.pageNo==i}'>color: red</c:if>" href="programinten.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${i}">${i}</a></li>
                          </c:forEach>
                          <c:if test="${sessionScope.pageBean.pageNo<sessionScope.pageBean.totalPage}">
                              <li style="display: inline;margin-left: 10px"><a href="programinten.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${sessionScope.pageBean.pageDown}">下一页</a></li>
                          </c:if>
                          <li style="display: inline;margin-left: 10px"><a href="programinten.do?queryTaskId=${taskInfo.task.task_Id}&planInfoPageNo=${sessionScope.pageBean.endPage}">尾页</a></li>
                      </ul>
                  </td>
              </tr>

          </table>
		  <p>
              <label>
              <input id="fankuiBtn" name="Submit" type="submit" class="menu3" value="反馈信息" />
            </label>
          </p>
      </form>
      </div>
    </td></tr>
</table>
<!-- #BeginLibraryItem "/Library/copyright.lbi" --><div class="copyright">COPYRIGHT 2007 DATANG TELECOM TECHNOLOGY CO.LTD 京ICP备06071639号</div><!-- #EndLibraryItem --><!-- #BeginLibraryItem "/Library/bottom.lbi" --><div id="bottom"><img src="../images/button.jpg" width="1002" height="17" /></div><!-- #EndLibraryItem -->
</body>
</html>
