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
    <script rel="script" src="../js/jquery-3.3.1.min.js"></script>
</head>
<body background="../backgrounds/backgroundmenu.jpg">

<jsp:include page="common/header.jsp"/>
<div class="wrapper">
<h1 align="center" style="color: white;"><fmt:message key="config.main.h1"/></h1>
<div style="padding-bottom: 90px">
    ${notFind}
<table class="table_main" align="center">
    <tr>
        <th><fmt:message key="config.table.crypto.icon"/></th>
        <th><fmt:message key="config.table.crypto.name"/></th>
        <th><fmt:message key="config.table.crypto.abbreviation"/></th>
    </tr>
    <c:forEach var="crypto" items="${crypto}">
        <tr>
            <td>
                <img id="crypto-img" src="${crypto.iconCrypto} " style="height: 35px; width: 35px;">
            </td>
            <td>
                <c:out value="${crypto.nameCrypto}"/>
            </td>
            <td>
                <c:out value="[${crypto.abbreviationCrypto}]"/>
            </td>
            <td>
                <a style="color: white;" href="<c:out value="${crypto.infoCrypto}"/>"><i
                        style="color: black; font-size: 150%;" class="fa fa-info-circle" aria-hidden="true"></i></a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/web?command=add_crypto_order&cryptoid=${crypto.idCrypto}"><i
                        style="color: black; font-size: 150%;" class="fa fa-shopping-cart" aria-hidden="true"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>

