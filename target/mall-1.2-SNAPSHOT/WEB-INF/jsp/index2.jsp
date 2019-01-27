<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<jsp:include page="./include/header.jsp"></jsp:include>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<jsp:include page="./include/publicInfo.jsp"></jsp:include>

<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <%--<li class="z-sel" ><a href="/">所有内容</a></li>--%>
                <li<c:if test="${showType== 'yes'}"> class="z-sel" </c:if> ><a href="/">所有内容</a></li>
                <c:if test="${cookie.user.value =='buyer'}">
                    <li
                            <c:if test="${showType== 'not'}">class="z-sel" </c:if> ><a href="/notBuy">未购买的内容</a></li>
                </c:if>
            </ul>
        </div>
    </div>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
            <c:if test="${cookie.user.value =='seller'||cookie.user.value ==null}">
                <c:forEach var="item" items="${list}">
                    <li id="p-${item.gid}">
                        <a href="/show?id=${item.gid}" class="link">
                            <div class="img"><img src=${item.image} alt=${item.title}></div>
                            <h3>${item.title}</h3>
                            <div class="price"><span class="v-unit">￥</span><span
                                    class="v-value">${item.sellPrice}</span></div>
                            <c:if test="${item.sell && cookie.user.value =='seller'}">
                                <span class="had"><b>已售出</br>${item.sellCount}件</b></span>
                                <%--<span class="had"><b>${item.sellCount}件</b></span>--%>
                            </c:if>
                            <c:if test="${!item.sell && cookie.user.value =='seller'}">
                                <div class="del2">
                                    <a href="/seller/delete?id=${item.gid}">删除</a>
                                </div>
                            </c:if>
                        </a>
                    </li>
                </c:forEach>
            </c:if>

            <c:if test="${cookie.user.value =='buyer'}">
                <c:forEach var="item" items="${list}">
                    <li id="p-${item.gid}">
                        <a href="/show?id=${item.gid}" class="link">
                            <div class="img"><img src=${item.image} alt=${item.title}></div>
                            <h3>${item.title}</h3>
                            <div class="price"><span class="v-unit">￥</span><span
                                    class="v-value">${item.sellPrice}</span></div>
                            <c:if test="${item.buy}">
                                <span class="had1"><b>已购买</b><br/></span>
                            </c:if>
                        </a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
    </div>
</div>

</div>
<jsp:include page="include/footer.jsp"></jsp:include>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageIndex.js"></script>
</body>
</html>