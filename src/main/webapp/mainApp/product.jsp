<%-- 
    Document   : product
    Created on : May 7, 2011, 4:43:33 PM
    Author     : Amin
--%>
<% if (request.getRemoteUser() != null){ %>
<jsp:include page="../includes/headerlogout.html" />
<%}else {%>
<jsp:include page="../includes/header.html" />
<%}%>
<jsp:include page="../includes/column_left_home.jsp" />

<td align ="center"> 
    
    <h2>Please select the product type!</h2>
    <p></p>
    <p></p>
    
    <a href="shop?action=show"> <img src="../images/pc.jpeg" /></a>


     <img src="../images/laptop.jpg" />
</td>


<jsp:include page="../includes/footer.jsp" />