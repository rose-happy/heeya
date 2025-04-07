<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="loginsave" method="post">
<table border="1" width="300px" align="center">
	<caption>로그인</caption>
	<tr>
		<th>아이디</th>	
		<td><input type="text" name="id"></td>
	</tr>
	<tr>
		<th>비밀번호</th>	
		<td><input type="text" name="pw"></td>
	</tr>
	
	<tr style="text-align: center;">
			
		<td colspan="2">
		<input type="submit" value="로그인">
		<input type="reset" value="돌아가기">
		</td>
	</tr>
</table>
</form>
</body>
</html>