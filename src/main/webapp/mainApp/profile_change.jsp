<%-- 
    Document   : display_email_entry
    Created on : Jun 6, 2011, 1:01:21 AM
    Author     : Amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<% if (request.getRemoteUser() != null){ %>
<jsp:include page="../includes/headerlogout.html" />
<%}else {%>
<jsp:include page="../includes/header.html" />
<%}%>
<jsp:include page="../includes/column_left_home.jsp" />

<td valign="top" >
    <h3>Your Profile has been updated successfully!</h3>
</td>
<jsp:include page="../includes/footer.jsp" />