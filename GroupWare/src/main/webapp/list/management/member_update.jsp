<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member_update</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/member_update.css"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="member_add">
        <form name="memberdAdd" method="post" class="form-horizontal">
            <div class="form-group row">
                <label class="col-sm-2 input-name">사원번호</label>
                <div class="col-sm-3">
                    <input class="form-input" type="id" required placeholder="사원번호를 입력하세요.">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">패스워드</label>
                <div class="col-sm-3">
                    <input class="form-input" type="text" required placeholder="패스워드를 입력하세요.">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">이름</label>
                <div class="col-sm-3">
                    <input class="form-input" type="text" required placeholder="이름을 입력하세요">
                </div>
            </div>
            <!-- 성별 start -->
            <!-- <div class="form-group row">
                <label class="col-sm-2 input-name">성별</label>
                <div class="col-sm-3">
                    <input class="gender" name="gender" type="radio" value="남">남 
                    <input class="gender" name="gender" type="radio" value="여" checked="">여
                </div>
            </div> -->
            <!-- end -->
            <div class="form-group row">
                <label class="col-sm-2 input-name">주소</label>
                <div class="col-sm-3">
                    <input class="form-input" type="text" required placeholder="주소를 입력하세요.">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">휴대전화</label>
                <div class="col-sm-5">
                    <select class="input_num" name="" id="">
                        <option value="">010</option>
                        <option value="">011</option>
                        <option value="">016</option>
                        <option value="">017</option>
                        <option value="">019</option>
                    </select> - 
                    <input class="input_num" type="text"> - 
                    <input class="input_num" type="text">

                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">직위</label>
                <div class="col-sm-3">
                    <input class="form-input" type="text" required placeholder="직위 입력하세요">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">부서</label>
                <div class="col-sm-3">
                    <input class="form-input" type="text" required placeholder="부서를 입력하세요">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">이메일</label>
                <div class="col-sm-3">
                    <input class="form-input" type="email" required placeholder="이메일을 입력하세요.">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">입사일</label>
                <div class="col-sm-3">
                    <input class="form-input" type="date" required placeholder="입사일을 입력하세요.">
                </div>
            </div>
            <div class="button" align="right">
            	<a href="#" class="btn btn-primary">저장</a>
            	<input type="submit" class="btn btn-primary" value="회원정보">
            	<a href="#" class="btn btn-primary">회원탈퇴</a>
            </div>
        </form>
    </div>
</body>
</html>