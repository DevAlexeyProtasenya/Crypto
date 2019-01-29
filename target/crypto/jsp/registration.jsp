<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localization}" scope="session"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="config.signin.registration"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="icon" href="../ico/icon.png">
    <script rel="script" src="../js/jquery-3.3.1.min.js"></script>
    <script rel="script" src="../js/validator.js"></script>
</head>
<body id="registration">
<div class="text-center">
    <form autocomplete="off" class="form-register" onsubmit="return checkRegistration()" action="${pageContext.request.contextPath}/web" method="POST" style="color:#E0FFFF;">
        <input type="hidden" name="command" value="registration" />
        <label><fmt:message key="config.registration.login"/><br>&nbsp;<input class="color" type="text" id="login" name="nickname" required autofocus="" /></label>
        <br>
        <label><fmt:message key="config.registration.password"/><br><input class="color" type="password" id="pass" name="password" required /></label>
        <br>
        <label><fmt:message key="config.registration.email"/><br>&nbsp;<input class="color" type="email" id="email" name="email" required /></label><br>
        <br>
        <button class="color" type="submit" name="registrationButton" class="btn"><fmt:message key="config.registration.button"/></button>
    </form>
    ${userExist}
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

