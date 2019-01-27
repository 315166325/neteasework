<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<jsp:include page="./include/header.jsp"></jsp:include>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>

<jsp:include page="./include/publicInfo.jsp"></jsp:include>
<div class="g-doc" id="settleAccount">
    <div class="m-tab m-tab-fw m-tab-simple f-cb" >
        <h2>已添加到购物车的内容</h2>
    </div>
    <table id="newTable" class="m-table m-table-row n-table g-b3">
    </table>
    <div id="act-btn"><button class="u-btn u-btn-primary" id="back" onclick="GoBack();">后退</button>
        <button class="u-btn u-btn-primary" id="Account">购买</button></div>
</div>

<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/settleAccount.js"></script>
</body>
<%--发一个ajax请求得到购物车信息--%>
</html>