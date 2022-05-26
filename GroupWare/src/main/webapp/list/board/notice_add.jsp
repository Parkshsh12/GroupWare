<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/resources/css/notice_add.css"/>">
</head>
<body>
<%
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	String search_item = request.getParameter("search_item");
	String text = request.getParameter("text");
	String number = (String) session.getAttribute("number");
	String name = (String) session.getAttribute("name");
%>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="board_add">
		<form action="/notice_add_submit.do?pageNum=<%=pageNum%>&search_item=<%=search_item%>&text=<%=text%>&number=<%=number%>&name=<%=name%>" name="boardAdd" method="post">
			<div class="form-items">
				<label class="form-item col-sm-2">제목</label> <input class="title"
					name="title" type="text" placeholder="제목을 입력하세요." required>
			</div>
			<div class="form-items">
				<label class="form-item col-sm-2">내용</label>
				<textarea name="content" cols="50" rows="8" placeholder="내용을 입력하세요."
					required></textarea>
			</div>
			<div class="form-items aaa">
				<label class="form-item col-sm-2">첨부파일</label>
				<div>
					<input class="title form-control" type="file" name="file">
				</div>
			</div>
			<div class="button">
				<input type="submit" class="btn btn-primary" value="등록 ">
				<a class="btn btn-primary" href="/notice_main.do?pageNum=<%=pageNum%>&search_item=<%=search_item%>&text=<%=text%>&number=<%=number%>&name=<%=name%>">취소</a>
			</div>
		</form>
	</div>
</body>
</html>