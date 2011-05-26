<%-- 
    Document   : admin
    Created on : May 11, 2011, 10:19:39 PM
    Author     : amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/includes/header.html" />
<jsp:include page="/includes/column_left_home.jsp" />

<td>
    <h2>Welcome to the Admin page<br>Please choose what to do!</br></h2>
</td>

<td>

    
    <form action="admin" method="post">
        <br></br>
        <br></br>
        
        <input type="submit" value="Go to the Components Page" align ="right"/>
        <input type="hidden" name="action" value="adminPageComponent"/>
        
        
    </form>

    
    <form action="admin" method="post">
        <br></br>
             
        <input type="submit" value="Add new products!" align ="right"/>
        <input type="hidden" name="action" value="adminPageProduct"/>
    </form>

</td>
    
 <jsp:include page="/includes/footer.jsp" />