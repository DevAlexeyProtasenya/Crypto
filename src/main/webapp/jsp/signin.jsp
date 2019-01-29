<%@ page errorPage="/jsp/error.jsp" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${localization}" scope="session"/>
<fmt:setBundle basename="localization"/>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <title><fmt:message key="config.signin.title"/></title>
    <link rel="icon" href="../ico/icon.png">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css">
    <style>
        body{
            padding-top:200px;
            background: url(../backgrounds/backgroundlog.jpg) no-repeat center fixed;
            background-attachment: fixed;
            background-size: cover;
        }
    </style>
</head>
<body style="color:#00FFFF;">
<div class="container">
    <div class="col-sm-6 col-sm-offset-3">
        <h1><fmt:message key="config.signin.title"/></h1>
        <br>
        <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/web">
            <input type="hidden" name="command" value="login"/>
            <div class="form-group">
                <label><fmt:message key="config.signIn.signIn"/>:</label>
                <input type="text" class="form-control" id='login' name="nickname">
            </div>
            <div class="form-group">
                <label><fmt:message key="config.password.pass"/>:</label>
                <input type="password" class="form-control" id='key' name="password">
            </div>
            <br/>
            ${loginAttributeError}
            <br/>
            ${wrongAction}
            <br/>
            ${nullPage}
            <br/>
            <br><br>
            <button type="submit" name="enterButton" class="btn"><fmt:message key="config.signin.button"/></button>
            <a style="color: white; font-size: 16px" href="${pageContext.request.contextPath}/jsp/registration.jsp"><fmt:message key="config.signin.registration"/></a>
        </form>
        <br>
    </div>
</div>
</body>
<footer class="navbar-fixed-bottom" align="right" style="
            padding:20px;
            font-size:250%;">
    <div class="row">
        <b><a style="color:white;" href="${pageContext.request.contextPath}/web?command=language&lang=ru_RU">RU</a></b>
        <b><a style="color:white;" href="${pageContext.request.contextPath}/web?command=language&lang=en_EN">EN</a></b>
    </div>
</footer>
</html>
