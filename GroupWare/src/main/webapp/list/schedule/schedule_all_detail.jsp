<%@page import="mvc.model.CalendarDTO"%>
<%@page import="mvc.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	CalendarDTO calendar = (CalendarDTO)request.getAttribute("schedule");
	String number = (String)session.getAttribute("number");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/board_boardNum.css?v=3"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="board_boardNum">
		<div class="title_container">
			<h1><%=calendar.getC_title()%></h1>
			<div class="title_items">
				<div class="title_item">
					<span class="title">작성자</span> <span><%=calendar.getName() %></span>
					<span class="title">부서</span> <span><%=calendar.getDepartment() %></span>
				</div>
				<div class="title_item">
					<span class="title">일정 기간</span> <span><%=calendar.getStart_date()%> ~ <%=calendar.getEnd_date() %></span>
				</div>
			</div>
			<hr>
			<div>
				<span><%=calendar.getC_content() %></span>
			</div>
		</div>
		<div class="btn">
			<a href="<c:url value="/scheduleAllAction.do"/>" class="btn btn-primary">되돌아가기</a>
			<%
				if(number.equals(calendar.getNumber())){
			%> 
			<c:set var="seq" value="<%=calendar.getSeq() %>"/>
			<a href="<c:url value="/scheduleDelete.do?number=${number}&seq=${seq}"/>" class="admin btn btn-primary" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>