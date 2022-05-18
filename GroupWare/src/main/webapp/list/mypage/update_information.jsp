<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/update_information.css"/>">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
    <div class="update_info">
        <form name="boardAdd" method="post" class="form-horizontal">
            <div class="form-group row">
                <label class="col-sm-2 input-name">이름</label>
                <div class="col-sm-3">
                    <p>박승현</p>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">사원번호</label>
                <div class="col-sm-3">
                    <p>0001</p>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">직위</label>
                <div class="col-sm-3">
                    <p>사원</p>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">입사일</label>
                <div class="col-sm-3">
                    <p>2022-05-12</p>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">주소</label>
                <div class="col-sm-3">
                    <input class="form-input" type="text" required placeholder="주소를 입력하세요.">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">이메일</label>
                <div class="col-sm-3">
                    <input class="form-input" type="email" required placeholder="이메일을 입력하세요.">
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
                <label class="col-sm-2 input-name">생년월일</label>
                <div class="col-sm-3">
                    <input class="form-input" type="date" required placeholder="생년월일을 입력하세요.">
                </div>
            </div>
            <div class="form-group row button" align="right">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-primary" value="등록">
                    <a href="<c:url value="/my_information.do?id=내 정보 관리"/>" class="btn btn-primary">취소</a>
                </div>
            </div>
        </form>
    </div>

</body>

</html>