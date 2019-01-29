<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${localization}" scope="session"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title><fmt:message key="config.main.title"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/mycrypto.css">
    <link rel="stylesheet" href="../css/wrapper.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script rel="script" src="../js/jquery-3.3.1.min.js"></script>
    <script rel="script" src="../js/application.js"></script>
    <link rel="icon" href="../ico/icon.png">
</head>
<body background="../backgrounds/backgroundmenu.jpg" id="about">
<div class="wrapper">
    <jsp:include page="common/header.jsp"/>
    <h1><fmt:message key="config.myCrypto.title"/></h1>
        <input type="hidden" name="command" value="sell_crypto_order"/>
        <div class="shopping-cart">
            <div class="column-labels" style="padding-top: 16px">
                <label class="crypto-image" style="margin-bottom: 0px; padding-bottom: 0px;"><fmt:message key="config.order.logo"/> </label>
                <label class="crypto-details" style="margin-bottom: 0px; padding-bottom: 0px;"><fmt:message key="config.order.crypto"/></label>
                <label class="crypto-price" style="margin-bottom: 0px; padding-bottom: 0px;"><fmt:message key="config.order.price"/></label>
                <label class="crypto-quantity" style="margin-bottom: 0px; padding-bottom: 0px;"><fmt:message key="config.order.amount"/></label>
                <label class="crypto-removal" style="margin-bottom: 0px; padding-bottom: 0px;"><fmt:message key="config.order.del"/></label>
                <label class="crypto-line-price" style="margin-bottom: 0px; padding-bottom: 0px;"><fmt:message key="config.order.sum"/></label>
            </div>
            <x:forEach var="crypto" items="${tranzaction}">
                <input type="hidden" name="idOrder" value="${crypto.idOrder}"/>
                <input type="hidden" name="idCrypto" value="${crypto.idCrypto}"/>
                <div class="crypto">
                    <hr color=white width=833px align="left" >
                    <div class="form-inline" >
                        <div class="crypto-image">
                            <img src="${crypto.iconCrypto} ">
                        </div>
                        <div class="crypto-details">
                            <p class="crypto-description"><x:out value="${crypto.nameCrypto}"/></p>
                        </div>
                        <div name="curName${crypto.abbreviationCrypto}${crypto.idOrder}" id="curName${crypto.abbreviationCrypto}${crypto.idOrder}" class="cryptoprice"
                              style="
    padding-top: 0px;
">
                            waiting...
                            <script>setInterval(function () {
                                findCryptoOrder('${crypto.abbreviationCrypto}', '${crypto.idOrder}')
                            }, 2000); </script>
                        </div>
                        <div class="crypto-quantity" style="
    padding-left: 60px;
">
                            <div class="crypto-details">
                                <div class="crypto-amount" id="amount${crypto.abbreviationCrypto}${crypto.idOrder}" style="
    padding-bottom: 14px;
"><x:out value="${crypto.amount}"/> </div>
                            </div>
                        </div>
                        <div class="crypto-removal">
                            <a  href="${pageContext.request.contextPath}/web?command=sell_crypto_order&idCrypto=${crypto.idCrypto}&idOrder=${crypto.idOrder}">
                            <i class="fa fa-retweet" style="color: black; font-size: 200%; text-align: center; color: white" aria-hidden="true">
                            </i>
                            </a>
                        </div>
                        <div class="crypto-line-price" name="price${crypto.abbreviationCrypto}${crypto.idOrder}" id="price${crypto.abbreviationCrypto}${crypto.idOrder}">
0</div>
                        <script>setInterval(function () {
                            let text = "${crypto.abbreviationCrypto}${crypto.idOrder}";
                            calculateCostOrderSell(text)
                        }, 500);</script>
                    </div>
                </div>
            </x:forEach>
        </div>

        <div class="form-inline">
            <div class="credit_card">
                <div class="row">
                    <div class="container">
                        <div class="row">
                            <div class="col-25">
                                <label class="credit-card" style="margin-bottom: 0px; margin-right: 25px;"><fmt:message key="config.basket.cardType" /></label>
                                <div class="icon-container" style="margin-bottom: 0px; margin-right: 25px;">
                                    <i class="fa fa-cc-visa" style="color:navy; font-size: 130%"></i>
                                    <i class="fa fa-cc-amex" style="color:blue; font-size: 130%"></i>
                                    <i class="fa fa-cc-mastercard" style="color:red; font-size: 130%"></i>
                                    <i class="fa fa-cc-discover" style="color:orange; font-size: 130%"></i>
                                </div>
                                <label class="credit-card" style="margin-right: 25px;"><fmt:message key="config.basket.cardNumber" /></label>
                                <input name="creditCard" type="text" placeholder="1111-2222-3333-4444">
                                <label class="credit-card" style="margin-right: 25px;"><fmt:message key="config.basket.month" /></label>
                                <input name="monthOnCard" type="text" placeholder="September">
                                <label class="credit-card" style="margin-right: 25px;"><fmt:message key="config.basket.year" /></label>
                                <input name="yearOnCard" type="text" placeholder="2018">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    ${wrong}
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>

