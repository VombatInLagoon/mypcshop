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

    <h2> You Have Selected The Product : ${param.selected} </h2>
    
    <h2> Available Set of Components </h2> 
    <jsp:useBean id="compList" class="business.CompListBean"  scope="application">
        
        Error, the bean should have been created in the servlet!
    </jsp:useBean>

    <c:set var="complist_xslt">
        <c:import url="complist_xslt.xsl"/>
    </c:set>

        
                
    <x:transform xslt="${complist_xslt}">
        
        <%= compList.getXMLByProductID(request.getParameter("selectedProduct")) %>
        
    </x:transform>



    <c:set var="shoppingcart_xslt">
        <c:import url="shoppingcart_xslt.xsl"/>
    </c:set> 

    <x:transform xslt="${shoppingcart_xslt}">
        <pcshop:shoppingcart/>
    </x:transform>

</td>
<c:if test="${sessionScope.currentUser != null}">
    <form action=shop?action=profile method=post>
        <input type="submit" value="Update Profile">
    </form>

    <form action=shop?action=logout method=post>
        <input type="submit" value="Logout">
    </form>
</c:if>





<jsp:include page="includes/footer.jsp" />