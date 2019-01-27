<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="n-head">
    <div class="g-doc f-cb">
        <c:if test="${cookie.user.value =='seller'}">
            <div class="user">
                    <%--卖家你好,<span class="name">${userName}</span><a href="/logout">[退出]</a>--%>
                卖家你好,<span class="name">${cookie.user.value}</span><a href="/logout">[退出]</a>
            </div>
        </c:if>
        <c:if test="${cookie.user.value =='buyer'}">
            <div class="user">
                    <%--买家你好，<span class="name">buyer</span>！<a href="/logout">[退出]</a>--%>
                买家你好，<span class="name">${cookie.user.value}</span>！<a href="/logout">[退出]</a>
            </div>
        </c:if>
        <%--<c:if test="${userName==''}" >--%>
        <c:if test="${cookie.user.value==null}">
            <div class="user">
                请<a href="/login">[登录]</a>
            </div>
        </c:if>
        <ul class="nav">
            <li><a href="/">首页</a></li>
            <c:if test="${cookie.user.value=='seller'}">
                <li><a href="/seller/publish">发布</a></li>
            </c:if>
            <c:if test="${cookie.user.value=='buyer'}">
                <li><a href="/account">账务</a></li>
                <li><a href="/cart">购物车</a></li>
            </c:if>
        </ul>
    </div>
</div>