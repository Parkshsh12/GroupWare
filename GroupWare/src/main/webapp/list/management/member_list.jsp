<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member_list</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/member_list.css"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="container">
        <table class="table">
            <tr class="table_header">
				<th>순서</th>
                <th>사원번호</th>
                <!--<th>패스워드</th>-->
                <th>이름</th>
                <th>주소</th>
                <th>휴대전화</th>
                <th>직위</th>
                <th>부서</th>
                <th>이메일</th>
                <th>입사일</th>
                <!--<th>시급</th>-->
            </tr>
            <tr>
                <td class="table_num">1</td>
                <td>0001</a></td>
                <!--<td>1111</td>-->
                <td>김태리</td>
                <td>서울시 강남구</td>
				<td>010-1111-1111</td>
				<td>팀장</td>
				<td>디자인팀</td>
				<td>0001@visual.com</td>
				<td>2021-04-10</td>
				<!--<td>10,000</td-->
            </tr>
			<tr>
               <td class="table_num">2</td>
                <td>0002</a></td>
                <!--<td>2222</td>-->
                <td>이병헌</td>
                <td>서울시 종로구</td>
				<td>010-1122-3344</td>
				<td>부장</td>
				<td>영업팀</td>
				<td>0002@visual.com</td>
				<td>2020-04-10</td>
				<!--<td>20,000</td>-->
            </tr>
            <tr>
               <td class="table_num">3</td>
                <td>0003</a></td>
                <!--<td>3333</td>-->
                <td>강동원</td>
                <td>서울시 동대문구</td>
				<td>010-1112-1122</td>
				<td>과장</td>
				<td>회계팀</td>
				<td>0003@visual.com</td>
				<td>2021-05-08</td>
				<!--<td>30,000</td>-->
            </tr>
            <tr>
                <td class="table_num">4</td>
                <td>0004</a></td>
                <!--<td>4444</td>-->
                <td>성동일</td>
                <td>서울시 서대문구</td>
				<td>010-4885-4885</td>
				<td>사원</td>
				<td>회계팀</td>
				<td>0004@visual.com</td>
				<td>2022-01-04</td>
				<!--<td>40,000</td>-->
            </tr>
            <tr>
                <td class="table_num">5</td>
                <td>0005</a></td>
                <!--<td>5555</td>-->
                <td>구준표</td>
                <td>경기도 남양주시</td>
				<td>010-1516-6645</td>
				<td>주임</td>
				<td>구매팀</td>
				<td>0005@visual.com</td>
				<td>2017-08-15</td>
				<!--<td>50,000</td>-->
            </tr>
            <tr>
                <td class="table_num">6</td>
                <td>0006</a></td>
                <!--<td>6666</td>-->
                <td>한예슬</td>
                <td>경기도 의정부시</td>
				<td>010-8884-6645</td>
				<td>대리</td>
				<td>자재팀</td>
				<td>0006@visual.com</td>
				<td>2015-12-25</td>
				<!--<td>60,000</td>-->
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
        	<a href="./member_update.jsp" class="boardAdd btn btn-primary">직원추가</a>
        </div>
        <div align="center" class="board_search">
            <form method="post">
                <table>
                    <tr>
                        <td>
                            <select class="search_select">
                                <option value="number">사원번호</option>
                                <!--<option value="pw">패스워드</option>-->
                                <option value="name">이름</option>
                                <option value="adress">주소</option>
                                <option value="phone">휴대전화</option>
                                <option value="positon">직위</option>
                                <option value="department">부서</option>
                                <option value="email">이메일</option>
                                <option value="join_date">입사일</option>
                                <!--<option value="hourlywage">시급</option>-->
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