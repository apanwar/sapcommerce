<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<spring:htmlEscape defaultHtmlEscape="true" />

<div class="account-section-header">
    <div class="row">
        <div class="container-lg col-md-6">
            <spring:theme code="text.account.profile.affiliateCommissionDetails"/>
        </div>
    </div>
</div>

<c:if test="${empty commissions}">
    <div class="account-section-content content-empty">
        <ycommerce:testId code="affiliateCommissionDetails_noComission_label">
            <spring:theme code="text.account.affiliateCommissionDetails.noCommission" />
        </ycommerce:testId>
    </div>
</c:if>
<c:if test="${not empty commissions}">
    <div class="row">
        <div class="container-lg col-md-6">
            <div class="account-section-content">
                <div class="account-overview-table">
                    <table class="responsive-table">
                        <tr class="responsive-table-head hidden-xs">
                            <th><spring:theme code="text.account.affiliateCommissionDetails.orderNumber" /></th>
                            <th><spring:theme code="text.account.affiliateCommissionDetails.commissionReceived"/></th>
                        </tr>
                        <c:forEach items="${commissions}" var="commission">
                            <tr class="responsive-table-item">
                                <ycommerce:testId code="commission_item">
                                    <td class="hidden-sm hidden-md hidden-lg"><spring:theme code="text.account.affiliateCommissionDetails.orderNumber" /></td>
                                    <td class="responsive-table-cell">
                                        <c:out value="${commission.orderNumber}"/>
                                    </td>
                                    <td class="hidden-sm hidden-md hidden-lg"><spring:theme code="text.account.affiliateCommissionDetails.commissionReceived" /></td>
                                    <td class="responsive-table-cell responsive-table-cell-bold">
                                            ${fn:escapeXml(commission.income.formattedValue)}
                                    </td>
                                </ycommerce:testId>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</c:if>