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
    <c:when test="${sessionScope.logedinUser != null}">
        <jsp:include page="../includes/headerlogoutadmin.html" />
    </c:when>
    
    <c:otherwise>
        <jsp:include page="../includes/header.html" />
    </c:otherwise>
</c:choose>

<jsp:include page="../includes/column_left_home.jsp" />



<td width = "840" valign="top">
    
    
     <jsp:useBean id="productList" class="business.ProductListBean"  scope="application">

        Error, the bean should have been created in the servlet!
    </jsp:useBean>

    <jsp:useBean id="compList" class="business.CompListBean"  scope="application">

        Error , the bean should have been created in the servlet!
    </jsp:useBean>


    <h2> The List of Components of : <%= productList.getNameById(request.getParameter("productid")) %> </h2>
    

    <form method="post" action="adminProduct.jsp">
        <input type="submit" name="back" value="Back" />
    </form>
    
    
    <c:set var="complist_xslt">
        <c:import url="complist_xslt_admin.xsl"/>
    </c:set>



    <x:transform xslt="${complist_xslt}">

        <%= compList.getXMLByProductID(request.getParameter("productid"))%>

    </x:transform>
    
<jsp:include page="../includes/footer.jsp" />