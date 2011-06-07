<%@page contentType="text/html" pageEncoding="UTF-8" import="business.*, tags.*" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@taglib prefix="pcshop" uri="/WEB-INF/pcshop.tld"%>

<jsp:include page="/includes/header.html" />
<jsp:include page="/includes/column_left_home.jsp" />


<!-- start the middle column -->

<td valign="top" class ="onlineshop">
            
            <c:if test="${sessionScope.currentUser == null}">
                <pcshop:profile url="${jdbcURL}"/>
            </c:if>
            

            <c:set var="shoppingcart_xslt">
                <c:import url="shoppingcart_checkout_xslt.xsl"/>
            </c:set> 
            <x:transform xslt="${shoppingcart_xslt}">
            <checkout>
                <pcshop:shoppingcart/>
                <name>${profile.name}</name>
                <address>${profile.street}</address>
                <zip>${profile.zip}</zip>
                <city>${profile.city}</city>
            </checkout>
        </x:transform>

<jsp:include page="/includes/footer.jsp" />



