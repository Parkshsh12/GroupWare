<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/resources/css/contact.css"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="container">
		<table class="table">
			<tr class="table_header">
				<th>번호</th>
				<th>부서명</th>
				<th>직급</th>
				<th>이름</th>
				<th>연락처</th>
				<th>입사일</th>
			</tr>
			<tr>
				<td>1</td>
				<td>영업팀</td>
				<td>부장</td>
				<td>감동란</td>
				<td>010-1122-3344</td>
				<td>2020-04-01</td>
			</tr>
			<tr>
				<td>2</td>
				<td>영업팀</td>
				<td>부장</td>
				<td>감동란</td>
				<td>010-1122-3344</td>
				<td>2020-04-01</td>
			</tr>
			<tr>
				<td>3</td>
				<td>영업팀</td>
				<td>부장</td>
				<td>감동란</td>
				<td>010-1122-3344</td>
				<td>2020-04-01</td>
			</tr>
			<tr>
				<td>4</td>
				<td>영업팀</td>
				<td>부장</td>
				<td>감동란</td>
				<td>010-1122-3344</td>
				<td>2020-04-01</td>
			</tr>
			<tr>
				<td>5</td>
				<td>영업팀</td>
				<td>부장</td>
				<td>감동란</td>
				<td>010-1122-3344</td>
				<td>2020-04-01</td>
			</tr>
		</table>

		<div align="center">
			<b> <a href="#">[1]</a> <a href="#">[2]</a> <a href="#">[3]</a>
			</b>
		</div>

		<div align="center" class="board_search">
			<form method="post">
				<table>
					<tr>
						<td><select class="search_select">
								<option value="subject">이름</option>
								<option value="subject">부서</option>
								<option value="content">직급</option>
								<option value="name">연락처</option>
						</select></td>
						<td class="search"><input class="search_content" type="text"
							name="text" /> <input type="submit" class="search_btn"
							value="검색" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>