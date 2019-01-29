<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/wrapper.css">
    <link rel="icon" href="../ico/icon.png">
    <script rel="script" src="../js/jquery-3.3.1.min.js"></script>
    <script rel="script" src="../js/application.js"></script>
</head>
<body background="../backgrounds/backgroundmenu.jpg">
<jsp:include page="common/header.jsp"/>
<div class="wrapper">
<table class="main-table" id="grid" style="margin-left: 200px">
    <tbody>
    <script> createTable();setInterval(function(){getCurrency()},10000);</script>
    </tbody>
</table>
    <div class="form-inline">
        <div class="credit_card">
            <div class="row">
                <div class="container">
                    <div class="row">
                        <div class="col-25">
                            <select id="sel">
                                <option id="btc">BTC</option>
                                <option id="usd">USD</option>
                                <option id="eth">ETH</option>
                            </select>
                            <label class="credit-card" style="margin-right: 25px;"></label>
                            <input id="from" name="creditCard" type="text" placeholder="От">
                            <label class="credit-card" style="margin-right: 25px;"></label>
                            <input id="to" name="monthOnCard" type="text" placeholder="До">
                            <button onclick="im = setInterval(function(){filter()},2000)">ok</button>
                            <button onclick=" fadeAllIn(); clearInterval(im);">cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script>
    var grid = document.getElementById("grid");
    grid.onclick = function(e) {
        if (e.target.tagName != 'TH') return;
        sortGrid(e.target.cellIndex);
    };

    function sortGrid(colNum) {
        var tbody = grid.getElementsByTagName('tbody')[0];
        var rowsArray = [].slice.call(tbody.rows);
        var compare;
        compare = function (rowA, rowB) {
            return rowB.cells[colNum].innerHTML - rowA.cells[colNum].innerHTML;
        }
        rowsArray.sort(compare);
        grid.removeChild(tbody);
        for (var i = 0; i < rowsArray.length; i++) {
            tbody.appendChild(rowsArray[i]);
        }
        grid.appendChild(tbody);
    }
</script>
</div>
</body>
</html>
