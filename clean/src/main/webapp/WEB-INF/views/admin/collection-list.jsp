<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.page-item.active .page-link {
	background-color: #495057;
}
</style>
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
		<a class="navbar-brand" href="/clean"><i class="fas fa-recycle"></i>
			깨끗한도시</a>
		<button class="navbar-toggler" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse flex-row-reverse"
			id="collapsibleNavbar">
			<c:if test="${empty ADMIN}">
				<ul class="nav navbar-nav float-lg-right">
					<li class="nav-item mr-sm-2"><button id="loginBtn"
							type="button" class="btn btn-light m-1">로그인</button></li>
					<li class="nav-item mr-sm-2"><button id="joinBtn"
							type="button" class="btn btn-light m-1">회원가입</button></li>
				</ul>
			</c:if>
			<c:if test="${not empty ADMIN}">
				<ul class="nav navbar-nav float-lg-right">
					<li class="nav-item mr-sm-2"><a class="nav-link"
						href="${contextPath}/admin/"> <i class="fas fa-user"></i>${ADMIN.userid}</a></li>

					<li class="nav-item mr-sm-2"><a class="nav-link"
						href="${contextPath}/user/logout"> <i
							class="fas fa-sign-out-alt"></i>로그아웃?
					</a></li>
				</ul>
			</c:if>
		</div>
	</nav>

	<div class="container mt-5">
		<ul class="nav nav-tabs nav-justified">
			<li class="nav-item"><a style="color:black;" class="nav-link" 
				href="${contextPath}/admin/list"><i class="fas fa-user-friends"></i>
					사용자 목록</a></li>
			<li class="nav-item"><a style="color:black;" class="nav-link"
				href="${contextPath}/admin/monitor"><i
					class="fas fa-location-arrow"></i> 관제</a></li>
			<li class="nav-item"><a style="background-color: #98bce4; color:black;" class="nav-link active"
				href="${contextPath}/admin/collection-list"><i
					class="fas fa-history"></i> 이용현황</a></li>
		</ul>
		
		<div class="jumbotron mt-5 content-center">
			<table class="table table-dark table-striped text-center">
				<thead class="thead-dark">
					<tr>
						<th><i class="fas fa-list-ol"></i> 번호</th>
						<th><i class="fas fa-user-friends"></i> 사용자</th>
						<th><i class="fas fa-battery-full"></i> 용량</th>
						<th><i class="fas fa-calendar-check"></i> 수집일</th>
					</tr>
				</thead>
				<c:forEach var="garbageCollection" items="${pi.list}"
					varStatus="status">
					<tr>
						<td>${garbageCollection.collectionNo}</a></td>
						<td>${garbageCollection.userid}</td>
						<td>${garbageCollection.cap}</td>
						<td><fmt:formatDate value="${garbageCollection.emptyDate}"
								pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>