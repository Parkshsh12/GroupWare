<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>attendance_month</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/attendance_month.css"/>">
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
                                <option value="month">근무년월</option>
                            </select>
                        </td>
                        <td class="search">
                            <input class="search_content" type="date" name="text" />
                            <input type="submit" class="search_btn" value="검색" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <table class="table">
        	<tr class="table_header">
				<th rowspan="2">사번</th>
                <th rowspan="2">성명</th>
                <th rowspan="2">부서</th>
                <th rowspan="2">직위</th>
                <th>1</th>
                <th>2</th>
                <th>3</th>
                <th>4</th>
                <th>5</th>
                <th>6</th>
                <th>7</th>
                <th>8</th>
                <th>9</th>
                <th>10</th>
                <th>11</th>
                <th>12</th>
                <th>13</th>
                <th>14</th>
                <th>15</th>
                <th>16</th>
            </tr>
            <tr>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
            </tr>
            <tr>
                <td rowspan="2">1</td>
                <td rowspan="2">김태리</td>
                <td rowspan="2">디자인팀</td>
                <td rowspan="2">팀장</td>
                <th>17</th>
                <th>18</th>
                <th>19</th>
                <th>20</th>
                <th>21</th>
                <th>22</th>
                <th>23</th>
                <th>24</th>
                <th>25</th>
                <th>26</th>
                <th>27</th>
                <th>28</th>
                <th>29</th>
                <th>30</th>
                <th>31</th>
                <th></th>
            </tr>
            <tr>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td>■</td>
                <td></td>
            </tr>
         </div>
</body>
</html>