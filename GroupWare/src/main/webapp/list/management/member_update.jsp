<%@page import="mvc.model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	MemberDTO member = (MemberDTO)request.getAttribute("member");
	String msg = request.getParameter("msg");
%>
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
        <form name="member_add" method="post" action="/member_update.do">
            <div class="form-group row">
                <label class="col-sm-2 input-name">사원번호</label>
                <div class="col-sm-3">
                    <input class="form-input" name="number" type="number" required placeholder="사원번호를 입력하세요.">
                   	<%--<%=member.getNumber() --%>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">이름</label>
                <div class="col-sm-3">
                    <input class="form-input" name="name" type="text" required placeholder="이름을 입력하세요">
                   <%--<%=member.getName() --%>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">주소</label>
                <div class="col-sm-3">
                    <input class="form-input" name="address" type="text" required placeholder="주소를 입력하세요.">
                </div>
            </div>
              <div class="form-group row">
                <label class="col-sm-2 input-name">휴대전화</label>
                <div class="col-sm-5">
                    <select class="input_num" name="phone1">
                        <option value="010">010</option>
                        <option value="011">011</option>
                        <option value="016">016</option>
                        <option value="017">017</option>
                        <option value="019">019</option>
                    </select> - 
                    <input class="input_num" maxlength="4" type="text" name="phone2"> - 
                    <input class="input_num" maxlength="4" type="text" name="phone3">

                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">직위</label>
                <div class="col-sm-3">
                    <input class="form-input" name="postion" type="text" required placeholder="직위 입력하세요">
                	<%-- <%=member.getPosition() %> --%>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">부서</label>
                <div class="col-sm-3">
                    <input class="form-input" name="department" type="text" required placeholder="부서를 입력하세요">
                	<%-- <%=member.getDepartment() %> --%>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">이메일</label>
                <div class="col-sm-3">
                    <input class="form-input" name="email" type="email" required placeholder="이메일을 입력하세요.">
                    <%-- <%=member.getEmail() %> --%>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">입사일</label>
                <div class="col-sm-3">
                    <input class="form-input" name="date" type="date" required placeholder="입사일을 입력하세요.">
                	<%-- <%=member.getJoin_date() %> --%>
                </div>
            </div>
            <div class="button" align="right">
            	<a href="./member_list.do?number=<%=member.getNumber() %>" class="btn btn-primary">수정</a>
            	<a href="./member_delete.do?number=<%=member.getNumber() %>" class="btn btn-primary">회원탈퇴</a>
            	<a href="./member_list.do?number=<%=member.getNumber() %>" class="btn btn-primary">취소</a>
            </div>
        </form>
    </div>
</body>

</html>