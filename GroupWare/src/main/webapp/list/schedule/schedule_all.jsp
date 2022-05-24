<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
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
<link rel="stylesheet" href="<c:url value="/resources/css/calendar.css?v=1"/>">
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
						<a class="btn btn-primary" href="#">일정추가</a>
					</div>
					<div class="daumdal">
						<a class="btn btn-primary"
							href="schedule_all.do?year=<%=year%>&amp;month=<%=month - 1%>">이전달</a>
						<a class="btn btn-primary"
							href="schedule_all.do?year=<%=year%>&amp;month=<%=month + 1%>">다음달</a>
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
									System.out.println(week);
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
							<td style="color: red"><%=j%></td>
							<%
								} else if (week % 7 == 1) {
							%>
							<td style="color: blue"><%=j%></td>
							<%
								} else {
							%>
							<td style="color: black"><%=j%></td>
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