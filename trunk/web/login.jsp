<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="/pcShop2011/pcshop.css">
<body>
<form method="POST" action='<%= response.encodeURL("j_security_check") %>' >
  <table border="0" cellspacing="5" align="center">
    <tr>
      <td colspan="2"  align="center"><h1>Log in to the PcShop</td>
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
      <td align="right"><input type="submit" value="Log In"></td>
      <td align="left"><input type="reset"></td>
    </tr>
    <tr>
     <td><a href="shop?action=newuser">Not a member? Please Register Here</a>      
     </td>
    </tr>
  </table>

</form>
</body>
</html>
