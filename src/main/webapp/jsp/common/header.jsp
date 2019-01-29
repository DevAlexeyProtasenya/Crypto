<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="localization"/>
<jsp:useBean id="order_list" class="java.util.HashSet" scope="session"/>
<jsp:useBean id="amount_list" class="java.util.HashSet" scope="session"/>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/web?command=main">
            <img src="../../ico/icon.png" alt="Logo" style="width:25px; height:25px;">
        </a>
        <ul class="navbar-nav active">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/web?command=main"><fmt:message key="config.header.main"/> </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/jsp/cost.jsp"><fmt:message key="config.header.table"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/web?command=view_my_crypto"><fmt:message key="config.header.my"/></a>
            </li>
        </ul>
        <a href="${pageContext.request.contextPath}/jsp/basket.jsp">
        <i style="color: rgb(23,162,184); font-size: 150%; margin-left: 590px;" class="fa fa-shopping-cart"
           aria-hidden="true"></i>
        <b id="count_of_order" style="color: rgb(23,162,184);">${order_list.size()}</b>
        </a>
        <a href="${pageContext.request.contextPath}/jsp/account.jsp">
        <i style="color: rgb(23,162,184); font-size: 150%; margin-left: 30px;" class="fa fa-user-secret"
           aria-hidden="true"></i>
        </a>
        <a href="${pageContext.request.contextPath}/web?command=logout" >
        <i style="color: rgb(23,162,184); font-size: 150%; margin-left: 30px;" class="fa fa-sign-out"
           aria-hidden="true"></i>
        </a>
    </nav>