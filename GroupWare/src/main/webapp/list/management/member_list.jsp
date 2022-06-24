<%@page import="mvc.model.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>임직원 관리</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/member_list.css"/>">
</head>
<body>
<%
	ArrayList<MemberDTO> list = (ArrayList<MemberDTO>) request.getAttribute("list");
	String search_item = (String) request.getAttribute("search_item");
	String texet = (String) request.getAttribute("text");
%>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="container">
		<div align="center" class="business_search">
			<form action="/member_list.do" method="post">
				<table>
					<tr>
						<td><select class="search_select" name="search_item">
								<option value="number">사원번호</option>
                                <option value="name">이름</option>
                                <option value="adress">주소</option>
                                <option value="phone">휴대전화</option>
                                <option value="positon">직위</option>
                                <option value="department">부서</option>
                                <option value="email">이메일</option>
                                <option value="join_date">입사일</option>
						</select></td>
						<td class="search">
							<input class="search_content" type="text" name="text" />
							<input type="submit" class="search_btn"	value="검색" />
						</td>
					</tr>
				</table>
			</form>
			<div class="business_add">
				<a href="/member_registration.do" class="memberAdd btn btn-primary">등록</a>
			</div>
		</div>
		<hr>
		<table class="table">
			<tr class="table_header">
				<th>순서</th>
                <th>사원번호</th>
                <th>이름</th>
                <th>주소</th>
                <th>휴대전화</th>
                <th>직위</th>
                <th>부서</th>
                <th>이메일</th>
                <th>입사일</th>
                <th>시급</th>
                <th>변경</th>
			</tr>
			<%
				for(int i = 0; i < list.size(); i++){
					MemberDTO member = list.get(i);
			%>
			<tr>
				<td><%=i+1%></td>
				<td><%=member.getNumber() %></td>
				<td><%=member.getName()%></td>
				<td><%=member.getAddress() %></td>
				<td><%=member.getPhone() %></td>
				<td><%=member.getPosition() %></td>
				<td><%=member.getDepartment() %></td>
				<td><%=member.getEmail() %></td>
				<td><%=member.getJoin_date() %></td>
				<td><%=member.getHourlywage() %></td>
				<td><a href="./member_update.do?number=<%=member.getNumber() %>">수정</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>