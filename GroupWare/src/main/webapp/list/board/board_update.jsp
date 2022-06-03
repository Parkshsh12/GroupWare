<%@page import="mvc.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	int pageNum = ((Integer)request.getAttribute("pageNum")).intValue();
	BoardDTO board = (BoardDTO)request.getAttribute("board");
	System.out.println(pageNum);
	System.out.println(board.getNumber());
	System.out.println(board.getName());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/board_update.css?v=1"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="board_add">
		<form name="boardAdd" method="post" action="/board_update.do?pageNum=<%=pageNum%>&num=<%=board.getSeq()%>">
			<div class="form-item">
				<label class="form-item">제목</label> <input class="title"
					name="subject" type="text" placeholder="제목을 입력하세요." value="<%=board.getTitle() %>" required>
			</div>
			<div class="form-item">
				<label class="form-item">이름</label> <input class="title"
					name="name" type="text" placeholder="이름을 입력하세요." value="<%=board.getName() %>" disabled="disabled">
			</div>
			<div class="form-item">
				<label class="form-item">내용</label>
				<textarea name="content" cols="50" rows="8" placeholder="내용을 입력하세요."
					required><%=board.getContent() %></textarea>
			</div>
			<div class="button">
				<input type="submit" class="btn btn-primary" value="수정 " onclick="return confirm('수정하시겠습니까?');"> <a
					href="/board_delete.do?num=<%=board.getSeq()%>&pageNum=<%=pageNum %>" onclick="return confirm('삭제하시겠습니까?');" class="btn btn-primary">삭제</a> 
					<a href="/BoardViewAction.do?num=<%=board.getSeq() %>&pageNum=<%=pageNum %>" class="admin btn btn-primary">취소</a>
			</div>
		</form>
	</div>
</body>
</html>