<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html >
<html>
<head>
    <jsp:include page="./include/header.jsp"></jsp:include>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<jsp:include page="./include/publicInfo.jsp"></jsp:include>
<form class="m-form m-form-ht n-login" id="loginForm" onsubmit="return false;" autocomplete="off">
    <div class="fmitem">
        <label class="fmlab">用户名：</label>
        <div class="fmipt">
            <input class="u-ipt" name="username" autofocus/>
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt">
            <input class="u-ipt" type="password" name="password"/>
        </div>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block">登 录</button>
        </div>
    </div>
</form>
<jsp:include page="include/footer.jsp"></jsp:include>
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageLogin.js"></script>
</body>
</html>