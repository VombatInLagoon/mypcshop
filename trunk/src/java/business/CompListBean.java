/*
 * CompListBean.java
 *
 */
package business;

import java.util.*;
import java.sql.*;
import java.io.*;
/**
 *
 * @author  Amin khorsandi
 */
public class CompListBean {
    
    private Collection compList;
    private String url=null;
    private int productID = 0;

    // this constructor is not really used in the application
    // but is here for testing purpose

    public CompListBean() throws Exception{
      this(
          "jdbc:mysql://localhost/pcshop?user=root&password=sesame");
    }
    
    /** Creates a new instance of BookListBean */

    public CompListBean(String _url) throws Exception {
        url=_url;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //ResultSet ts = null;
        compList = new ArrayList();    // a list
        try{
            
	    // get a database connection and load the JDBC-driver

            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
	    // create SQL statements to load the components into the list
	    // each component is a ComponentBean object

            stmt = conn.createStatement();
            String sql="SELECT PRODUCT_ID, COMPONENT.COMPONENT_ID, NAME ,";
            sql += "PRICE, DESCRIPTION  FROM COMPONENT,";
            sql += "COMP_PROD WHERE COMPONENT.COMPONENT_ID = COMP_PROD.COMPONENT_ID ";
            
            //String sql = "select brand from PRODUCT where PRODUCT_ID = " + sp ;
            
            
            
            rs= stmt.executeQuery(sql);
       
	    // analyze the result set

            while(rs.next()){
                
                ComponentBean cb = new ComponentBean();
                
                cb.setId(rs.getInt("COMPONENT_ID"));
                cb.setPId(rs.getInt("PRODUCT_ID"));
                cb.setName(rs.getString("NAME"));
               
                cb.setPrice(rs.getInt("PRICE"));
                cb.setDescription(rs.getString("DESCRIPTION"));
                compList.add(cb);
                
            }
        
        }
        catch(SQLException sqle){
            throw new Exception(sqle);
        }

	// note the we always try to close all services
	// even if one or more fail to close
	
        finally{
 	    try{
              rs.close();
              //ts.close();
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
    
    
    
    public CompListBean(String _url,Integer _int) throws Exception {
        url=_url;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //ResultSet ts = null;
        int tmp = 0;
        compList = new ArrayList();    // a list
        try{
            
	    // get a database connection and load the JDBC-driver

            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
	    // create SQL statements to load the components into the list
	    // each component is a ComponentBean object

            stmt = conn.createStatement();
            String sql="SELECT COMPONENT.COMPONENT_ID, NAME ,";
            sql += "PRICE, DESCRIPTION,STOCK_NUM  FROM COMPONENT";
            
            
            //String sql = "select brand from PRODUCT where PRODUCT_ID = " + sp ;
            
            
            
            rs= stmt.executeQuery(sql);
       
	    // analyze the result set

            while(rs.next()){
                
                ComponentBean cb = new ComponentBean();
                
                cb.setId(rs.getInt("COMPONENT_ID"));
                cb.setPId(tmp);
                cb.setName(rs.getString("NAME"));
                cb.setStockNum(rs.getInt("STOCK_NUM"));
                cb.setPrice(rs.getInt("PRICE"));
                cb.setDescription(rs.getString("DESCRIPTION"));
                compList.add(cb);
                
            }
        
        }
        catch(SQLException sqle){
            throw new Exception(sqle);
        }

	// note the we always try to close all services
	// even if one or more fail to close
	
        finally{
 	    try{
              rs.close();
              //ts.close();
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
    
    public CompListBean(String _url,String comp) throws Exception {
        
        url=_url;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //ResultSet ts = null;
        compList = new ArrayList();    // a list
        try{
            
	    // get a database connection and load the JDBC-driver

            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
	    // create SQL statements to load the components into the list
	    // each component is a ComponentBean object

            stmt = conn.createStatement();
            String sql="SELECT COMPONENT_ID, DESCRIPTION FROM COMPONENT WHERE COMPONENT.NAME ='"+comp+"'";
            
            //String sql = "select brand from PRODUCT where PRODUCT_ID = " + sp ;
            
            
            
            rs= stmt.executeQuery(sql);
       
	    // analyze the result set

            while(rs.next()){
                
                ComponentBean cb = new ComponentBean();
                
                cb.setId(rs.getInt("COMPONENT_ID"));
                //cb.setPId(rs.getInt("PRODUCT_ID"));
                //cb.setName(rs.getString("NAME"));
               
                //cb.setPrice(rs.getInt("PRICE"));
                cb.setDescription(rs.getString("DESCRIPTION"));
                compList.add(cb);
                
            }
        
        }
        catch(SQLException sqle){
            throw new Exception(sqle);
        }

	// note the we always try to close all services
	// even if one or more fail to close
	
        finally{
 	    try{
              rs.close();
              //ts.close();
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
    
    
    // return the booklist
    
    public java.util.Collection getProductList() {
        return compList;
    }
    
    
    public int getProductID() {
        return productID;
    }
    
    public void setProductID(int _pid) {
        productID = _pid;
    }
    
    // create an XML document from the complist 
    
    public String getXml() {
        
        ComponentBean cb=null;
        Iterator iter = compList.iterator();
        StringBuffer buff = new StringBuffer();
        
        buff.append("<complist>");
        while(iter.hasNext()){
            cb=(ComponentBean)iter.next();
            buff.append(cb.getXml());
        }
        buff.append("</complist>");        
        
        return buff.toString();
    }
    

    // search for a component by component ID

    public ComponentBean getById(int id) {
	ComponentBean cb = null;
	Iterator iter = compList.iterator();
        
	while(iter.hasNext()){
	    cb=(ComponentBean)iter.next();
	    if(cb.getId()== id){
                return cb;
	    }
	}
	return null;
    }
    
     public String getXMLByProductID(String pid) {
	ComponentBean cb = null;
        Iterator iter = compList.iterator();
        StringBuffer bufftmp = new StringBuffer();
        bufftmp.append("<complist>");
        while(iter.hasNext()){
            cb=(ComponentBean)iter.next();
            if(cb.getPId()== Integer.parseInt(pid)){
                 bufftmp.append(cb.getXml());
	    }
            
        }
        bufftmp.append("</complist>");        
        
        return bufftmp.toString();
	
    }
    
     /**
      * This method has been added to support the add new component 
      * Administrative task
      * @param id
      * @param amount
      * @throws Exception 
      */
     public void setCount(int id,int amount) throws Exception{
         
        Connection conn = null;
        Statement stmt = null;
        
       
        
        try{
            
	    // get a database connection and load the JDBC-driver

            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/pcshop?user=root&password=sesame");
            
	    // create SQL statements to load the components into the list
	    // each component is a ComponentBean object

           
            // first we get the current amount of the component 
            // using the following query
            
            conn.setAutoCommit(false);
            
            
            stmt = conn.createStatement();
            
            String sql="UPDATE COMPONENT SET STOCK_NUM = (STOCK_NUM+9),";
            sql += "WHERE COMPONENT_ID = 'id'";
            
  


            //String sql = "select brand from PRODUCT where PRODUCT_ID = " + sp ;
            
            
            
            stmt.executeUpdate(sql);
       
	    // analyze the result set

            
          conn.commit();
      //out.println("Order successful!  Thanks for your business!");
    }
    catch (Exception e) {
      // Any error is grounds for rollback
      try {
        conn.rollback();
      }
      catch (SQLException ignored) { }
      //out.println("Order failed. Please contact technical support.");
    }
    finally {
      // Clean up.
      try {
        if (conn != null) conn.close();
      }
      catch (SQLException ignored) { }
    }
         
     }
    
    
    
    // a main used for testing, remember that a bean can be run
    // without a container

 /*   public static void main(String[] args){
        try{
	    CompListBean clb = new CompListBean();
	    System.out.println(clb.getXml());
        }
        catch(Exception e){
	    System.out.println(e.getMessage());
        }
    } */
}

