<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>member_registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/member_registration.css"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="member_reg">
        <form name="memberdReg" method="post" class="form-horizontal">
            <div class="form-group row">
                <label class="col-sm-2 input-name">사번</label>
                <div class="col-sm-3">
                    <input class="form-input" type="id" required placeholder="사번을 입력하세요.">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">직위</label>
                <div class="col-sm-3">
                    <input class="form-input" type="text" required placeholder="직위를 입력하세요.">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">부서</label>
                <div class="col-sm-3">
                    <input class="form-input" type="text" required placeholder="부서를 입력하세요">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">주소</label>
                <div class="col-sm-3">
                    <input class="form-input" type="text" required placeholder="주소를 입력하세요.">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">휴대전화</label>
                <div class="col-sm-5">
                    <input class="form-tel" type="tel" name="company_phone1" required> -
                    <input class="form-tel" type="tel" name="company_phone2" required> -
                    <input class="form-tel" type="tel" name="company_phone3" required>
                </div>
            </div>
            <div class="button" align="right">
            	<input type="submit" class="btn btn-primary" value="등록">
                <a href="#" class="btn btn-primary">취소</a>
            </div>
        </form>
    </div>
</body>
</html>