<%--
    Document   : product
    Created on : May 7, 2011, 4:43:33 PM
    Author     : amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="business.*, tags.*" %>

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

    <jsp:useBean id="productList" class="business.ProductListBean"  scope="application">

        Error, the bean should have been created in the servlet!
    </jsp:useBean>
        

    <jsp:useBean id="compList" class="business.CompListBean"  scope="application">

        Error, the bean should have been created in the servlet!
    </jsp:useBean>



    
    

   
    <h2> The Product List </h2>

    <c:set var="productlist_xslt">
        <c:import url="productlist_xslt.xsl"/>
    </c:set>


    <x:transform xslt="${productlist_xslt}">
        <jsp:getProperty name="productList" property="xml"/>
    </x:transform>


    <h2> The configuration of the  : <%= productList.getNameById(request.getParameter("productid")) %> </h2>
   



    <c:set var="complist_xslt_brif">
        <c:import url="complist_xslt_brif.xsl"/>
    </c:set>



    <x:transform xslt="${complist_xslt_brif}">

        <%= compList.getXMLByProductID(request.getParameter("productid"))%>

    </x:transform>
    
    
    
    <c:set var="shoppingcart_xslt">
        <c:import url="shoppingcart_xslt.xsl"/>
    </c:set>

    <x:transform xslt="${shoppingcart_xslt}">
        <pcshop:shoppingcart/>
    </x:transform>

</td>


<jsp:include page="includes/footer.jsp" />