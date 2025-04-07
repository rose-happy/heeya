<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="top.jsp" %>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
td,th{
text-align: center;
}
img:hover{

	transform:scale(3,2);

}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="800px" align="center">
<caption>상품 조회하기</caption>

	<tr>
		<th>상품번호</th><th>상품명</th><th>생산자</th><th>생산지역</th>
		<th>생산일자</th><th>상품가격</th><th>상품이미지</th><th>상품 조회수</th><th>상품수정</th><th>상품삭제</th>
	</tr>
	
	<c:forEach items="${list}" var="li">
		<tr>
			<td>${li.snumber }</td>
			<td>
		<a href="detail?num=${li.snumber }"> ${li.spname }	<!-- 상품명 링크 -->
			 </a> <!-- detail 이라는 목적지에 num 변수를 가져간다 -->
			</td>
			<td>${li.sname }</td>
			<td>${li.sarea }</td>
			<td>${li.sdate }</td>
			<td>${li.sprice }</td>
			<td>
			<img src="./image/${li.simg }"width="70px"height="60px">	
			</td>
			<td>${li.sreadcnt }</td>
			<td>
		<a href="upload?num=${li.snumber}">수정 </a>
			</td>
			<td>
		<a href="delete?num=${li.snumber}">삭제</a>
			</td>
			
		</tr>
	</c:forEach>
	
</table>
</body>
</html>