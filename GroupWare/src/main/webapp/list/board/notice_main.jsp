<%@page import="mvc.model.NoticeDTO"%>
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
<link rel="stylesheet" href="<c:url value="/resources/css/notice_main.css"/>">
</head>
<body>
<%
	String number = (String) session.getAttribute("number");
	String name = (String) session.getAttribute("name");
	ArrayList<NoticeDTO> list = (ArrayList<NoticeDTO>) request.getAttribute("noticelist");
	int total_record = ((Integer) request.getAttribute("total_record")).intValue();
	int pageNum = (Integer) request.getAttribute("pageNum");
	int total_page = ((Integer) request.getAttribute("total_page")).intValue();
	String search_item = request.getParameter("search_item");
	String text = request.getParameter("text");
	if(search_item == null){
		search_item = "title";
		text = "";
	}
%>
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
				for(int i = 0; i < list.size(); i++ ){
					NoticeDTO notice = (NoticeDTO) list.get(i);
			%>
			<tr>
				<td><%=notice.getSeq() %></td>
				<td><a href="./noticeView.do?seq=<%=notice.getSeq()%>&pageNum=<%=pageNum%>&search_item=<%=search_item%>&text=<%=text%>"><%=notice.getTitle()%></a></td>
				<td><%=notice.getName() %></td>
				<td><%=(notice.getB_date()).substring(0,19) %></td>
				<td><%=notice.getHit() %></td>
			</tr>
			<%
				}
			%>
		</table>

		<div align="center">
			<%
			for(int i = 1; i <= total_page; i++){
				if(pageNum == i ){
					
			%>
			<a href="/notice_main.do?pageNum=<%=i%>&search_item=<%=search_item%>&text=<%=text%>"><b>[<%=i%>]</b></a>
			<%
				}
				else{
					%>
					<a href="/notice_main.do?pageNum=<%=i%>&search_item=<%=search_item%>&text=<%=text%>"><font color='#4C5317'>[<%=i%>]</font></a>
					<%
							
				}
			}
			%>
		</div>
		<div align="right">
			<a href="./notice_add.do?pageNum=<%=pageNum%>&search_item=<%=search_item%>&text=<%=text%>&number=<%=number%>&name=<%=name%>" class="boardAdd btn btn-primary">글쓰기</a>
		</div>

		<div align="center" class="board_search">
			<form action="/notice_main.do" method="post">
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
							<input class="search_content" type="text" name="text" />
							<input type="submit" class="search_btn" value="검색" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>