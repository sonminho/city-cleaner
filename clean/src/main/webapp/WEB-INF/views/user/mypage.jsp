<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="/clean"><i class="fas fa-recycle"></i>&nbsp;
			깨끗한도시</a>
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
						href="${contextPath}/user/mypage/${USER.userid}"><i
							class="fas fa-user"></i>${USER.userid}</a></li>

					<li class="nav-item mr-sm-2"><a class="nav-link"
						href="${contextPath}/user/logout"><i
							class="fas fa-sign-out-alt"></i>로그아웃 </a></li>
				</ul>
			</c:if>
		</div>
	</nav>

	<div class="container">
		<div class="jumbotron mt-5 content-center">
			<h4><i class="fas fa-info"></i>&nbsp;마이페이지</h4>
			<br/>
			<ul class="nav nav-tabs nav-justified">
				<li class="nav-item"><a class="nav-link active" href="#">정보수정</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">쓰레기 수거요청</a></li>
				<li class="nav-item"><a class="nav-link" href="#">이용 현황</a></li>
			</ul>
		</div>
	</div>
	
	<!-- footer -->
	<footer class="mt-5 p-3 bg-dark text-white">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-6">
					깨끗한 도시 &copy; 2019.05.23
				</div>
				<div class="col-sm-6 text-right">
					자율주행을 활용한 IoT 개발 전문가
				</div>
			</div>
		</div>
	</footer>
</body>
</html>