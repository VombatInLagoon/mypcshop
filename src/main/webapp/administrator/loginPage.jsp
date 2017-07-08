<%-- 
    Document   : loginPage
    Created on : Jun 7, 2011, 10:54:36 AM
    Author     : Amin
--%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<jsp:include page="../includes/header.html" />
<jsp:include page="../includes/column_left_home.jsp" />

<td valign="top" class ="onlineshop">

    <form action="login">
        <table border="0" cellspacing="5" align="left">
            <tr>
                <td colspan="2"  align="left"><h2>Log in to the Admin Area</h2></td>
            </tr>

            <tr>
                <th align="left">Username:</th>
                <td align="left"><input type="text" name="un" required></td>
            </tr>
            <tr>
                <th align="left">Password:</th>
                <td align="left"><input type="password" name="pw" required></td>
            </tr>
            <tr>
                <th align="left"><input type="submit" value="Log In"></th>
                <td align="left"><input type="reset"></td>
                <td align="left"><input type="hidden" name="action" value="" ></td>
            </tr>
        </table>
    </form>

<jsp:include page="/includes/footer.jsp" />