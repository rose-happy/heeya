<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="1000px" align="center">
<caption>회원 찾기</caption>
	<tr>
		<th>아이디</th><th>비밀번호</th>
		<th>이름</th><th>나이</th>
	</tr>
	
	<c:forEach items="${list }" var="mm">
		<tr>
			<td>${mm.id }</td>
			<td>${mm.pw}</td>
			<td>${mm.name }</td>
			<td>${mm.age }</td>
		</tr>
	</c:forEach>
	
</table>
</body>
</html>