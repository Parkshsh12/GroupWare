<%@page import="mvc.model.CompanyDTO"%>
<%@page import="java.util.ArrayList"%>
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
<link rel="stylesheet" href="/resources/css/business_company.css?ver=3">
</head>
<body>
<%
	ArrayList<CompanyDTO> list = (ArrayList<CompanyDTO>) request.getAttribute("companylist");
	String search_item = (String) request.getAttribute("search_item");
	String texet = (String) request.getAttribute("text");
%>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="container">
		<div align="center" class="business_search">
			<form action="/business_company.do" method="post">
				<table>
					<tr>
						<td><select class="search_select" name="search_item">
								<option value="p_company">업체명</option>
								<option value="p_address">주소</option>
								<option value="p_person">담당자</option>
						</select></td>
						<td class="search">
							<input class="search_content" type="text" name="text" />
							<input type="submit" class="search_btn"	value="검색" />
						</td>
					</tr>
				</table>
			</form>
			<div class="business_add">
				<a href="/business_companyAdd.do" class="businessAdd btn btn-primary">등록</a>
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
			<%
				for(int i = 0; i < list.size(); i++){
					CompanyDTO company = list.get(i);
			%>
			<tr>
				<td><%=company.getSeq()%></td>
				<td><%=company.getP_company() %></td>
				<td><%=company.getP_industry() %></td>
				<td><%=company.getP_address() %></td>
				<td><%=company.getP_companyNum() %></td>
				<td><%=company.getP_person() %></td>
				<td><%=company.getP_personNum() %></td>
				<td><a href="/companyUpdate.do?seq=<%=company.getSeq()%>">수정</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>