<%-- 
    Document   : form
    Created on : Jan 20, 2017, 6:55:01 PM
    Author     : kiett
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="${pageContext.servletContext.contextPath}/resources/CSS/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/resources/CSS/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="fullscreen_bg" class="fullscreen_bg">

        <div class="container">

	<form:form class="form-signin" action="${pageContext.servletContext.contextPath}/Login/">
		<h1 class="form-signin-heading text-muted">Sign In</h1>
		<input type="text" name="txtname" class="form-control" placeholder="Username" required="" autofocus="">
		<input type="password" name="txtpassword" class="form-control" placeholder="Password" required="">
                <h4 class="text-danger" style="color:#c12e2a;">${alert}</h4>
		<button class="btn btn-lg btn-primary btn-block" name="btnSubmit">
			Sign In
		</button>
        </form:form>    
   
        </div>
        </div>
    </body>
</html>
