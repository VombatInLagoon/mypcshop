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
<jsp:include page="includes/header.html" />
<jsp:include page="includes/column_left_home.jsp" />



<td width ="840" valign="top"> 
    
    <jsp:useBean id="compList" class="business.CompListBean"  scope="application">

        Error, the bean should have been created in the servlet!
    </jsp:useBean>
     <h2>The list of available components in the warehouse!</h2>
    <p></p>
    <p></p>
    

        
        
    <c:set var="complist_xslt">
        <c:import url="complist_xslt.xsl"/>
    </c:set>

    <x:transform xslt="${complist_xslt}">
        
       <jsp:getProperty name="compList" property="xml"/>

    </x:transform>
    
    
</td>


<jsp:include page="includes/footer.jsp" />