<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
	<script src="https://kit.fontawesome.com/53a8c415f1.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
</head>
<body>
    <div class="wrapp">
        <div class="login">
			<img src="../../resources/images/logo.png" alt="" width="100px">
            <h2>LOGIN</h2>
            <%
				String error = request.getParameter("error");
				if (error != null) {
					out.println("<h4>");
					out.println("아이디와 비밀번호를 확인해 주세요");
					out.println("</h4>");
				}
			%>
			<form class="form-signin" action="./LoginAction.do" method="post">
	            <div class="login_id">
	                <h4>사원번호</h4>
	                <input type="text" class="form-control" placeholder="사원번호를 입력하세요" name="number" required autofocus>
	            </div>
	            <div class="login_pw">
	                <h4>비밀번호</h4>
	                <input type="password" class="form-control" placeholder="비밀번호를 입력하세요." name="pw" required autofocus>
	            </div>
	            <div class="login_etc">
	                 <div class="forgot_id">
	                    <a href="id_search.jsp">사번 찾기 > </a>
	                </div>
	                <div class="forgot_pw">
	                    <a href="pw_search.jsp">비밀번호 찾기 > </a>
	                </div>
	            </div>
	            <div class="submit">
	                <button class="btn-block" type="submit">로그인</button>
	            </div>
            </form>
        </div>
    </div>
</body>
</html>