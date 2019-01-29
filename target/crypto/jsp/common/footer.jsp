<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locallization}"/>
<fmt:setBundle basename="localization"/>
<footer class="navbar footer content container-fluid" align="right" style="
height: 30px;
background: #343a40;
display: block;
padding-top: 0px">
    <b><a style="font-size: 15pt; color: white;"
          href="${pageContext.request.contextPath}/web?command=language&lang=ru_RU">RU</a></b>
    <b><a style="font-size: 15pt; color: white;"
          href="${pageContext.request.contextPath}/web?command=language&lang=en_EN">EN</a></b>
</footer>