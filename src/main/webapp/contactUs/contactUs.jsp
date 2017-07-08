<%-- 
    Document   : contactUs
    Created on : Jun 5, 2011, 7:52:31 PM
    Author     : amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.html" />
<jsp:include page="../includes/column_left_home.jsp" />

<td valign="top" class="contact">
    <form action=contactUs method="post">
        <table width="450px">
            </tr>
            <tr>
            <td valign="top">
            <label for="first_name">First Name *</label>
            </td>
            <td valign="top">
                <input  type="text" name="first_name" maxlength="50" size="30">
            </td>
            </tr>

            <tr>
            <td valign="top"">
        <label for="last_name">Last Name *</label>
        </td>
        <td valign="top">
            <input  type="text" name="last_name" maxlength="50" size="30">
        </td>
        </tr>
        <tr>
        <td valign="top">
        <label for="email">Email Address *</label>
        </td>
        <td valign="top">
            <input  type="text" name="email" maxlength="80" size="30">
        </td>

        </tr>
        <tr>
        <td valign="top">
        <label for="telephone">Telephone Number</label>
        </td>
        <td valign="top">
            <input  type="text" name="telephone" maxlength="30" size="30">
        </td>
        </tr>
        <tr>
        <td valign="top">
        <label for="comments">Comments *</label>
        </td>
        <td valign="top">
            <textarea  name="comments" maxlength="1000" cols="30" rows="6"></textarea>
        </td>

        </tr>
        <tr>
        <td colspan="2" style="text-align:center">
            
            <input type="button" value="Submit" onClick ="validate(this.form)">  
        </td>
        </tr>
    </table>
</form>

<script language="javascript">
    function validate(form){
        if (form.first_name.value ==""){
            alert("Please fill in your name");
            form.first_name.focus();
                                   
        }
        else if(form.last_name.value==""){
            alert("Please fill in your last name ");
            form.last_name.focus();
        }
        else if(form.email.value==""){
            alert("Please fill in your email address ");
            form.email.focus();
        }
        else if(form.comments.value==""){
            alert("Please fill in your comments in the comment box ");
            form.comments.focus();
        }
        else {
            form.submit();
        }
    }
</script>
    
<jsp:include page="/includes/footer.jsp" />