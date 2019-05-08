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
a:link {
	color: black;
}

a:visited {
	color: black;
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

<!-- Naver Maps API 키 값 필요 -->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=rbeyz68rf5">
</script>

<script>
	$.fn.loadMap = function() {
		var mapOptions = {
				center : new naver.maps.LatLng(37.3595704, 127.105399),
				zoom : 10,
				zoomControl : true,
				zoomControlOptions : {
					style : naver.maps.ZoomControlStyle.SMALL
				}
			};
		var map = new naver.maps.Map('map', mapOptions);
		
		return map;
	}
	
	$.fn.getUsers = function(map) {
		console.log('사용자 목록 불러오기');
		
		var userArray = JSON.parse('${userList}');
		console.log(userArray);
		
		var users = new Array();
		
		userArray.forEach(user=> {
			users[user.userid] = user;
			//console.log(user);
			
			// marker 생성			
			var marker = new naver.maps.Marker({
				position : new naver.maps.LatLng(user.lat, user.lon),				
				map : map,
				icon : {
					content : '<i class="fas fa-trash-alt" style="color:red;"></i>',
					size : new naver.maps.Size(22, 35),
					anchor : new naver.maps.Point(11,35)
				}
			});
			
			users.marker = marker;
			console.log(users);
			
			naver.maps.Event.addListener(marker, 'click', function(e) {
				console.log(users);
			});
		});
		
		return users;
	}
	
	$(function() {
		var map = $('#map').loadMap();		
		var users = $('#map').getUsers(map);		
		
		console.log(users['abc']);
	});	
</script>


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
			<ul class="nav navbar-nav float-lg-right">
				<li class="nav-item mr-sm-2"><button id="loginBtn"
						type="button" class="btn btn-light m-1">로그인</button></li>
				<li class="nav-item mr-sm-2"><button id="joinBtn" type="button"
						class="btn btn-light m-1">회원가입</button></li>
			</ul>
		</div>
	</nav>

	<div class="container mt-5">
		<ul class="nav nav-tabs nav-justified">
			<li class="nav-item"><a class="nav-link"
				href="${contextPath}/admin/list"><i class="fas fa-user-friends"></i>
					사용자 목록</a></li>
			<li class="nav-item"><a class="nav-link active"
				href="${contextPath}/admin/monitor"><i
					class="fas fa-location-arrow"></i> 관제</a></li>
			<li class="nav-item"><a class="nav-link" href="#"><i
					class="fas fa-history"></i> 이용현황</a></li>
			<!-- <li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
			</li> -->
		</ul>

		<div class="jumbotron mt-5 mx-auto">
			<div id="map" style="width:100%;height:400px;"></div>
		</div>
	</div>
</body>
</html>