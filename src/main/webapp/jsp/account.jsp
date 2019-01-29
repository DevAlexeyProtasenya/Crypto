<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localization}" scope="session"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="config.main.title"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/differentCrypto.css">
    <link rel="stylesheet" type="text/css" href="../css/wrapper.css">
    <link rel="icon" href="../ico/icon.png">
    <script rel="script" src="../../js/jquery-3.3.1.min.js"></script>
    <script rel="script" src="../../js/validator.js"></script>
</head>
<body background="../backgrounds/backgroundmenu.jpg">
<jsp:include page="../jsp/common/header.jsp"/>
<div class="wrapper">
<div class="container" style="
    width:13%;
    height:13%;
    margin:auto;
">
    <form autocomplete="off" method="POST" onsubmit="return checkNewLogin()"
          action="${pageContext.request.contextPath}/web?command=new_login" style="
    margin-top: 60px;
    width: 200px;
    margin-bottom: 50px;
">
        <div class="form-group" style="
    margin-bottom: 12px;
    height: 61px;
    width: 400px;
">
        <label><fmt:message key="config.account.prevlogin"/></label>
        ${user.login}
            <input class="form-control" type="text" id="login"
                   name="nickname" placeholder="<fmt:message key="config.account.login"/>" required autofocus="" style="
    width: 226px;
    height: 34px;
"/>
            <button type="submit" name="newLogin" class="btn btn-success" style="
    width: 100px;
    height: 34px;
    padding-left: 12px;
    padding-right: 12px;
    margin-top:15px;
">
                <fmt:message key="config.account.button"/>
            </button>
        </div>
    </form>
    <br>
    ${newLogin}
    <br>
    <form autocomplete="off" method="POST" onsubmit="return checkNewEmail()"
          action="${pageContext.request.contextPath}/web?command=new_email" style="
    margin-bottom: 65px;
    width: 100px;
">
        <div class="form-group" style="
    margin-bottom: 12px;
    height: 61px;
    width: 400px;
">
        <label><fmt:message key="config.account.prevmail"/></label>
        ${user.email}
        <input class="form-control" type="email" id="email" name="email" placeholder="<fmt:message key="config.account.email"/>"
                required style="
    width: 226px;
    height: 34px;
"/>
            <button type="submit" name="newEmail" class="btn btn-success" style="
    width: 100px;
    height: 34px;
    padding-left: 12px;
    padding-right: 12px;
    margin-top: 15px;
">
                <fmt:message key="config.account.button"/>
            </button>
        </div>
    </form>
    ${newEmail}
    <br>
    <form autocomplete="off" method="POST" onsubmit="return checkNewPassword()"
          action="${pageContext.request.contextPath}/web?command=new_password">
        <div class="form-group" style="
    margin-bottom: 12px;
    height: 61px;
    width: 200px;
">
        <label><fmt:message key="config.account.pass"/><br></label>
            <input class="form-control" type="password" id="pass" placeholder="<fmt:message key="config.account.password"/>"
                   name="password" required style="
    width: 226px;
    height: 34px;
"/>
            <button type="submit" name="newPassword" class="btn btn-success" style="
    width: 100px;
    height: 34px;
    padding-left: 12px;
    padding-right: 12px;
    margin-top: 15px;
">
                <fmt:message key="config.account.button"/>
            </button>
        </div>
    </form>
    ${newPassword}
    <br>
</div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
