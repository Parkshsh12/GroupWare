<%@page import="mvc.model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	MemberDTO member = (MemberDTO)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/update_information.css"/>">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
</script>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
    <div class="update_info">
        <form name="boardAdd" method="post" class="form-horizontal" action="/updateInfoAction.do?number=<%=member.getNumber()%>">
            <div class="form-group row">
                <label class="col-sm-2 input-name">이름</label>
                <div class="col-sm-3">
                    <p><%=member.getName() %></p>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">사원번호</label>
                <div class="col-sm-3">
                    <p><%=member.getNumber() %></p>
                </div>
            </div>            
            <div class="form-group row">
                <label class="col-sm-2 input-name">비밀번호</label>
                <div class="col-sm-3">
                    <input class="form-input" name="password" type="text" required placeholder="비밀번호를 입력하세요.">
                </div>
            </div>
      
            <div class="form-group row">
                <label class="col-sm-2 input-name">직위</label>
                <div class="col-sm-3">
                    <p><%=member.getPosition() %></p>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">입사일</label>
                <div class="col-sm-3">
                    <p><%=member.getJoin_date() %></p>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">주소</label>
                <div class="col-sm-3">
                    <input class="form-input" name="address" type="text" value="<%=member.getAddress() %>" required placeholder="주소를 입력하세요.">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">이메일</label>
                <div class="col-sm-3">
                    <input class="form-input" name="email" type="email" value="<%=member.getEmail() %>" required placeholder="이메일을 입력하세요.">
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
            <div class="form-group row button" align="right">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-primary" value="등록" onclick="test()">
                    <c:set var="number" value="<%=member.getNumber() %>"/>
                    <a href="<c:url value="/my_informationChk.do?number=${number}"/>" class="btn btn-primary">취소</a>
                </div>
            </div>
        </form>
    </div>

</body>

</html>