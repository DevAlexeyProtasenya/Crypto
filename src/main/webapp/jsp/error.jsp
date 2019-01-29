<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localization}" scope="session"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="icon" href="../ico/icon.png">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <title><fmt:message key="config.error.title"/></title>
</head>
<body>
<div class="container text-center">
    <br><br>
    <c:if test="${user eq null}">
        <a href="${pageContext.request.contextPath}/jsp/signin.jsp"><img src="../images/logogreen.png" height="90px" width="105px"></a>
    </c:if>
    <br><br><br>
    <div class="row">
        <div class="col-md-offset-4 col-md-4">
            <b><fmt:message key="config.error.requestFrom"/> ${pageContext.errorData.requestURI} "is failed"
                <br/>
                <fmt:message key="config.error.servletName"/> ${pageContext.errorData.servletName}
                <br/>
                <fmt:message key="config.error.status"/> ${pageContext.errorData.statusCode}
                <br/>
                <fmt:message key="config.error.exception"/> ${pageContext.errorData.throwable}
            </b>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>