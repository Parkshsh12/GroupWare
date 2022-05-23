<%@page import="mvc.model.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String sessionNumber = (String)session.getAttribute("number");
	ArrayList boardList = (ArrayList)request.getAttribute("boardList");
	int total_page = ((Integer)request.getAttribute("total_page")).intValue();
	int pageNum = ((Integer)request.getAttribute("pageNum")).intValue();
	int total_record = ((Integer) request.getAttribute("total_record")).intValue();
	String search_item = (String)request.getAttribute("search_item");
	String text = (String)request.getAttribute("text");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/board_main.css"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="container">
		<table class="table">
			<tr class="table_header">
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<%
				for(int i = 0; i < boardList.size(); i++){
					BoardDTO board = (BoardDTO)boardList.get(i);
					String date = board.getB_date().substring(0, 19);
			%>
			<tr>
				<td><%=board.getSeq() %></td>
				<td><a href="./BoardViewAction.do?num=<%=board.getSeq()%>&pageNum=<%=pageNum %>&number=<%=sessionNumber%>"><%=board.getTitle() %></a></td>
				<td><%=board.getName() %></td>
				<td><%=date%></td>
				<td><%=board.getHit() %></td>
			</tr>
			<%
				}
			%>
		</table>

		<div align="center">
			<c:set var="pageNum" value="<%=pageNum%>" />
						<c:forEach var="i" begin="1" end="<%=total_page%>">
						<%
							if(search_item != null && text != null){
						%>
							<a href="./board_main.do?pageNum=${i}&search_item=<%=search_item%>&text=<%=text%>">
								<%
						}
							else{
								
								%>
								<a href="./board_main.do?pageNum=${i}">
								<%
							}
								%>
								<c:choose>
									<c:when test="${pageNum==i}">
										<b> [${i}]</b>
									</c:when>
									<c:otherwise>
										<font color='4C5317'> [${i}]</font>
									</c:otherwise>
								</c:choose>
							</a>
							</a>
						</c:forEach>
		</div>
		<div align="right">
		<c:set var="number" value="<%=sessionNumber %>" />
			<a href="<c:url value="/board_add.do?number=${number}"/>" class="boardAdd btn btn-primary">글쓰기</a>
		</div>

		<div align="center" class="board_search">
			<form method="post" action="/board_main.do">
				<table>
					<tr>
						<td>
							<select class="search_select" name="search_item">
									<option value="title">제목</option>
									<option value="content">본문 내용</option>
									<option value="name">글쓴이</option>
							</select>
						</td>
						<td class="search">
							<input class="search_content" type="text" name="text"/>
							<input type="submit" class="search_btn" value="검색" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>