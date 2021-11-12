<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${not empty myAffiliateId }">
    <spring:url value="${product.url}?affiliateId=${myAffiliateId }" var="affiliateUrl" />
    <a href="${affiliateUrl }"><spring:message code="affiliate.link"/></a>
</c:if>