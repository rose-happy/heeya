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
<form action="delete2" method="post" enctype="multipart/form-data">
	<table border="1" width="350px" align="center">
<caption>${dto.sname}님의 상품삭제 목록</caption>


	<tr>
		<th>상품번호</th>
		<td><input type="text" name="snumber" value="${dto.snumber}" readonly="readonly"> </td>
	</tr>
	<tr>
		<th>상품명</th>
		<td><input type="text" name="spname" value="${dto.spname}"> </td>
	</tr>
	<tr>
		<th>생산자</th>
		<td><input type="text" name="sname" value="${dto.sname}"> </td>
	</tr>
	<tr>
		<th>생산지역</th>
		<td><input type="text" name="sarea" value="${dto.sarea}"> </td>
	</tr>
	<tr>
		<th>생산일자</th>
		<td><input type="date" name="sdate"> </td>
	</tr>
	<tr>
		<th>상품가격</th>
		<td><input type="text" name="sprice" value="${dto.sprice}"> </td>
	</tr>
	<tr>
		<th>상품이미지</th>
		<td>
		<img  src="./image/${dto.simg}" width="40px" height="35px">
		
	</tr>
	
	<tr>
		
		<td> <!-- 수정 하려는 이미지 파일을 삭제 하기 위해 파일명 저장 -->
			<input type="hidden"  name="himg" value="${dto.simg }">
		</td>
			
	</tr>
	
	<tr style="text-align: center;">
	
		<td colspan="2">
		<input type="submit" value="확인"> 
		<input type="button" value="취소" onclick="location.href='main'"> 
		</td>
	</tr>
</table>
</form>
</body>
</html>