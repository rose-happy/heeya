<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
<h2>
	상품관리 페이지
</h2>
</header>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="./">Home</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#"></a></li>
      <li><a href="memberinput">회원가입</a></li>  <!-- 목적지 -->
      <li><a href="memberout">회원 조회</a></li>  <!-- 목적지 -->
    
    
      <li class="active"><a href="#"></a></li>
      <li><a href="sinput">상품 찾기</a></li>
      <li><a href="sout">상품 조회</a></li>
      <li><a href="search">상품 검색</a></li>
     
     </ul>
      <ul class="nav navbar-nav navbar-right">
      <c:choose>
      
      	<c:when test="${loginstate==true }">
      	 <li><a href="memberinput"><span class="glyphicon glyphicon-user"></span> ${id }님 </a></li>
     	 <li><a href="logout"><span class="glyphicon glyphicon-log-in"></span> 로그아웃</a></li>
      	</c:when>
      	
      	<c:otherwise>
      	<li><a href="memberinput"><span class="glyphicon glyphicon-user"></span> 회원가입</a></li>
    	  <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      	</c:otherwise>
      	
      </c:choose>
      
    </ul>
   
  </div>
</nav>
</body>
</html>