<%-- 
    Document   : product
    Created on : May 7, 2011, 4:43:33 PM
    Author     : amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="business.*, tags.*" %>


<% String message =(String) request.getAttribute("message"); %>
<% String messageEptyCart =(String) request.getAttribute("messageEptyCart"); %>        

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@taglib prefix="pcshop" uri="/WEB-INF/pcshop.tld"%>

<% if (request.getRemoteUser() != null){ %>
<jsp:include page="includes/headerlogout.html" />
<%}else {%>
<jsp:include page="includes/header.html" />
<%}%>

<jsp:include page="includes/column_left_home.jsp" />



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

    
<% if (message != null) { %>
 <p><i><%= message %></i></p> 
<%}%>
    
<c:set var="shoppingcart_xslt">
    <c:import url="shoppingcart_xslt.xsl"/>
</c:set> 
    
    
    <x:transform xslt="${shoppingcart_xslt}">
        <pcshop:shoppingcart/>
    </x:transform>
    

    
<% if (messageEptyCart != null) { %>
 <p><i><%= messageEptyCart %></i></p> 
<%}%>
    
<jsp:include page="includes/footer.jsp" />