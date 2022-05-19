<%@page import="mvc.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String sessionNumber = (String)session.getAttribute("number");
	String pageNum = request.getParameter("pageNum");
	BoardDTO board = (BoardDTO)request.getAttribute("board");
	System.out.println(sessionNumber);
	System.out.println(board.getNumber());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/board_boardNum.css"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="board_boardNum">
		<div class="title_container">
			<h1><%=board.getTitle() %></h1>
			<div class="title_items">
				<div class="title_item">
					<span class="title">작성자</span> <span><%=board.getName() %></span>
				</div>
				<div class="title_item">
					<span class="title">작성일자</span> <span><%=board.getB_date() %></span>
				</div>
			</div>
			<hr>
			<div>
				<span><%=board.getContent() %></span>
			</div>
		</div>
		<div class="btn">
			<a href="/board_main.do?pageNum=<%=pageNum %>" class="btn btn-primary">되돌아가기</a>
			<%
				if(sessionNumber.equals(board.getNumber())){
			%> 
			<a href="/board_updateAction.do?num=<%=board.getSeq() %>&pageNum=<%=pageNum %>" class="admin btn btn-primary">수정</a>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>