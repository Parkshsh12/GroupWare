<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/my_information.css"/>">
<title>Insert title here</title>
</head>
<body>	
	<jsp:include page="../../main_topbar/main.jsp"/>	
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
    <div class="update_info">
        <div name="boardAdd" class="form-horizontal">
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
                    <p>창원시 의창구 봉곡동</p>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">이메일</label>
                <div class="col-sm-3">
                    krtmdgus2@naver.com
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">휴대전화</label>
                <div class="col-sm-3">
                    010-****-****
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">생년월일</label>
                <div class="col-sm-3">
                    1996-05-29
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-offset-2 col-sm-10" align="right">
                    <a href="<c:url value="/update_information.do?id=내 정보 수정"/>" class="btn btn-primary">수정</a>
                </div>
            </div>
           
        </div>
    </div>

</body>
</html>