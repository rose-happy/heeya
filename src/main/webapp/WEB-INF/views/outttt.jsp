<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ include file="top.jsp" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="800px" align="center">
<caption>상품 검색하기</caption>

	<tr>
		<th>상품번호</th><th>상품명</th><th>생산자</th><th>생산지역</th>
		<th>생산일자</th><th>상품가격</th><th>상품이미지</th><th>상품 조회수</th>
	</tr>
	
	<c:forEach items="${list}" var="li">
		<tr>
			<td>${li.snumber }</td>
			<td>${li.spname }</td>
			<td>${li.sname }</td>
			<td>${li.sarea }</td>
			<td>${li.sdate }</td>
			<td>${li.sprice }</td>
			<td>
			<img src="./image/${li.simg }"width="70px"height="60px">	
			</td>
			<td>${li.sreadcnt }</td>		
		</tr>
	</c:forEach>
	
</table>
</body>
</html>