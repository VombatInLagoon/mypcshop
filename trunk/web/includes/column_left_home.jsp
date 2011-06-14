<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<td width="160"  valign="top" bgcolor="#FFFFCC">
  <p>
  <br><br>
  
  <a href="http://localhost:8080/pcShop2011">
    Home
  </a><br><br>
    
    <a href="http://localhost:8080/pcShop2011/mainApp/product.jsp">
        List of Products
    </a><br><br>
    
    <a href="http://localhost:8080/pcShop2011/login/loginPageProfile.jsp">
        Edit Your Profile
    </a><br><br>
    <a href="http://localhost:8080/pcShop2011/contactUs/contactUs.jsp">
        Contact Customer Service
    </a>
    <%-- The Admin link is for testing only
         and should be deleted for production environements --%>
    <br><br>
    
    <c:if test = "${pageContext.request.remoteUser == null}">
    <a href="http://localhost:8080/pcShop2011/administrator/login?action=login">
        Administrator Area
    </a>
        
    </c:if>
   
    
    </p>
</td>
