<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/id_search.css"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp" />
	<jsp:include page="../../main_topbar/topbar.jsp" />
	<jsp:include page="../../main_topbar/contents.jsp" />
	<section class="container">
		<div class="id_box">
			<form class="id_search" action="./searchId.do" method="post">
				<img src="../../resources/images/logo.png" alt="" width="100px">
				<%
				String result = (String) session.getAttribute("searchedId");
				if (result != null) {
				%>
				<h3><%=result%></h3>
				<%
				}
				%>
				<div class="wrap">
					<h2>사원번호 찾기</h2>
					<div class="login_id">
						<h4>사원번호</h4>
						<input class="email_text form-control" type="text"
							placeholder="사원번호를 입력하세요" name="number" required autofocus>
					</div>
					<div class="login_name">
						<h4>이름</h4>
						<input class="email_text form-control" type="text"
							placeholder="이름을 입력하세요." name="name" required autofocus>
					</div>
					<div class="login_mail">
						<h4>이메일</h4>
						<input class="email_text form-control" type="text"
							placeholder="이메일을 입력하세요." name="email" required autofocus>
					</div>
					<div>
						<input type="submit" class="searchbutton">
					</div>

					<div class="loginsearch">
						<p>비밀번호를 찾으시나요?</p>
						<a href="./pw_search.jsp">비밀번호 찾기</a>
					</div>
				</div>
			</form>
		</div>
		<%
		session.invalidate();
		%>
	</section>
</body>
</html>