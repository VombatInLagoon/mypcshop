<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


<jsp:include page="/includes/header.html" />
<jsp:include page="/includes/column_left_home.jsp" />


<!-- start the middle column -->

<td valign="top" class ="onlineshop">
    <form method="post" action='<%= response.encodeURL("j_security_check")%>' >
        <table border="0" cellspacing="5" align="left">
            <tr>
                <td colspan="2"  align="left"><h2>Log in to continue shopping </h2></td>
            </tr>
            <tr>
                <th align="left">Username:</th>
                <td align="left"><input type="text" name="j_username"></td>
            </tr>
            <tr>
                <th align="left">Password:</th>
                <td align="left"><input type="password" name="j_password"></td>
            </tr>
            <tr>
                <th align="left"><input type="submit" value="Log In"></th>
                <td align="left"><input type="reset"></td>
            </tr>
            <tr>
                <td><a href="shop?action=newuser">register here!</a>
                </td>
            </tr>
        </table>
    </form>

<jsp:include page="/includes/footer.jsp" />