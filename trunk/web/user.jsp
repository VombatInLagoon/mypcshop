<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@taglib prefix="webstore" uri="/WEB-INF/pcshop.tld"%>

<html>
    <head><title>New User</title>
    <link rel="stylesheet" href="/pcShop2011/pcshop.css">
    </head>
    <center>
        <body>
            <h1>Please create a profile here. 
            </h1>
            ${(passwordInvalid != null)?(passwordInvalid):null}
            <table border=0>
                <form action=shop?action=usercreate method=post>
                    <tr>
                        <td>Username:</td>
                        <td> <input type="text" name="user" value="${profile.user}" ></td>
                    </tr>
                    <tr>
                        <td>New password:</td> 
                        <td><input type="password" name="password" 
                                   value = ${profile.password}></td>
                    </tr>
                    <tr>
                        <td>Verify password:</td> 
                        <td><input type="password" name="password2"
                                   value = ${profile.password} ></td>
                    </tr>
                    <tr>
                        <td>Name:</td>
                        <td> <input type = "text" name = "name"
                                    value = "${profile.name}" ></td>
                    </tr>
                    <tr>
                        <td>Street Address:</td> 
                        <td><input type = "text" name = "street"
                                   value = "${profile.street}"></td>
                    </tr>
                    <tr>
                        <td>Zipcode:</td>
                        <td><input type = "text" name = "zip"
                                   value = "${profile.zip}"></td>
                    </tr>
                    <tr>
                        <td>City:</td>
                        <td> <input type = "text" name = "city"
                                    value = "${profile.city}"></td>
                    </tr>
                    <tr>
                        <td>Country:</td>
                        <td> <input type = "text" name = "country"
                                    value = "${profile.country}"></td>
                    </tr>
                    <tr>
                        <td>Select roles:</td>
                        <c:forEach var="next" items="${roles}">
                        <tr>
                        <c:if test="${next.key != 'admin' && next.key != 'manager-script'}">
                            <c:if test="${next.value == true}">
                                <td><input type="checkbox" name="${next.key}" checked> ${next.key}    </td>
                                </c:if>
                                <c:if test="${next.value == false}">
                                <td><input type="checkbox" name="${next.key}"> ${next.key}    </td>
                                </c:if>
                        </c:if>
                        </tr>
                    </c:forEach>
            </table> 
                    <input type="button" value="Go" onClick="validate(this.form)">
            </form>

                    
                    
<script language="JavaScript">
function validate(form) 
{
    if (form.user.value=="") 
    {
            alert("Please fill in your user name");
            form.user.focus();
    }
    else if (form.password.value=="") 
    {
            alert("Please fill in your password");
            form.password.focus();
    }
    else if (form.password2.value=="") 
    {
            alert("Please re-enter your password");
            form.password2.focus();
    }
    else if (form.name.value=="") 
    {
            alert("Please fill in your name");
            form.name.focus();
    }
    else if (form.city.value=="") 
    {
            alert("Please fill in your city name");
            form.city.focus();
    }
    else if (form.street.value=="") 
    {
            alert("Please fill in your address");
            form.street.focus();
    }
    else if (form.zip.value=="") 
    {
            alert("Please fill in your zip/postal code");
            form.zip.focus();
    }
    else if (form.country.value=="") 
    {
            alert("Please fill in your country");
            form.country.focus();
    }
    else 
    {
           form.submit();
    }
}
</script>

        </body>
    </center>
</html>
