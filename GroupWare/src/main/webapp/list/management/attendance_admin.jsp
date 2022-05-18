<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>attendance_admin</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/attendance_admin.css"/>">
</head>
<body>
		<jsp:include page="../../main_topbar/main.jsp"/>
		<jsp:include page="../../main_topbar/topbar.jsp"/>
		<jsp:include page="../../main_topbar/contents.jsp"/>
		<div class="container">
        <div align="center" class="attendance_search">
            <form method="post">
                <table>
                    <tr>
                        <td>
                            <select class="search_select">
                                <option value="subject">근무일자</option>
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
        <table class="table">
            <tr class="table_header">
				<th>사원번호</th>
                <th>성명</th>
                <th>출근시각</th>
                <th>퇴근시간</th>
                <th>근무시간</th>
                <th>휴일근로</th>
                <th>연장근로</th>
                <th>야간근로</th>
                <th>지각시간</th>
                <th>조퇴시간</th>
                <th>출근IP</th>
                <th>퇴근IP</th>
            </tr>
            <tr>
                <td class="table_num">1</td>
                <td><a href="#">김태리</a></td>
                <td>09:00</td>
                <td>06:00</td>
                <td>8시간</td>
				<td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td class="table_num">2</td>
                <td><a href="#">이병헌</a></td>
                <td>09:00</td>
                <td>06:00</td>
                <td>8시간</td>
				<td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td class="table_num">3</td>
                <td><a href="#">강동원</a></td>
                <td>09:00</td>
                <td>06:00</td>
                <td>8시간</td>
				<td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td class="table_num">4</td>
                <td><a href="#">성동일</a></td>
                <td>09:00</td>
                <td>06:00</td>
                <td>8시간</td>
				<td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td class="table_num">5</td>
                <td><a href="#">구준표</a></td>
                <td>09:00</td>
                <td>06:00</td>
                <td>8시간</td>
				<td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td class="table_num">6</td>
                <td><a href="#">한예슬</a></td>
                <td>09:00</td>
                <td>06:00</td>
                <td>8시간</td>
				<td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td>00:00</td>
                <td></td>
                <td></td>
            </tr>
        </table>

        <div align="center">
            <b>
                <a href="#">[1]</a>
                <a href="#">[2]</a>
                <a href="#">[3]</a>
            </b>
        </div>
         <div class="button" align="right">
            <a href="#" class="attendanceAdd btn btn-primary">근태계산</a>
            <a href="#" class="saveAdd btn btn-primary">저장</a>
        </div>
    </div>
</body>
</html>