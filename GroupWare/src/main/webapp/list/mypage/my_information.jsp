<%@page import="mvc.model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	MemberDTO member = (MemberDTO)request.getAttribute("member");
	String msg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/my_information.css"/>">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function PasswordCheck(){
		var password1 = document.getElementById("password").value;
		var password2 = document.getElementById("password2").value;
		
		if(password1 != password2){
			window.alert('비밀번호가 틀렸습니다. 다시 입력해주세요.');
			return false;
		} else{
			document.submitform.submit();
		}
	}
</script>
<body>	
	<jsp:include page="../../main_topbar/main.jsp"/>	
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<%
		if(msg.equals("1")){
	%>
	<form action="/my_informationChk.do?number=<%=member.getNumber() %>" method="post" name="submitform">
		<label>비밀번호 :  </label><input type="password" id="password" autofocus>
		<input type="hidden" id="password2" value="<%=member.getPw()%>">
		<input type="button" value="확인" class="btn btn-primary" onclick="PasswordCheck()" autofocus>
	</form>
	<%
		} else{
	%>
    <div class="update_info">
        <div name="boardAdd" class="form-horizontal">
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
                    <p><%=member.getAddress() %></p>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">이메일</label>
                <div class="col-sm-3">
                    <%=member.getEmail() %>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">휴대전화</label>
                <div class="col-sm-3">
                    <%=member.getPhone() %>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-offset-2 col-sm-10" align="right">
                <c:set var="number" value="<%=member.getNumber() %>"/>
                    <a href="<c:url value="/update_information.do?number=${number}"/>" class="btn btn-primary">수정</a>
                </div>
            </div>
           
        </div>
    </div>
<%
		}
%>
</body>
</html>