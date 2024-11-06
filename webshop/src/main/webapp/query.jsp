<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
<style>
.contents {
	display: flex;
	flex-wrap: wrap;
	justify-content: start;
	width: 60%;
	margin: 30px 0;
	padding-top: 30px;
}

.item {
	width: calc(100%/ 3 - 30px);
	margin-bottom: 30px;
	padding: 50px 10px;
	text-align: center;
}
</style>
</head>
<body>
	<h1>商品検索</h1>
	<%-- 検索フォーム（前回入力引き継ぎ） --%>
	<form action="query" method="get">
		<input type="radio" name="mode" value="nameQuery"
			<c:if test="${param.mode != 'priceQuery'}">checked</c:if>>
		商品名検索： <input type="text" name="name"
			value="<c:out value="${param.name}"/>">（部分一致）／ <input
			type="radio" name="mode" value="priceQuery"
			<c:if test="${param.mode == 'priceQuery'}">checked</c:if>>
		価格検索： <input type="number" name="low"
			value="<c:out value="${param.low}"/>">円〜 <input type="number"
			name="high" value="<c:out value="${param.high}"/>">円 <input
			type="submit" value="検索">
	</form>
	<%-- エラーメッセージ（あれば） --%>
	<c:if test="${not empty errorMessage}">
		<p>${errorMessage}</p>
	</c:if>
	<%-- 検索結果（あれば）cssのflex-boxを使用しています--%>
	<div class="contents">
		<c:forEach var="shohin" items="${shohinList}">
			<div class="item">
				<a href="detail?code=${shohin.code}"> <img
					src="img/${shohin.image}"><br> <c:out
						value="${shohin.name}" />
				</a>
			</div>
			<!-- .item -->
		</c:forEach>
	</div>
	<!-- .contents -->
</body>
</html>