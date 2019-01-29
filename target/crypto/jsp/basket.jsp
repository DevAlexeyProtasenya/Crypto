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
    <link rel="stylesheet" href="../css/basket.css">
    <link rel="stylesheet" href="../css/wrapper.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script rel="script" src="../js/jquery-3.3.1.min.js"></script>
    <script rel="script" src="../js/application.js"></script>
    <script rel="script" src="../js/validator.js"></script>
    <link rel="icon" href="../ico/icon.png">
</head>
<body background="../backgrounds/backgroundmenu.jpg" id="about">
<div class="wrapper">
<jsp:include page="common/header.jsp"/>
<h1><fmt:message key="config.order.title"/></h1>
    <form autocomplete="off" method="POST" onsubmit="return checkCard()" action="${pageContext.request.contextPath}/web">
        <input type="hidden" name="command" value="add_order"/>
<div class="shopping-cart">
    <div class="column-labels" style="padding-top: 16px">
        <label class="crypto-image" style="margin-bottom: 0px; padding-bottom: 0px;"><fmt:message key="config.order.logo"/> </label>
        <label class="crypto-details" style="margin-bottom: 0px; padding-bottom: 0px;"><fmt:message key="config.order.crypto"/></label>
        <label class="crypto-price" style="margin-bottom: 0px; padding-bottom: 0px;"><fmt:message key="config.order.price"/></label>
        <label class="crypto-quantity" style="margin-bottom: 0px; padding-bottom: 0px;"><fmt:message key="config.order.amount"/></label>
        <label class="crypto-removal" style="margin-bottom: 0px; padding-bottom: 0px;"><fmt:message key="config.order.del"/></label>
        <label class="crypto-line-price" style="margin-bottom: 0px; padding-bottom: 0px;"><fmt:message key="config.order.sum"/></label>
    </div>
    <x:forEach var="crypto" items="${order_list}">
        <div class="crypto">
            <hr color=white width=833px align="left" >
            <div class="form-inline" >
                <input type="hidden" name="command" value="calculate_order"/>
                <div class="crypto-image">
                    <img src="${crypto.iconCrypto} ">
                </div>
                <div class="crypto-details">
                    <p class="crypto-description"><x:out value="${crypto.nameCrypto}"/></p>
                </div>
                <div name="curName${crypto.abbreviationCrypto}" class="cryptoprice"
                     id="curName${crypto.abbreviationCrypto}" style="
    padding-top: 0px;
">
                    waiting...
                    <script>setInterval(function () {
                        findCrypto('${crypto.abbreviationCrypto}')
                    }, 1000); </script>
                </div>
                <div class="crypto-quantity">
                    <input type="number" name="amount${crypto.abbreviationCrypto}" id="amount${crypto.abbreviationCrypto}">
                </div>
                <div class="crypto-removal" style="
    padding-top: 10px;
">
                    <a  href="${pageContext.request.contextPath}/web?command=delete_crypto_order&cryptoid=${crypto.idCrypto}">
                    <i class="fa fa-trash-o" style="color: black; font-size: 200%; text-align: center; color: white" aria-hidden="true">
                        </i>
                    </a>
                </div>
                <div class="crypto-line-price" id="price${crypto.abbreviationCrypto}" style="
    padding-top: 10px;
">0</div>
                <script>setInterval(function () {
                    let text = "${crypto.abbreviationCrypto}";
                    calculateCostOrder(text)
                }, 2000);</script>
            </div>
        </div>
    </x:forEach>
    <div class="totals">
        <div class="totals-item">
            <label style="
    margin-right: 0px;
    margin-left: 210px;
"><fmt:message key="config.order.sum"/> </label>
            <div class="totals-value" id="result" style="margin-right: -224;
">0</div>
        </div>
    </div>
    <hr color=white width=833px align="left">
    <script>setInterval(function () {
        calculateCost(document.getElementsByClassName("cryptoprice").length)
    }, 2000);</script>

</div>

        <div class="form-inline">
        <div class="credit_card">
    <div class="row">
        <div class="container">
                <div class="row">
                    <div class="col-25">
                        <label class="credit-card" style="margin-bottom: 0px; margin-right: 25px;"><fmt:message key="config.basket.cardType" /></label>
                        <div class="icon-container" style="margin-bottom: 0px">
                            <i class="fa fa-cc-visa" style="color:navy; font-size: 130%"></i>
                            <i class="fa fa-cc-amex" style="color:blue; font-size: 130%"></i>
                            <i class="fa fa-cc-mastercard" style="color:red; font-size: 130%"></i>
                            <i class="fa fa-cc-discover" style="color:orange; font-size: 130%"></i>
                        </div>
                        <label class="credit-card"  style="margin-right: 25px;"><fmt:message key="config.basket.owner" /></label>
                        <input name="nameOnCard" type="text" placeholder="John More Doe">
                        <label class="credit-card"  style="margin-right: 25px;"><fmt:message key="config.basket.cardNumber" /></label>
                        <input name="creditCard"  type="text" placeholder="1111-2222-3333-4444">
                        <label class="credit-card"  style="margin-right: 25px;"><fmt:message key="config.basket.month" /></label>
                        <input name="monthOnCard" type="text" placeholder="September">
                        <label class="credit-card"  style="margin-right: 25px;"><fmt:message key="config.basket.year" /></label>
                        <input name="yearOnCard" type="text" placeholder="2018">
                        <label class="credit-card" style="margin-right: 25px;"><fmt:message key="config.basket.cvv" /></label>
                        <input name="cvvOnCard" type="text" placeholder="352">
                    </div>
                </div>
            <button type="submit" align="center" name="enterButton" class="btn" style="
    margin-top: 10px;
    margin-left: 50px;
" ><fmt:message key="config.basket.pay"/></button>
        </div>
    </div>
</div>
        </div>
    </form>
    ${wrong}
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>

