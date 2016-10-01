<!DOCTYPE html>
<%@page import="com.sl.oauth.GlobalCons"%>
<%@page import="com.sl.oauth.UserPojo"%>
<html>
<head>
<title>DEMO - Login With Google using Java</title>
<link href='/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
</head>
<body>

<%UserPojo gp = (UserPojo)request.getAttribute(GlobalCons.AUTH); %>
<div style="width:400px;margin:auto;padding-top:30px;">
  <table class="table table-bordered">
    <tr>
      <td>User ID</td>
      <td><%=gp.getId()%></td>
    </tr>
    <tr>
      <td>Name</td>
      <td><%=gp.getName()%></td>
    </tr>
   
    
    
  </table> 
</div>

<div class="demoDiv">
<a	href="/GoogleAuth/index.jsp"
	class="btn btn-lg btn-social .btn-facebook"> <i
	class="fa fa-google"></i> Home
</a>

</body>
</html>