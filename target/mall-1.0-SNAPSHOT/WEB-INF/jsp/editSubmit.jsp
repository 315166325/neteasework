<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<jsp:include page="./include/header.jsp"></jsp:include>
<body>
<jsp:include page="./include/publicInfo.jsp"></jsp:include>
<div class="g-doc">
    <div class="n-result">
        <h3>编辑成功！</h3>
        <p><a href="/show?id=${id}">[查看内容]</a><a href="/">[返回首页]</a></p>
    </div>
</div>
</body>
</html>>