<%@ page import="mvc.model.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/notice_noticeNum.css"/>">
</head>
<body>
<%
	NoticeDTO notice = (NoticeDTO) request.getAttribute("noticeView");
	int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
	String number = (String) session.getAttribute("number");
	String name = (String) session.getAttribute("name");
	String search_item = request.getParameter("search_item");
	String text = request.getParameter("text");
%>
<%=
	notice.getSeq()
%>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="notice_noticeNum">
		<div class="title_container">
			<h1><%=notice.getTitle() %></h1>
			<div class="title_items">
				<div class="title_item">
					<span class="title">작성자</span> <span><%=notice.getName() %></span>
				</div>
				<div class="title_item">
					<span class="title">작성일자</span> <span><%=(notice.getB_date()).substring(0,19) %></span>
					<span class="title">조회수</span> <span><%=notice.getHit() %></span>
				</div>
			</div>
			<hr>
			<div>
				<span><%=notice.getContent() %></span>
			</div>
		</div>
		<div class="btn">
			<a href="/notice_main.do?pageNum=<%=pageNum%>" class="btn btn-primary">되돌아가기</a> 
			<%
				if(number.equals(notice.getNumber())){	
			%>
			<a href="/notice_update.do?pageNum=<%=pageNum%>&search_item=<%=search_item%>&text=<%=text%>&seq=<%=notice.getSeq() %>" class="admin btn btn-primary">수정</a>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>