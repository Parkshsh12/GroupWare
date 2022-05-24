<%@page import="java.util.ArrayList"%>
<%@page import="mvc.model.CalendarDTO"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String number = (String)session.getAttribute("number");
	ArrayList<CalendarDTO> calendarList = (ArrayList)request.getAttribute("calendarList");
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
<link rel="stylesheet" href="<c:url value="/resources/css/calendar.css?v=3"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="calendar-box">
			<div class="calendar" align="center">
				<h2><%=year%>년&nbsp;<%=month%>월
				</h2>
				<div class="title">
					<div class="iljung">
					<c:set var="number" value="<%=number %>"/>
						<a class="btn btn-primary" href="<c:url value="/scheduleDepAdd.do?number=${number}"/>">일정추가</a>
					</div>
					<div class="daumdal">
						<a class="btn btn-primary"
							href="scheduleDepAction.do?year=<%=year%>&amp;month=<%=month - 1%>&number=<%=number%>">이전달</a>
						<a class="btn btn-primary"
							href="scheduleDepAction.do?year=<%=year%>&amp;month=<%=month + 1%>&number=<%=number%>">다음달</a>
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

										<%
										i++;
										}
									%>
										<div><%=calendar.getStart_date()%> ~ <%=calendar.getEnd_date()%><br><%=calendar.getC_title()%> [<%=calendar.getName() %>]
											<ul class="inner_sch">
												<li><b><%=calendar.getC_title() %></b></li>
												<li><span class="ca_title">일정기간</span> <span><%=calendar.getStart_date()%> ~ <%=calendar.getEnd_date()%></span></li>
												<li><span class="ca_title">작성자</span><span><%=calendar.getName() %></span></li>
												<li><span class="ca_title">일정내용</span><span><%=calendar.getC_title() %></span></li>
											</ul>
										</div>
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

										<%
										i++;
										}
									%>
										<div><%=calendar.getStart_date()%> ~ <%=calendar.getEnd_date()%><br><%=calendar.getC_title()%> [<%=calendar.getName() %>]</div>
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

										<%
										i++;
										}
									%>
										<div><%=calendar.getStart_date()%> ~ <%=calendar.getEnd_date()%><br><%=calendar.getC_title()%> [<%=calendar.getName() %>]</div>
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
</body>
</html>
