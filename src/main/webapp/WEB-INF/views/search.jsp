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

<form action="search2" method="post">

	<table border="1" width="300px" align="center">
	<caption>찾을 상품을 검색 하세요.</caption>
	<tr>
		<th>검색구분</th>
		<td><select name="key">
			<option value="spname">상품명</option>	
			<option value="sname">생산자</option>	
			<option value="sarea">생산지역</option>
		</select>	
		</td>
	
	</tr>
		<tr>
			<th>검색값</th>
			<td><input type="text" name="svalue"> </td>
		</tr>
		
		<tr style="text-align: center;">
			<td colspan="2">
			<input type="submit" value="검색">
			<input type="button" value="취소" onclick="location.href='main'">
			</td>
		</tr>
		
	</table>

</form>

</body>
</html>