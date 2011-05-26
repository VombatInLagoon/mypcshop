<%-- 
    Document   : product
    Created on : May 7, 2011, 4:43:33 PM
    Author     : amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="business.*, tags.*" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@taglib prefix="pcshop" uri="/WEB-INF/pcshop.tld"%>

<jsp:include page="includes/header.html" />
<jsp:include page="includes/column_left_home.jsp" />



<td width = "840" valign="top">    

<h2> The Current List of Products </h2>

<jsp:useBean id="productList" class="business.ProductListBean"  scope="application">
    Error, the Bean is not created!
</jsp:useBean>

<c:set var="productListWareHouse_xslt">
   <c:import url="productListWareHouse_xslt.xsl"/>
</c:set>

    <form action="admin">
        <x:transform xslt="${productListWareHouse_xslt}">
            <jsp:getProperty name="productList" property="xml"/>
        </x:transform>
        <input type="hidden" name ="action" value="addProduct"/>
    </from>

<jsp:include page="includes/footer.jsp" />