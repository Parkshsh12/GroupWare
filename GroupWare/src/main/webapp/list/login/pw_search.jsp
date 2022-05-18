<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/pw_search.css"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
    <section class="container">
        <div class="pw_box">
        <form action="./SearchPw.do" method="post">
            <div>
                <div class="pw_search">
                    <img src="../../resources/images/logo.png" alt="" width="100px">
               		<%
						String result =(String)session.getAttribute("searchedPw");
						if (result != null)
						{%>
						<h3><%=result%></h3>
					<%	} %>
                	<div class="wrap">
			            <h2>비밀번호 찾기</h2>
			            <div class="login_id" action="./LoginAction.do" method="post">
			                <h4>사원번호</h4>
			                <input type="text" class="form-control" placeholder="사원번호를 입력하세요" name="number" required autofocus>
			            </div>
			            <div class="login_name">
			                <h4>이름</h4>
			                <input type="text" class="form-control" placeholder="이름을 입력하세요." name="name" required autofocus>
			            </div>
			            <div class="login_mail">
			                <h4>이메일</h4>
			                <input type="text" class="form-control" placeholder="이메일을 입력하세요." name="mail" required autofocus>
			            </div>
			            <div><input type="submit" class="searchbutton"></div>
			            
			            <div class="loginsearch">
			            	<p>사번을 찾으시나요?</p>
			                    <a href="id_search.jsp"><button class="button" type="button">사번 찾기</button></a>
			                </div>
			            </div>
			        </div>
                <%session.invalidate();%>
            </div>
		</form>
     </div>
    </section>
</body>
</html>