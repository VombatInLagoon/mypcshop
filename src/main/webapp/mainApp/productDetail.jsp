<%-- 
    Document   : product
    Created on : May 7, 2011, 4:43:33 PM
    Author     : Amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="business.*, tags.*" %>



<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@taglib prefix="pcshop" uri="/WEB-INF/pcshop.tld"%>


<c:choose>
    <c:when test="${pageContext.request.remoteUser != null}">
        <jsp:include page="../includes/headerlogout.html" />
    </c:when>
    
    <c:otherwise>
        <jsp:include page="../includes/header.html" />
    </c:otherwise>
</c:choose>


<jsp:include page="../includes/column_left_home.jsp" />



<td width = "840" valign="top">    

<h2> List of Products </h2>
<jsp:useBean id="productList" class="business.ProductListBean"  scope="application">
    Error, the Bean is not created!
</jsp:useBean>

<c:set var="productlist_xslt">
   <c:import url="productlist_xslt.xsl"/>
</c:set>


<x:transform xslt="${productlist_xslt}">
    <jsp:getProperty name="productList" property="xml"/>
</x:transform>

<%-- this is to check that the entered amount by user is correct! --%>    
<h3> ${(message != null)?(message):null} </h3>  
    
<c:set var="shoppingcart_xslt">
    <c:import url="shoppingcart_xslt.xsl"/>
</c:set> 
    
    
    <x:transform xslt="${shoppingcart_xslt}">
        <pcshop:shoppingcart/>
    </x:transform>
    

<%-- If the cart is empty show the error message --%>    
<h3> ${(messageEmptyCart != null)?(messageEmptyCart):null} </h3>  


<jsp:include page="../includes/footer.jsp" />