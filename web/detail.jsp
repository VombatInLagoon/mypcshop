<%@page contentType="text/html" pageEncoding="UTF-8" import="business.*, tags.*" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@taglib prefix="pcshop" uri="/WEB-INF/pcshop.tld"%>


<jsp:useBean id="component" class="business.ProductBean" scope="request">
    Error, the bean should have been created in the servlet!
</jsp:useBean>
    

<c:set var="componentdetail_xslt">
   <c:import url="componentdetail_xslt.xsl"/>
</c:set> 

<x:transform xslt="${componentdetail_xslt}">
    <detailpage>
      <jsp:getProperty name="component" property="xml"/>
    </detailpage>
</x:transform>


