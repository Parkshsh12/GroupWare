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
<link rel="stylesheet" href="<c:url value="/resources/css/business_search.css"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="business_container">
		<div align="center" class="board_search">
			<form method="post">
				<table>
					<tr>
						<td>
							<div class="btn_title">업체명</div>
						</td>
						<td class="search"><input class="search_content" type="text"
							name="text" /> <input type="submit" class="search_btn"
							value="검색" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="select_table">
			<table>
				<tr class="table_title">
					<th>기준 년도</th>
				</tr>
				<tr>
					<td><input type="number" min="2019" max="2022">년</td>
				</tr>
			</table>
			<table>
				<tr class="table_title">
					<th>1월</th>
					<th>2월</th>
					<th>3월</th>
					<th>4월</th>
					<th>5월</th>
					<th>6월</th>
					<th>7월</th>
					<th>8월</th>
					<th>9월</th>
					<th>10월</th>
					<th>11월</th>
					<th>12월</th>
				</tr>
				<tr>
					<td>12344</td>
					<td>12344</td>
					<td>12344</td>
					<td>12344</td>
					<td>12344</td>
					<td>12344</td>
					<td>12344</td>
					<td>12344</td>
					<td>12344</td>
					<td>12344</td>
					<td>12344</td>
					<td>12344</td>
				</tr>
				<tr>
					<td>22134</td>
					<td>22134</td>
					<td>22134</td>
					<td>22134</td>
					<td>22134</td>
					<td>22134</td>
					<td>22134</td>
					<td>22134</td>
					<td>22134</td>
					<td>22134</td>
					<td>22134</td>
					<td>22134</td>
				</tr>


			</table>
		</div>
		<hr>
		<div class="search_list" align="center">
			<table>
				<tr>
					<th class="list_search_company" rowspan="2">거래처</th>
					<th colspan="2">1월</th>
					<th colspan="2">2월</th>
					<th colspan="2">3월</th>
					<th colspan="2">4월</th>
					<th colspan="2">5월</th>
					<th colspan="2">6월</th>
					<th colspan="2">7월</th>
					<th colspan="2">8월</th>
					<th colspan="2">9월</th>
					<th colspan="2">10월</th>
					<th colspan="2">11월</th>
					<th colspan="2">12월</th>
				</tr>
				<tr class="list_search_title">
					<th>매입</th>
					<th>매출</th>
					<th>매입</th>
					<th>매출</th>
					<th>매입</th>
					<th>매출</th>
					<th>매입</th>
					<th>매출</th>
					<th>매입</th>
					<th>매출</th>
					<th>매입</th>
					<th>매출</th>
					<th>매입</th>
					<th>매출</th>
					<th>매입</th>
					<th>매출</th>
					<th>매입</th>
					<th>매출</th>
					<th>매입</th>
					<th>매출</th>
					<th>매입</th>
					<th>매출</th>
					<th>매입</th>
					<th>매출</th>
				</tr>
				<tr align="center">
					<td>에이비</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
				</tr>
				<tr align="center">
					<td>씨디</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
				</tr>
				<tr align="center">
					<td>이에프</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
				</tr>
				<tr align="center">
					<td>지에이치</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
				</tr>
				<tr align="center">
					<td>아이제이</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
				</tr>
				<tr align="center">
					<td>케이엘</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
				</tr>
				<tr align="center">
					<td>엠엔오</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
				</tr>
				<tr align="center">
					<td>피큐알</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
					<td>21345</td>
					<td>11233</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>