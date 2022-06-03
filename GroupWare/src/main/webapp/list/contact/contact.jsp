<%@page import="mvc.model.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
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
<%
	ArrayList memberList = (ArrayList)request.getAttribute("memberList");
	int pageNum = ((Integer)request.getAttribute("pageNum")).intValue();
	int total_page = ((Integer)request.getAttribute("total_page")).intValue();
	int total_record = ((Integer)request.getAttribute("total_record")).intValue();
	int count = 0;
	if(pageNum == 1){
		count = 0;
	} else{
		count = (pageNum - 1 ) * 5;
	}
%>
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
			<%
				for(int i = 0; i < memberList.size(); i++){
					MemberDTO member = (MemberDTO)memberList.get(i);
			%>
			<tr>
				<td><%=total_record - count - i %></td>
				<td><%=member.getDepartment() %></td>
				<td><%=member.getPosition() %></td>
				<td><%=member.getName() %></td>
				<td><%=member.getPhone() %></td>
				<td><%=member.getJoin_date() %></td>
			</tr>
			<%
				}
			%>
		</table>
	
		<div align="center">
        	<c:set var="pageNum" value="<%=pageNum %>"/>
        	<c:forEach var="i" begin="1" end="<%=total_page %>">
            	<a href="./contact.do?pageNum=${i}&number=${number}">
            	<c:choose>
            		<c:when test="${pageNum==i}">
            			<b>[${i}]</b>            		
            		</c:when>
            		<c:otherwise>
            			<font color='4C5317'>[${i}]</font>
            		</c:otherwise>
            	</c:choose>
            	</a>
            </c:forEach>
		</div>

		<div align="center" class="board_search">
			<form method="post" action="/contact.do">
				<table>
					<tr>
						<td><select class="search_select" name="search_item">
								<option value="name">이름</option>
								<option value="department">부서</option>
								<option value="position">직급</option>
								<option value="phone">연락처</option>
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