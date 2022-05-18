<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/business_company.css"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="container">
		<div align="center" class="business_search">
			<form method="post">
				<table>
					<tr>
						<td><select class="search_select">
								<option value="subject">제목</option>
								<option value="content">본문 내용</option>
								<option value="name">글쓴이</option>
						</select></td>
						<td class="search"><input class="search_content" type="text"
							name="text" /> <input type="submit" class="search_btn"
							value="검색" /></td>
					</tr>
				</table>
			</form>
			<div class="business_add">
				<a href="#" class="businessAdd btn btn-primary">등록</a>
			</div>
		</div>
		<hr>
		<table class="table">
			<tr class="table_header">
				<th>순서</th>
				<th>업체명</th>
				<th>업종명</th>
				<th>주소</th>
				<th>전화번호</th>
				<th>담당자</th>
				<th>담당자 연락처</th>
				<th>변경</th>
			</tr>
			<tr>
				<td>1</td>
				<td><a href="#">에이비</a></td>
				<td>전기 전자</td>
				<td>창원시 의창구 도리도리길 10-6</td>
				<td>055-123-4567</td>
				<td>김도리</td>
				<td>010-1111-2222</td>
				<td><a href="#">수정</a></td>
			</tr>
			<tr>
				<td>2</td>
				<td><a href="#">씨디</a></td>
				<td>철강</td>
				<td>마산 합포구 어리버리길 20-6</td>
				<td>055-234-5678</td>
				<td>김어리</td>
				<td>010-1234-5678</td>
				<td><a href="#">수정</a></td>
			</tr>
			<tr>
				<td>3</td>
				<td><a href="#">이에프</a></td>
				<td>전자</td>
				<td>마산 회원기 어벙이길 11-4</td>
				<td>055-111-3344</td>
				<td>박어벙</td>
				<td>010-2345-6789</td>
				<td><a href="#">수정</a></td>
			</tr>
			<tr>
				<td>4</td>
				<td><a href="#">지에이치</a></td>
				<td>판매</td>
				<td>서울 특별시 두리단길 312-4</td>
				<td>02-123-7890</td>
				<td>최두리</td>
				<td>010-2222-3333</td>
				<td><a href="#">수정</a></td>
			</tr>
			<tr>
				<td>5</td>
				<td><a href="#">아이제이</a></td>
				<td>통신</td>
				<td>대구 동동길 2층 201호</td>
				<td>051-222-1122</td>
				<td>문동동</td>
				<td>010-4444-5555</td>
				<td><a href="#">수정</a></td>
			</tr>
		</table>
	</div>
</body>
</html>