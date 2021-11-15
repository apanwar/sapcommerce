<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="deliveryMethods" required="true" type="java.util.List" %>

<%@ attribute name="selectedDeliveryMethodId" required="false" type="java.lang.String" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ attribute name="isSelected" required="false" type="java.lang.Boolean" %>


<select id="delivery_method" name="delivery_method" class="form-control">
	<c:forEach items="${deliveryMethods}" var="deliveryMethod">
		<multi-checkout:deliveryMethodDetails deliveryMethod="${deliveryMethod}" isSelected="${deliveryMethod.code eq selectedDeliveryMethodId}"/>
	</c:forEach> 
</select>


<c:set var="UPS" value="UPS" />
<c:set var="fedex" value="fedex" />
<c:if test="${ UPS eq selectedDeliveryMethodId}">
<h5>Select Delivery Date</h5>
<input type="date" data-date-inline-picker="true" />
<h5>Select Delivery Time</h5>
<select >
	
	<option>9 am - 10 am</option>
	<option>10 am - 11 am</option>
	<option>11 am - 12 noon</option>
	<option>1 pm - 2 pm</option>
	<option>2 pm - 3 pm</option>
	<option>3 pm - 4 pm</option>
	<option>4 pm - 5 pm</option>
	
	
</select>
 </c:if>
 <c:if test="${ fedex eq selectedDeliveryMethodId}">
 <h5>Select Delivery Date</h5>
<input type="date" data-date-inline-picker="true" />

 <h5>Select Delivery Time</h5>
<select >
	<option>1 am - 2 am</option>
	<option>2 am - 3 am</option>
	<option>3 am - 4 am</option>
	<option>4 am - 5 am</option>
	<option>5 am - 6 am</option>
	<option>6 am - 7 am</option>
	<option>7 am - 8 am</option>
	<option>8 am - 9 am</option>
	<option>9 am - 10 am</option>
	<option>10 am - 11 am</option>
	<option>11 am - 12 noon</option>
	<option>1 pm - 2 pm</option>
	<option>2 pm - 3 pm</option>
	<option>3 pm - 4 pm</option>
	<option>4 pm - 5 pm</option>
	<option>5 pm - 6 pm</option>
	<option>6 pm - 7 pm</option>
	<option>7 pm - 8 pm</option>
	<option>8 pm - 9 pm</option>
	<option>9 pm - 10 pm</option>
	<option>10 pm - 11 pm</option>
	<option>11 pm - 12 </option>
	
</select>
 </c:if>
