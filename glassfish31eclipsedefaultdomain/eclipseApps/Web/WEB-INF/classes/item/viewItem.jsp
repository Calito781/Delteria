<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="delt" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<delt:page>

<div class="box">
<h1><img alt="Icon" src="game/image.png?filename=${item.iconFile}" /> ${item.name} ${item.questItem ? "(Quest Item)" : ""}</h1>

<p>
${item.description}
</p>

<ul>
<c:forEach items="${item.properties}" var="property">
<c:if test="${property_formatter.shouldDisplayProperty(property.key)}">
	<li>${property_formatter.format(property)}</li>
</c:if>
</c:forEach>
</ul>
</div>

</delt:page>