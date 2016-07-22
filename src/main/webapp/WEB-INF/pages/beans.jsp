<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>Loaded Beans</h2>

	<c:if test="${not empty beans}">

		<ul>
			<c:forEach var="beanName" items="${beans}">
				<li>${beanName}</li>
			</c:forEach>
		</ul>

	</c:if>
</body>
</html>