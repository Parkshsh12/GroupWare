<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String number = (String)session.getAttribute("number");
	String name = (String) request.getAttribute("name");
	System.out.println(name);
%>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/board_add.css"/>">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="board_add">
		<form name="boardAdd" method="post" action="./boardAddAction.do">
			<input class="title" name="number" type="hidden" value="<%=number%>">
			<div class="form-item">
				<label class="form-item">제목</label> <input class="title"
					name="subject" type="text" placeholder="제목을 입력하세요." required>
			</div>
			<div class="form-item">
				<label class="form-item">이름</label> <input class="title"
					name="name" type="text" value="<%=name%>" readonly="readonly">
			</div>
			<div class="form-item">
				<label class="form-item">내용</label>
				<textarea name="content" cols="50" rows="8" placeholder="내용을 입력하세요."
					required></textarea>
			</div>
			<div class="button">
				<input type="submit" class="btn btn-primary" value="등록 "> <input
					type="reset" class="btn btn-primary" value="취소 ">
			</div>
		</form>
	</div>

</body>
</html>