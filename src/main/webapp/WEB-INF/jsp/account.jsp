<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<jsp:include page="./include/header.jsp"></jsp:include>
<head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<jsp:include page="./include/publicInfo.jsp"></jsp:include>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已购买的内容</h2>
    </div>
    <table class="m-table m-table-row n-table g-b3">
        <colgroup><col class="img"/><col/><col class="time"/><col/><col class="num"/><col/><col class="price"/><col/></colgroup>
        <thead>
        <tr><th>内容图片</th><th>内容名称</th><th>购买时间</th><th>购买数量</th><th>购买价格</th></tr>
        </thead>
        <tbody>
        <c:forEach var = "item" items="${result}">
            <tr>
                <td><a href="/show?id=${item.gid}"><img src="${item.image}" alt=""></a></td>
                <td><h4><a href="/show?id=${item.gid}">${item.title}</a></h4></td>
                <td><span class="v-time">${item.buyTime}</span></td>
                <td><span class="v-num">${item.buyCount}</span></td>
                <td><span class="v-unit">￥</span><span class="value">${item.buyPrice}</span></td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4"><div class="total">总计：</div></td>
            <td><span class="v-unit">￥</span><span class="value">${total}</span></td>
        </tr>
        </tfoot>
    </table>
</div>
</body>
</html>