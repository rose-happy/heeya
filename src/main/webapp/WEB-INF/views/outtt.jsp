<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="top.jsp" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="800px" align="center">
<caption>상품 조회하기</caption>

	<tr>
		<th>생산지역</th><th>상품이미지</th><th>상품 조회수</th>
	</tr>
	
		<tr>	
			<td>${dto.sarea }</td>
			<td>
			<img src="./image/${dto.simg }"width="70px"height="60px">	
			</td>
			<td>${dto.sreadcnt }</td>
		</tr>
	
</table>
</body>
</html>