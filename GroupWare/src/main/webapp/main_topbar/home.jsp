<%@page import="mvc.model.CalendarDTO"%>
<%@page import="mvc.model.NoticeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mvc.model.BoardDTO"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	ArrayList<CalendarDTO> calendarList = (ArrayList)request.getAttribute("calendarAllList");
	ArrayList<BoardDTO> list = (ArrayList)request.getAttribute("boardList");
	ArrayList<NoticeDTO> noticelist = (ArrayList)request.getAttribute("noticelist");
	Calendar cal = Calendar.getInstance();
	
	int nowYear = cal.get(Calendar.YEAR);
	int nowMonth = cal.get(Calendar.MONTH) + 1;
	
	String fyear = request.getParameter("year");
	String fmonth = request.getParameter("month");
	
	int year = nowYear;
	int month = nowMonth;
	
	if (fyear != null) {
		year = Integer.parseInt(fyear);
	}
	if (fmonth != null) {
		month = Integer.parseInt(fmonth);
	}
	cal.set(year, month - 1, 1);
	
	year = cal.get(Calendar.YEAR);
	month = cal.get(Calendar.MONTH) + 1;
	
	int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	int week = cal.get(Calendar.DAY_OF_WEEK);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/css/home_calendar.css?v=5"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/home_2.css"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<style>

</style>
</head>
<body>
	<div class="topbar">
		<h1>Home</h1>
		<img src="./resources/images/logo.png" alt="logo" width="100px">
	</div>
	<div class="container1">
		<div class="table-box">
			<div class="title1">
				<h3>공지사항</h3>
				<a href="/notice_main.do" class="btn btn-primary btn-sm">더 보기</a>
			</div>
			<table class="table1">
				<tr class="table_header">
					<th>제목</th>
					<th>작성일</th>
				</tr>
				<% for(int i = 0; i < 5; i++){ 
					NoticeDTO notice = noticelist.get(i);					
				%>
				<tr>
					<td><a href="./noticeView.do?seq=<%=notice.getSeq()%>&pageNum=1"><%=notice.getTitle()%></a></td>
					<td><%=notice.getB_date()%></td>
				</tr>
				<%
				}
				%>
			</table>
			<div class="title2">
				<h3>자유게시판</h3>
				<a href="/board_main.do" class="btn btn-primary btn-sm">더 보기</a>
			</div>
			<table class="table1">
				<tr class="table_header">
					<th>제목</th>
					<th>작성일</th>
				</tr>
 				<%
					for(int i = 0; i < 5; i++){
						BoardDTO board = list.get(i);
				%>
				<tr>
					<td><a href="./BoardViewAction.do?num=<%=board.getSeq()%>&pageNum=1"><%=board.getTitle() %></a></td>
					<td><%=board.getB_date() %></td>
				</tr>
				<%
					}				
				%>
			</table>
		</div>
		<div class="calendar-box">
			<h3>오늘의 일정</h3>
			<div class="calendar_home" align="center">
				<h2><%=year%>년&nbsp;<%=month%>월
				</h2>
				<div class="title">
					<div class="daumdal">
						<a class="btn btn-primary"
							href="home.do?year=<%=year%>&amp;month=<%=month - 1%>">이전달</a>
						<a class="btn btn-primary"
							href="home.do?year=<%=year%>&amp;month=<%=month + 1%>">다음달</a>
					</div>
				</div>
				<div class="table">
					<table border="1">
						<tr class="calendar_title">
							<th style="color: red">일</th>
							<th>월</th>
							<th>화</th>
							<th>수</th>
							<th>목</th>
							<th>금</th>
							<th style="color: blue">토</th>
						</tr>
						<tr>
							<%
								for (int i = 0; i < week - 1; i++) {
							%>
							<td height="20">&nbsp;</td>
							<%
								}
							%>
							<%
								for (int j = 1; j <= endDay; j++) {
									week++;
									if (week % 7 == 2) {
							%>
						</tr>
						<tr>
							<%
								}
							%>
							<%
								if (week % 7 == 2) {
							%>
							<td style="color: red">
							<%=j%>
							<%
							int i = 1;
								for(int k = 0; k < calendarList.size(); k++){
									
									CalendarDTO calendar = calendarList.get(k);
									String date = calendar.getStart_date();
									String day = date.substring(8, date.length());
									String c_month = date.substring(5, 7);
									int day3 = Integer.parseInt(day);
									int c_month3 = Integer.parseInt(c_month);
									if(month == c_month3 && j == day3){
										if(i==1){
											%>
								<div class="circle">
								<c:set var="seq" value="<%=calendar.getSeq() %>"/>
										<%
										i++;
										}
									%>
										<a href="<c:url value="/scheduleHomeDetail.do?seq=${seq}"/>"><%=calendar.getStart_date()%> ~ <%=calendar.getEnd_date()%><br><%=calendar.getC_title()%> [<%=calendar.getName() %>]</a>
									<%
								}
							}
							%>
								</div>
							</td>
							<%
								} else if (week % 7 == 1) {
							%>
							<td style="color: blue">
							<%=j%>
							<%
							int i = 1;
								for(int k = 0; k < calendarList.size(); k++){
									
									CalendarDTO calendar = calendarList.get(k);
									String date = calendar.getStart_date();
									String day = date.substring(8, date.length());
									String c_month = date.substring(5, 7);
									int day3 = Integer.parseInt(day);
									int c_month3 = Integer.parseInt(c_month);
									if(month == c_month3 && j == day3){
										if(i==1){
											%>
								<div class="circle">
								<c:set var="seq" value="<%=calendar.getSeq() %>"/>
										<%
										i++;
										}
									%>
										<a href="<c:url value="/scheduleHomeDetail.do?seq=${seq}"/>"><%=calendar.getStart_date()%> ~ <%=calendar.getEnd_date()%><br><%=calendar.getC_title()%> [<%=calendar.getName() %>]</a>
									<%
								}
							}
							%>
								</div>
							</td>
							<%
								} else {
							%>
							<td style="color: black">
							<%=j%>
							<%
							int i = 1;
								for(int k = 0; k < calendarList.size(); k++){
									
									CalendarDTO calendar = calendarList.get(k);
									String date = calendar.getStart_date();
									String day = date.substring(8, date.length());
									String c_month = date.substring(5, 7);
									int day3 = Integer.parseInt(day);
									int c_month3 = Integer.parseInt(c_month);
									if(month == c_month3 && j == day3){
										if(i==1){
											%>
								<div class="circle">
								<c:set var="seq" value="<%=calendar.getSeq() %>"/>
										<%
										i++;
										}
									%>
										<a href="<c:url value="/scheduleHomeDetail.do?seq=${seq}"/>"><%=calendar.getStart_date()%> ~ <%=calendar.getEnd_date()%><br><%=calendar.getC_title()%> [<%=calendar.getName() %>]</a>
									<%
								}
							}
							%>
								</div>
							</td>
							<%
								}
							}
							%>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>