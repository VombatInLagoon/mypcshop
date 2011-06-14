<%-- 
    Document   : addProduct
    Created on : May 25, 2011, 8:35:34 PM
    Author     : amin & soode
    This page is supposed to produce a interface to make the admin able to add new 
    Products into the pcshop data-base by selecting its components from
    availabel set of components.


--%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="business.*,java.util.*" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>



<!-- This will check that if the user is log in and shows the logout option! --> 
<c:choose>
    <c:when test="${sessionScope.logedinUser != null}">
        <jsp:include page="../includes/headerlogoutadmin.html" />
    </c:when>
    
    <c:otherwise>
        <jsp:include page="../includes/header.html" />
    </c:otherwise>
</c:choose>

<jsp:include page="../includes/column_left_home.jsp" />


<td width = "840" valign="top">    

    <h1>Please Enter The New Product Configuration. </h1>

    <table border=0>
        <form action=admin>
            <tr>
                <td>Product Brand:</td>
                <td> <input type="text" name="brand" value="" ></td>
            </tr>


            <tr>
                <td>Description:</td>
                <td> <input type = "text" name = "name"
                            value = "" ></td>
            </tr>
            <tr>
                <td>Select MB Type:</td> 
                <td>
                    <select name="Mb">

                        <%
                            ArrayList clmb = (ArrayList) request.getAttribute("complistmb");
                            ArrayList<ComponentBean> itemsmb = clmb;
                            for (ComponentBean item : itemsmb) {
                        %>
                        
                        <option value="<%=item.getId()%>" name="Mb" >
                            <%=item.getDescription()%>
                        </option> 
                            

                        <%}%>
                            
                    </select>  
                           
                        
                        

                </td>
            </tr>
            <tr>
                <td> Select RAM Type & Amount: </td>
                <td>
                    <select name="Ram">

                        <%
                            ArrayList clram = (ArrayList) request.getAttribute("complistram");
                            ArrayList<ComponentBean> itemsram = clram;
                            for (ComponentBean item : itemsram) {
                        %>

                        <option value="<%=item.getId() %>" name="Ram" >
                            <%=item.getDescription()%>
                        </option> 


                        <%}%>

                    </select>  
                        <input name="ramAmount" type="text" size="2"></input>


                </td>
            </tr>
            <tr>
                <td>Select VGA Type & Amount:</td>
                <td> 
                    <select name="Vga">

                        <%
                            ArrayList clvga = (ArrayList) request.getAttribute("complistvga");
                            ArrayList<ComponentBean> itemsvga = clvga;
                            for (ComponentBean item : itemsvga) {
                        %>

                        <option value="<%=item.getId()%>" name="Vga" >
                            <%=item.getDescription()%>
                        </option> 


                        <%}%>

                    </select>  
                            <input name="vgaAmount" type="text" size="2"></input>
                </td>
            </tr>

            <tr>
                <td>Select CPU Type & Amount:</td>
                <td> 

                    <select name="Cpu">

                        <%
                            ArrayList clcpu = (ArrayList) request.getAttribute("complistcpu");
                            ArrayList<ComponentBean> itemscpu = clcpu;
                            for (ComponentBean item : itemscpu) {
                        %>

                        <option value="<%=item.getId()%>" name="Cpu" >
                            <%=item.getDescription()%>
                        </option> 


                        <%}%>
                        
                    </select>  
                            <input name="cpuAmount" type="text" size="2"></input>
                </td>
            </tr>

            <tr>
                <td>Select H.D.D Type & Amount:</td>
                <td>

                    <select name="Hdd">

                        <%
                            ArrayList clhdd = (ArrayList) request.getAttribute("complisthdd");
                            ArrayList<ComponentBean> itemshdd = clhdd;
                            for (ComponentBean item : itemshdd) {
                        %>

                        <option value="<%=item.getId()%>" name="HDD" >
                            <%=item.getDescription()%>
                        </option> 


                        <%}%>

                    </select>         
                            <input name="hddAmount" type="text" size="2" ></input>
                </td>

            </tr> 

            <tr>
                <td>Select Monitor Type & Amount:</td>
                <td> 
                    <select name="Monitor">

                        <%
                            ArrayList clmonitor = (ArrayList) request.getAttribute("complistmonitor");
                            ArrayList<ComponentBean> itemsmonitor = clmonitor;
                            for (ComponentBean item : itemsmonitor) {
                        %>

                        <option value="<%=item.getId()%>" name="Monitor" >
                            <%=item.getDescription()%>
                        </option> 


                        <%}%>

                    </select>  

                            <input name="monitorAmount" type="text" size="2"></input>
                </td>
            </tr>

            <tr>
                <td>Select Optical Drive Type & Amount:</td>
                <td> 
                    <select name="Optic">

                        <%
                            ArrayList cloptic = (ArrayList) request.getAttribute("complistoptic");
                            ArrayList<ComponentBean> itemsoptic = cloptic;
                            for (ComponentBean item : itemsoptic) {
                        %>

                        <option value="<%=item.getId() %>" name="Optic" >
                            <%=item.getDescription()%>
                        </option> 


                        <%}%>

                    </select>  
                        <input name="opticAmount" type="text" size="2"></input>
                        
                </td>
            </tr>   
            
   
    
      </table> 
     <input type="hidden" name ="action" value="addNewProduct">
     <input type="button" value="Submit" onClick ="validate(this.form)"> 
    
</form>
                      
                     

<%-- a javascript code to validate the data on the form (client side)--%>  
<script language="javascript">
    function validate(form){
        if (form.brand.value ==""){
            alert("Please fill in the brand of the product");
            form.brand.focus();
                                   
        }
        else if(form.name.value==""){
            alert("Please fill in the name of the product");
            form.name.focus();
        }
        else {
            form.submit();
        }
    }
</script>

<jsp:include page="../includes/footer.jsp" />