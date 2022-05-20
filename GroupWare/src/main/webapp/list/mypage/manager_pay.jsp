<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/d777ad58b4.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/manager_pay.css"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
    <div class="container">
        <table class="table">
            <tr class="table_header">
                <th>번호</th>
                <th>귀속년월</th>
                <th>지급일자</th>
                <th>급여구분</th>
            </tr>
            <tr>
                <td class="table_num">5</td>
                <td>2022/05</td>
                <td>2022-05-10</td>
                <td>급여 <button type="button"  id="modal_opne_btn" class="btn btn-default btn-sm">보기</button></td>
            </tr>
            <tr>
                <td class="table_num">4</td>
                <td>2022/05</td>
                <td>2022-05-10</td>
                <td>급여 <button type="button" id="modal_opne_btn1" class="btn btn-default btn-sm">보기</button></td>
            </tr>
            <tr>
                <td class="table_num">3</td>
                <td>2022/05</td>
                <td>2022-05-10</td>
                <td>급여 <button type="button" id="modal_btn" class="btn btn-default btn-sm">보기</button></td>
            </tr>
            <tr>
                <td class="table_num">2</td>
                <td>2022/05</td>
                <td>2022-05-10</td>
                <td>급여 <button type="button" id="modal_btn" class="btn btn-default btn-sm">보기</button></td>
            </tr>
            <tr>
                <td class="table_num">1</td>
                <td>2022/05</td>
                <td>2022-05-10</td>
                <td>급여 <button type="button" id="modal_btn" class="btn btn-default btn-sm">보기</button></td>
            </tr>
            <tr>
                <td class="table_num">1</td>
                <td>2022/05</td>
                <td>2022-05-10</td>
                <td>급여 <button type="button" id="modal_btn" class="btn btn-default btn-sm">보기</button></td>
            </tr>
            <tr>
                <td class="table_num">1</td>
                <td>2022/05</td>
                <td>2022-05-10</td>
                <td>급여 <button type="button" id="modal_btn" class="btn btn-default btn-sm">보기</button></td>
            </tr>
            <tr>
                <td class="table_num">1</td>
                <td>2022/05</td>
                <td>2022-05-10</td>
                <td>급여 <button type="button" id="modal_btn" class="btn btn-default btn-sm">보기</button></td>
            </tr>
            <tr>
                <td class="table_num">1</td>
                <td>2022/05</td>
                <td>2022-05-10</td>
                <td>급여 <button type="button" id="modal_btn" class="btn btn-default btn-sm">보기</button></td>
            </tr>
            <tr>
                <td class="table_num">1</td>
                <td>2022/05</td>
                <td>2022-05-10</td>
                <td>급여 <button type="button" id="modal_btn" class="btn btn-default btn-sm">보기</button></td>
            </tr>
         
        </table>
        <div align="center">
            <b>
                <a href="#">[1]</a>
                <a href="#">[2]</a>
                <a href="#">[3]</a>
            </b>
        </div>
    </div>
    <div class="modal" id="modal">
    	<div class="container modal_content">
    	
	        <h2 class="title">5월 급여 명세서
	        	<button type="button" id="modal_close_btn"><i class="fa-solid fa-square-xmark"></i></button>
	        </h2>
	        <table class="table">
	            <tr class="table_header">
	                <th>부서</th>
	                <td>개발팀</td>
	                <th>사번</th>
	                <td>0001</td>
	            </tr>
	            <tr class="table_header">
	                <th>직급</th>
	                <td>사원</td>
	                <th>성명</th>
	                <td>박승현</td>
	            </tr>
	        </table>
	        <table class="table">
				  <tr>
				    <th rowspan="2">지급내역</th>
				    <th>기본급</th>
				    <th>연장근무</th>
				    <th>시간<br>0</th>
				    <th>휴일근무</th>
				    <th>시간<br>0</th>
				    <th>직급수당</th>
				    <th>주휴수당</th>
				  </tr>
				  <tr>
				    <td>0</td>
				    <td colspan="2">0</td>
				    <td colspan="2">0</td>
				    <td>0</td>
				    <td>0</td>
				  </tr>
			</table>
	        <table class="table">
	            <tr class="table_header">
	                <th rowspan="2">공제내역</th>
	                <th>국민연금</th>
	                <th>건강보험</th>
	                <th>고용보험</th>
	                <th>소득세</th>
	            </tr>
	            <tr class="table_header">
	                <td>100,000</td>
	                <td>100,000</td>
	                <td>30,000</td>
	                <td>300,000</td>
	            </tr>
	        </table>
	        <table class="table">
	            <tr class="table_header">
	                <th rowspan="2">합계</th>
	                <th>지급총액</th>
	                <th>공제세액</th>
	                <th>차감지급액</th>
	            </tr>
	            <tr class="table_header">
	                <td>1,000,000</td>
	                <td>530,000</td>
	                <td>470,000</td>
	            </tr>
	        </table>
	    </div>
    </div>
<script>
    document.getElementById("modal_opne_btn").onclick = function() {
        document.getElementById("modal").style.display="block";
    }
   
    document.getElementById("modal_close_btn").onclick = function() {
        document.getElementById("modal").style.display="none";
    }   
</script>
    <div class="modal" id="modal1">
    	<div class="container modal_content">
    	
	        <h2 class="title">5월 급여 명세서
	        	<button type="button" id="modal_close_btn1"><i class="fa-solid fa-square-xmark"></i></button>
	        </h2>
	        <table class="table">
	            <tr class="table_header">
	                <th>부서</th>
	                <td>개발팀</td>
	                <th>사번</th>
	                <td>0001</td>
	            </tr>
	            <tr class="table_header">
	                <th>직급</th>
	                <td>사원</td>
	                <th>성명</th>
	                <td>박승현</td>
	            </tr>
	        </table>
	        <table class="table">
				  <tr>
				    <th rowspan="2">지급내역</th>
				    <th>기본급</th>
				    <th>연장근무</th>
				    <th>시간<br>0</th>
				    <th>휴일근무</th>
				    <th>시간<br>0</th>
				    <th>직급수당</th>
				    <th>주휴수당</th>
				  </tr>
				  <tr>
				    <td>0</td>
				    <td colspan="2">0</td>
				    <td colspan="2">0</td>
				    <td>0</td>
				    <td>0</td>
				  </tr>
			</table>
	        <table class="table">
	            <tr class="table_header">
	                <th rowspan="2">공제내역</th>
	                <th>국민연금</th>
	                <th>건강보험</th>
	                <th>고용보험</th>
	                <th>소득세</th>
	            </tr>
	            <tr class="table_header">
	                <td>100,000</td>
	                <td>100,000</td>
	                <td>30,000</td>
	                <td>300,000</td>
	            </tr>
	        </table>
	        <table class="table">
	            <tr class="table_header">
	                <th rowspan="2">합계</th>
	                <th>지급총액</th>
	                <th>공제세액</th>
	                <th>차감지급액</th>
	            </tr>
	            <tr class="table_header">
	                <td>1,000,000</td>
	                <td>530,000</td>
	                <td>470,000</td>
	            </tr>
	        </table>
	    </div>
    </div>
<script>
    document.getElementById("modal_opne_btn1").onclick = function() {
        document.getElementById("modal1").style.display="block";
    }
   
    document.getElementById("modal_close_btn1").onclick = function() {
        document.getElementById("modal1").style.display="none";
    }   
</script>
</body>
</html>