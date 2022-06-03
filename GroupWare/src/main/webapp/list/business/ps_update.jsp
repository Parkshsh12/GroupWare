<%@page import="mvc.model.PStableDTO"%>
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
<link rel="stylesheet" href="<c:url value="/resources/css/ps_add.css"/>">
</head>
<body>
<%
	String division = request.getParameter("division");
	PStableDTO dto = (PStableDTO) request.getAttribute("dto");
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	String search_item = request.getParameter("search_item");
	String text = request.getParameter("text");
%>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="update_info">
		<form action="/psupdateAction.do?division=<%=division%>&seq=<%=dto.getSeq() %>" name="boardAdd" method="post" class="form-horizontal">
			<div class="form-group row">
				<label class="col-sm-2 input-name">업체명</label>
				<input class="form-input" type="text" value="<%=dto.getCompany() %>" name="company" required placeholder="업체명을 입력하세요.">
			</div>
			<div class="form-group row">
				<label class="col-sm-2 input-name">품명</label>
				<input class="form-input" type="text" name="name" value="<%=dto.getName() %>" required placeholder="품명을 입력하세요.">
				<label class="col-sm-2 input-name">일자</label> <input
					class="form-input" type="date" value="<%=dto.getDate() %>" name="date" required>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 input-name">카테고리</label> <input
					class="form-input" name="category" value="<%=dto.getCategory()%>">
					<!-- <option>기타</option>
					<option>경비</option>
					<option>판매</option> -->
				</input> <label class="col-sm-2 input-name">수량</label>
				<input class="form-input" name="qty" type="text" value="<%=dto.getQty()%>" required placeholder="수량을 입력하세요.">
			</div>
			<div class="form-group row">
				<label class="col-sm-2 input-name">단위</label>
				<input class="form-input" name="unit" type="text" value="<%=dto.getUnit() %>" required placeholder="단위 입력하세요.">
				<label class="col-sm-2 input-name">단품 가격</label>
				<input class="form-input" type="text" name="price" value="<%=dto.getPrice() %>" required placeholder="가격을 입력하세요.">
			</div>
			<div class="form-group row">
				<label class="col-sm-2 input-name">비고</label>
				<textarea cols="50" rows="5" name="because" placeholder="사유를 입력하세요."><%=dto.getBecause()%></textarea>
			</div>
			<div class="button" align="right">
				<input type="submit" class="btn btn-primary" value="등록" "> <a
					href="/psmenu_main.do?division=<%=dto.getDivision()%>&search_item=<%=search_item%>&text=<%=text%>&pageNum=<%=pageNum%>" class="btn btn-primary">취소</a>
			</div>
		</form>
	</div>
</body>
</html>