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

<form action="save" method="post" enctype="multipart/form-data">
	<table border="1" width="350px" align="center">
<caption>상품찾기</caption>

	<tr>
		<th>상품명</th>
		<td><input type="text" name="spname"> </td>
	</tr>
	<tr>
		<th>생산자</th>
		<td><input type="text" name="sname"> </td>
	</tr>
	<tr>
		<th>생산지역</th>
		<td><input type="text" name="sarea"> </td>
	</tr>
	<tr>
		<th>생산일자</th>
		<td><input type="date" name="sdate"> </td>
	</tr>
	<tr>
		<th>상품가격</th>
		<td><input type="text" name="sprice"> </td>
	</tr>
	<tr>
		<th>상품이미지</th>
		<td><input type="file" name="simg"> </td>
	</tr>
	
	<tr style="text-align: center;">
	
		<td colspan="2">
		<input type="submit" value="확인"> 
		<input type="reset" value="취소"> 
		</td>
	</tr>
</table>
</form>

</body>
</html>