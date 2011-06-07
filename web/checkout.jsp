<%@page contentType="text/html" pageEncoding="UTF-8" import="business.*, tags.*" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@taglib prefix="pcshop" uri="/WEB-INF/pcshop.tld"%>

<html>
    <head><title>CheckOut</title>
        <link rel="stylesheet" href="/pcShop2011/pcshop.css">
    </head>
    <center>
        <body>
            
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

    </body>
</center>
</html>



