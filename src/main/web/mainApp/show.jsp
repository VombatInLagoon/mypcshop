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

<h2> Available Set of Components </h2>
<jsp:useBean id="compList" class="business.CompListBean"  scope="application">
    Error, the bean should have been created in the servlet!
</jsp:useBean>

<c:set var="complist_xslt">
   <c:import url="complist_xslt.xsl"/>
</c:set>


<x:transform xslt="${complist_xslt}">
    <jsp:getProperty name="compList" property="xml"/>
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


<%-- <jsp:include page="/includes/column_right_news.jsp" flush="true" /> --%>
<jsp:include page="../includes/footer.jsp" />
