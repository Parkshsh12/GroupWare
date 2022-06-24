<%@page import="mvc.model.NoticeDTO"%>
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
<link rel="stylesheet" href="<c:url value="/resources/css/notice_update.css"/>">
</head>
<body>
<%
	NoticeDTO notice = (NoticeDTO) request.getAttribute("noticeView");
	int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
	String search_item = request.getParameter("search_item");
	String text = request.getParameter("text");
	int seq = Integer.parseInt(request.getParameter("seq"));
%>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="board_add">
		<form action="/notice_update_submit.do?seq=<%=seq%>&pageNum=<%=pageNum%>" name="boardAdd" method="post">
			<div class="form-item">
				<label class="form-item">제목</label> <input class="title"
					name="title" type="text" placeholder="제목을 입력하세요." value="<%=notice.getTitle() %>" required>
			</div>
			<div class="form-item">
				<label class="form-item">내용</label>
				<textarea name="content" cols="50" rows="8" placeholder="내용을 입력하세요."
					required><%=notice.getContent() %></textarea>
			</div>
			<div class="button">
				<input type="submit" class="btn btn-primary" value="수정"> <a
					href="/notice_delete.do?seq=<%=seq%>" class="btn btn-primary">삭제</a>
					<input type="reset" class="btn btn-primary" value="취소 ">
			</div>
		</form>
	</div>
</body>
</html>