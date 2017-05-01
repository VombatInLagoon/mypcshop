/*
 * ProfileBean.java
 *
 */
package domain;

import java.util.*;
import java.sql.*;

/**
 *
 * @author  Olle Eriksson
 * (Borrowed from the bookshop application)
 */
public class Profile {
    
    // create a profile bean

    static String url= null;
    private String user;
    private String password;
    private String name;
    private String street;
    private String zip;
    private String city;
    private String country;
    private HashMap<String,Boolean> role = null;
    
    static Connection currentCon = null;
    
    private boolean valid;

    
    public Profile() {
        this("jdbc:mysql://localhost/pcshop?user=root&password=sesame");
        
    }

    
    // constructor, set the database URL
    public Profile(String _url) {
        url=_url;
    }

    public HashMap<String, Boolean> getRole() {
	return role;
    }

    public void setRole(HashMap<String,Boolean> _role) {
        role = _role;
    }


    public String getUser() {
	return user;
    }

    public void setUser(String _user) {
        user = _user;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String _pass) {
        password = _pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String _street) {
        street = _street;
    }

    public String getZip() {
        return zip;
    }


    public void setZip(String _zip) {
         zip = _zip;
    }
    public String getCity() {
        return city;
    }


    public void setCity(String _city) {
        city = _city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String _country) {
        country =  _country;
    }
    
    public boolean getValid() { 
        return valid; 
    } 
    
    public void setValid(boolean _valid) { 
        valid = _valid; 
    } 
    
    
    

    // populate a profile instance from the database

    public void populate(String u)  throws Exception{
        Connection conn =null;
        Statement stmt = null;
        ResultSet rs=null;
 
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            String sql;
            sql ="SELECT * from  USERS where USER_NAME = " +
                                             "'" + u + "'";
            rs= stmt.executeQuery(sql);

	    // analyze the result set
	    
	    rs.next();
            user= u;
            password = rs.getString("USER_PASS");
            name = rs.getString("NAME");
            street = rs.getString("STREET_ADDRESS");
            zip = rs.getString("ZIP_CODE");
            city = rs.getString("CITY");
	    country = rs.getString("COUNTRY");
	}   
	catch(SQLException sqle){
            throw new Exception(sqle);
	}
        finally{
 	    try{
		rs.close();
            }
            catch(Exception e) {}
            try{
		stmt.close();
            }
	    catch(Exception e) {}
            try {
		conn.close();
            }
            catch(Exception e){}
        }
    }
    
    // return all roles that we have, return a MAP with names
    // and the flag set to false

     public HashMap<String,Boolean> getRoles() throws Exception {

        Connection conn =null;
        Statement stmt = null;
        ResultSet rs=null;
        HashMap<String,Boolean> role = new HashMap<String,Boolean>();

        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            conn=DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            String sql;
            sql ="SELECT * from USER_ROLES";
            rs= stmt.executeQuery(sql);
            while (rs.next()) {
		String r = rs.getString("ROLE_NAME");
		if(!role.containsKey(r))role.put(r,false);
            }
            return role;
	}   
	catch(SQLException sqle){
            throw new Exception(sqle);
	}
        finally{
 	    try{
		rs.close();
            }
            catch(Exception e) {}
            try{
		stmt.close();
            }
	    catch(Exception e) {}
            try {
		conn.close();
            }
            catch(Exception e){}
        }
     }
    
    // test if a user is in our tables

    public boolean testUser(String u) throws Exception {
	
        Connection conn =null;
        Statement stmt = null;
        ResultSet rs=null;
  
        try{
	    Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            String sql;
            sql ="SELECT NAME from USERS WHERE USER_NAME = " + "'" + u + "'";
            rs= stmt.executeQuery(sql);

	    // check if we got any result set
	    
	    boolean b = rs.next();
            return b;
       }   
	catch(SQLException sqle){
            throw new Exception(sqle);
	}
        finally{
 	    try{
		rs.close();
            }
            catch(Exception e) {}
            try{
		stmt.close();
            }
	    catch(Exception e) {}
            try {
		conn.close();
            }
            catch(Exception e){}
        }
    }
    
    
    
    public Profile selectUser(String _name)
            throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url);
        PreparedStatement ps = null;
        ResultSet rs = null;
        String userName = _name;
        
        String query = "SELECT * FROM USERS " +
                       "WHERE USER_NAME = ?";
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            Profile up = null;
            if (rs.next())
            {
                up = new Profile();
                
                up.setUser(rs.getString("USER_NAME"));
                up.setName(rs.getString("NAME"));
                up.setStreet(rs.getString("STREET_ADDRESS"));
                up.setZip(rs.getString("ZIP_CODE"));
                up.setPassword(rs.getString("USER_PASS"));
                up.setCity(rs.getString("CITY"));
                up.setCountry(rs.getString("COUNTRY"));
             
            }
            return up;
        }
        catch (SQLException e){
            return null;
        }        
        finally
        {
             try{
		rs.close();
            }
            catch(Exception e) {}
            try{
		ps.close();
            }
	    catch(Exception e) {}
            try {
		connection.close();
            }
            catch(Exception e){}
        }
    }
    
    
    /**
     * This method is supposed to get the user name and password of the 
     * User who is trying to enter the web site. And will check to see if the
     * User is eligible to enter the site or not!
     * (Used to control the log-in in the Admin Area)
     * @param bean
     * @return 
     */
    
    
    public  static Profile loginAdmin(Profile bean) {
        //preparing some objects for connection 
        Statement stmtLogin = null;
        ResultSet rsLogin = null;
        String username = bean.getUser(); 
        String password = bean.getPassword();
        
        
        String searchQuery = "select USERS.USER_NAME , USER_PASS from USERS "
                + ", USER_ROLES where USER_ROLES.USER_NAME='"+username+"'"
               
                + "and ROLE_NAME = 'admin' and "
                + "USERS.USER_PASS ='"+password+"'";
        
        // "System.out.println" prints in the console; Normally used to trace the process 
/*        System.out.println("Your user name is " + username);
        System.out.println("Your password is " + password); 
        System.out.println("Query: "+searchQuery); */
        try { 
            //connect to DB
            Class.forName("com.mysql.jdbc.Driver");
            currentCon = DriverManager.getConnection(url);
            stmtLogin = currentCon.createStatement(); 
            rsLogin = stmtLogin.executeQuery(searchQuery); 
            boolean more = rsLogin.next(); 
            
            // if user does not exist set the isValid variable to false 
            if (!more) { 
                System.out.println("Sorry, you are not a registered user! Please sign up first");
                bean.setValid(false); 
            } //if user exists set the isValid variable to true 
            else if (more) { 
                String userName = rsLogin.getString("USER_NAME");
                //String lastName = rsLogin.getString("LastName");
                
                System.out.println("Welcome " + userName);
                bean.setUser(userName); 
                //bean.setLastName(lastName); 
                bean.setValid(true); 
            } 
        } catch (Exception ex) { 
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }  
        
        //some exception handling 
        
        finally { 
            if (rsLogin != null) { 
                try { 
                    rsLogin.close(); 
                } catch (Exception e) {} 
                rsLogin = null; 
            } 
            
            
            if (stmtLogin != null) { 
                try { 
                    stmtLogin.close(); 
                } catch (Exception e) {} 
                stmtLogin = null; 
            } 
            
            if ( currentCon != null) { 
                try { 
                    currentCon.close(); 
                } catch (Exception e) { 
                } 
                
                
                currentCon = null; 
            } 
        } 
        
        return bean; 
    
    }
    
     /**
     * This method is supposed to get the user name and password of the 
     * User who is trying to enter the web site. And will check to see if the
     * User is eligible to enter the site or not!
     * (Used to control the log-in in the Change Profile Area)
     * @param bean 
     * @return 
     */
    
    
    public  static Profile login(Profile bean) {
        //preparing some objects for connection 
        Statement stmtLogin = null;
        ResultSet rsLogin = null;
        String username = bean.getUser(); 
        String password = bean.getPassword();
        
        
        String searchQuery = "select USERS.USER_NAME , USER_PASS from USERS "
                + ", USER_ROLES where USER_ROLES.USER_NAME='"+username+"'"
                + "and USERS.USER_PASS ='"+password+"'";
        
        // "System.out.println" prints in the console; Normally used to trace the process 
        System.out.println("Your user name is " + username); 
        System.out.println("Your password is " + password); 
        System.out.println("Query: "+searchQuery); 
        try { 
            //connect to DB
            Class.forName("com.mysql.jdbc.Driver");
            currentCon = DriverManager.getConnection(url);
            stmtLogin = currentCon.createStatement(); 
            rsLogin = stmtLogin.executeQuery(searchQuery); 
            boolean more = rsLogin.next(); 
            
            // if user does not exist set the isValid variable to false 
            if (!more) { 
                System.out.println("Sorry, you are not a registered user! Please sign up first");
                bean.setValid(false); 
            } //if user exists set the isValid variable to true 
            else if (more) { 
                String userName = rsLogin.getString("USER_NAME");
                //String lastName = rsLogin.getString("LastName");
                
                System.out.println("Welcome " + userName);
                bean.setUser(userName); 
                //bean.setLastName(lastName); 
                bean.setValid(true); 
            } 
        } catch (Exception ex) { 
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }  
        
        //some exception handling 
        
        finally { 
            if (rsLogin != null) { 
                try { 
                    rsLogin.close(); 
                } catch (Exception e) {} 
                rsLogin = null; 
            } 
            
            
            if (stmtLogin != null) { 
                try { 
                    stmtLogin.close(); 
                } catch (Exception e) {} 
                stmtLogin = null; 
            } 
            
            if ( currentCon != null) { 
                try { 
                    currentCon.close(); 
                } catch (Exception e) { 
                } 
                
                
                currentCon = null; 
            } 
        } 
        
        return bean; 
    }
}

