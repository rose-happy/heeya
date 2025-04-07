<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="top.jsp" %>   
<!DOCTYPE html>
<html>
<head>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script type="text/javascript">
$(document).ready(function () {
		$("idcheck").click(function () { // 중복검사 버튼 클릭
			var id = $("#id").val(); // 폼에서 입력한 ID값을 가져온다
		$.ajax({  //id를 컨트롤러로 넘김 
			type:"post",
			async:true,
			url:"idgo",
			data:{"id":id},
			success:function(res){
				if(res=="ok") alert("사용가능");
				else alert("사용중");
				
			}
				
		});	
		});
});

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="membersave" method="post">
	<table border="1" width="350px" align="center">
		<caption>회원관리</caption>
		<tr>
			<th>아이디</th>
			<td><input type="text" name="id" ID="id">
				<input type="button" value="ID 중복확인" id="idcheck">
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="pw"> </td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name"> </td>
		</tr>
		<tr>
			<th>나이</th>
			<td><input type="text" name="age"> </td>
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