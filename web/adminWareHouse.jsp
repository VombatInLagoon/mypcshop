<%-- 
    Document   : adminwareHouse
    Created on : May 15, 2011, 10:48:50 PM
    Author     : amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="business.* , tags.*"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@taglib prefix="pcshop" uri="/WEB-INF/pcshop.tld"%>

<!DOCTYPE html>
<% if (request.getRemoteUser() != null){ %>
<jsp:include page="includes/headerlogout.html" />
<%}else {%>
<jsp:include page="includes/header.html" />
<%}%>
<jsp:include page="includes/column_left_home.jsp" />



<td width ="840" valign="top"> 
    <%--
    <jsp:useBean id="compFullList" class="business.CompListBean"  scope="application">

        Error, the bean should have been created in the servlet!
    </jsp:useBean>
    --%>
    <%--
        Each time we instantiate a new object to reflect the amount of the 
        Components in the stock
    --%>
    
    <% 
        CompListBean compFullList = new CompListBean(
                "jdbc:mysql://localhost/pcshop?user=root&password=sesame", 0);
        request.setAttribute("compFullList", compFullList);
    %>
    <h2>The list of available components in the warehouse!</h2>
    <p></p>
    <p></p>
   
        
        
    <c:set var="compfulllist_xslt">
        <c:import url="compfulllist_xslt.xsl"/>
    </c:set>

    <x:transform xslt="${compfulllist_xslt}">
        
       <%= compFullList.getXml() %>

    </x:transform>
    
    
</td>


<jsp:include page="includes/footer.jsp" />