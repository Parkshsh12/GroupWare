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
<link rel="stylesheet" href="<c:url value="/resources/css/business_companyAdd.css"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="update_info">
		<form action="/companyAddAction.do" name="boardAdd" method="post" class="form-horizontal">
			<div class="form-group row">
				<label class="col-sm-2 input-name">업체명</label>
				<div class="col-sm-3">
					<input class="form-input" type="text" name="p_company" required
						placeholder="업체명을 입력하세요.">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 input-name">업종명</label>
				<div class="col-sm-3">
					<input class="form-input" type="text" name="p_industry"
						placeholder="업종명을 입력하세요.">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 input-name">주소</label>
				<div class="col-sm-3">
					<input class="form-input" type="text" name="p_address" required
						placeholder="주소를 입력하세요.">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 input-name">전화번호</label>
				<div class="col-sm-5">
					<select class="form-tel" name="p_companyNum1">
						<option value="055">055</option>
						<option value="051">051</option>
						<option value="032">032</option>
						<option value="02">02</option>
					</select> - <input class="form-tel" type="tel" name="p_companyNum2" required>
					- <input class="form-tel" type="tel" name="p_companyNum3" required>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 input-name">담당자</label>
				<div class="col-sm-3">
					<input class="form-input" type="text" name="p_person"
						placeholder="담당자를 입력하세요.">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 input-name">휴대폰</label>
				<div class="col-sm-5">
					<input class="form-tel" type="tel" name="p_personNum1" required>
					- <input class="form-tel" type="tel" name="p_personNum2" required>
					- <input class="form-tel" type="tel" name="p_personNum3" required>
				</div>
			</div>
			<div class="form-group row" align="right">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="등록"> <a
						href="#" class="btn btn-primary">취소</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>