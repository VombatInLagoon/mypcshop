<% if (request.getAttribute("currentUser") != null){ %>
<jsp:include page="/includes/headerlogout.html" />
<%}else {%>
<jsp:include page="/includes/header.html" />
<%}%>

<jsp:include page="/includes/column_left_home.jsp" />

<!-- start the middle column -->

<td valign="top"   width="40%">
    <h2>Welcome!</h2>
    <p>
        This is an online shopping web site. Through this e-commerce site you 
        can easily find what you need.We offer a wide variety of Personal Computers
        with unique configuration,each suitable for one specific task.
        We offer up-scale solutions for Gamers and also ordinary home and office 
        uses.Feel free and navigate trough our list of products.
    </p>
</td>
<td valign="top" class ="onlineshop" width="40%">

</td>

<!-- end the middle column -->

<%-- <jsp:include page="/includes/column_right_news.jsp" flush="true" /> --%>
<jsp:include page="/includes/footer.jsp" />