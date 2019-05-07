<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>

<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<script>
	$(function() {
		$("#loginBtn").on("click", function() {
			console.log("${contextPath}/user/login")
			location = '${contextPath}/user/login';
		});

		$('#joinBtn').on("click", function() {
			location = '${contextPath}/user/join';
		});
	});
</script>

<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="/clean">깨끗한도시</a>
		<button class="navbar-toggler" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse flex-row-reverse"
			id="collapsibleNavbar">
			<c:if test="${empty USER}">
				<ul class="nav navbar-nav float-lg-right">
					<li class="nav-item mr-sm-2"><button id="loginBtn"
							type="button" class="btn btn-light m-1">로그인</button></li>
					<li class="nav-item mr-sm-2"><button id="joinBtn"
							type="button" class="btn btn-light m-1">회원가입</button></li>
				</ul>
			</c:if>
			<c:if test="${not empty USER}">
				<ul class="nav navbar-nav float-lg-right">
					<li class="nav-item mr-sm-2"><a class="nav-link"
						href="${contextPath}/user/"> <i class="fas fa-user"></i>${USER.userid}</a></li>

					<li class="nav-item mr-sm-2"><a class="nav-link"
						href="${contextPath}/user/logout"> <i
							class="fas fa-sign-out-alt"></i>로그아웃
					</a></li>
				</ul>
			</c:if>
		</div>
	</nav>
	<div class="container">
		<br />
		<div id="home" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ul class="carousel-indicators">
				<li data-target="#home" data-slide-to="0" class="active"></li>
				<li data-target="#home" data-slide-to="1"></li>
			</ul>

			<!-- The slideshow -->
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="resources/home1.JPG">
				</div>
				<div class="carousel-item">
					<img src="resources/home2.JPG">
				</div>
			</div>

			<!-- Left and right controls -->
			<a class="carousel-control-prev" href="#home" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a class="carousel-control-next" href="#home" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>
		</div>
	</div>

</body>
</html>
