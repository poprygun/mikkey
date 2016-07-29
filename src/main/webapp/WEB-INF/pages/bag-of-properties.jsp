<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>Properties with values</h2>
<c:set var="object" value="${bag}" />
<c:if test="${not empty object['class'].declaredFields}">
    <h2>Declared fields <em>&dollar;{object.name}</em></h2>
    <ul>
        <c:forEach var="field" items="${object['class'].declaredFields}">
            <c:catch><li><span>${field.name}</span> ${object[field.name]}</li></c:catch>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>