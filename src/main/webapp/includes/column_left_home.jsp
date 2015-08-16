<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%
    String scheme      = request.getScheme();
    String serverName  = request.getServerName();
    int serverPort     = request.getServerPort();
    String contextPath = request.getContextPath();
    String host = scheme + "://" + serverName + ":" + serverPort + contextPath;
%>

<td width="160"  valign="top" bgcolor="#FFFFCC">
  <p>
  <br><br>
  
  <a href="<%=host %>">
    Home
  </a><br><br>
    
    <a href="<%=host%>/mainApp/product.jsp">
        List of Products
    </a><br><br>
    
    <a href="<%=host%>/login/loginPageProfile.jsp">
        Edit Your Profile
    </a><br><br>
    <a href="<%=host%>/contactUs/contactUs.jsp">
        Contact Customer Service
    </a>
    <%-- The Admin link is for testing only
         and should be deleted for production environements --%>
    <br><br>
    
    <c:if test = "${pageContext.request.remoteUser == null}">
    <a href="<%=host%>/administrator/login?action=login">
        Administrator Area
    </a>
        
    </c:if>
   
    
    </p>
</td>
