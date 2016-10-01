<!DOCTYPE html>

<html>
<head>
<title>Error</title>
<link href='/css/bootstrap.min.css' rel='stylesheet' type='text/css' />
</head>
<body>


	<center>
		<span style="color: red"> <%
 	out.println(request.getAttribute("errMsg"));
 %></span>
	</center>

	<div class="demoDiv">
		<a href="/GoogleAuth/index.jsp"
			class="btn btn-lg btn-social .btn-facebook"> <i
			class="fa fa-google"></i> Home
		</a>
</body>
</html>