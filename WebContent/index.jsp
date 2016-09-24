<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Using Java</title>
</head>
<link href="social-buttons.css" rel="stylesheet"/>
<link href="bootstrap.min.css" rel="stylesheet">
<style>
.demoDiv {
  margin:auto;
  text-align:center;
}
</style>
<body>
<br/>




<div class="demoDiv">
<a	href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/GoogleAuth/googlecallback&response_type=code&client_id=713078684886-fsbv4kbc71hj98lktc6ieq8689ool64n.apps.googleusercontent.com&approval_prompt=force"
	class="btn btn-lg btn-social btn-google-plus"> <i
	class="fa fa-google"></i> Sign in with Google
</a>
</div>

<div class="demoDiv">
<a	href="https://www.facebook.com/v2.7/dialog/oauth?redirect_uri=http://localhost:8080/GoogleAuth/facebookcallback&state=c64f819323592fa72a5485b789a94418&scope&client_id=1745111929082181&ret=login&scope=email,user_birthday"
	class="btn btn-lg btn-social .btn-facebook"> <i
	class="fa fa-google"></i> Sign in with Facebook
</a>

</div>

<br>
<br>

<div>

Login by SPN Active directory: <a href="spnlogin"> Click here</a>

</div>

<div style="margin: 0 auto;width: 250px;">
	Sign in with Active Directory
	<div id="msg" style="color:red;">
		<%
			String msg = (String)request.getAttribute("msg"); 
			if(msg != null && !msg.isEmpty()){
				out.println(msg);
			}
		%>
	</div>
	
	<form method="post" action="loginLDAP">
		<div>
			Username:
			<input type="text" id="username" name="username" />
		</div>
		<div>
			Password:
			<input type="password" id="password" name="password" />
		</div>
		<div>
			<input type="reset" name="Reset">
			<input type="submit" value="Login">
		</div>
	</form>
</div>



</body>
</html>