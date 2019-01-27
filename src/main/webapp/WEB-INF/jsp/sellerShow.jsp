<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<jsp:include page="./include/header.jsp"></jsp:include>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<jsp:include page="./include/publicInfo.jsp"></jsp:include>
<div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="${sellerItem.image} " alt=""></div>
        <div class="cnt">
            <h2>${sellerItem.title}</h2>
            <p class="summary">${sellerItem.summary}</p>
            <div class="price">
                <span class="v-unit">￥</span><span class="v-value">${sellerItem.sellPrice}</span>
            </div>

            <%--<div class="num">购买数量：<span id="plusNum" class="lessNum"><a>-</a></span><span class="totalNum" id="allNum">${sellerItem.count}</span><span id="addNum" class="moreNum"><a>+</a></span></div>--%>
            <c:if test="${cookie.user.value  =='seller'}">
                <div class="oprt f-cb">
                    <a href="/seller/edit?id=${sellerItem.gid}" class="u-btn u-btn-primary">编 辑</a>
                </div>
            </c:if>
            <c:if test="${cookie.user.value =='buyer'}"><%--${sellerItem.buyCount}--%>
                <div class="num">购买数量：<span id="plusNum" class="lessNum"><a>-</a></span><span class="totalNum"
                                                                                              id="allNum">1</span><span
                        id="addNum" class="moreNum"><a>+</a></span></div>
                <div class="oprt f-cb">
                    <c:if test="${isSelled == false}">
                        <button class="u-btn u-btn-primary" id="add" data-id="${sellerItem.gid}"
                                data-title="${sellerItem.title}" data-price="${sellerItem.sellPrice}"
                                data-image="${sellerItem.image}">
                            加入购物车
                        </button>
                    </c:if>
                    <c:if test="${isSelled == true}">
                        <span class="u-btn u-btn-primary z-dis">已购买</span>
                        <span>当时购买价格：${buyPrice}</span>
                    </c:if>
                </div>
            </c:if>

        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${sellerItem.detail}
    </div>
</div>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageShow.js"></script>
</body>
</html>