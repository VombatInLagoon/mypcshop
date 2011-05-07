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
        compList = new ArrayList();    // a list
        try{
            
	    // get a database connection and load the JDBC-driver

            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
	    // create SQL statements to load the books into the list
	    // each componenet is a ComponentBean object

            stmt = conn.createStatement();
            String sql="SELECT COMPONENT_ID, NAME , S_NAME AS SUPPLIER_NAME, ";
            sql += "PRICE, DESCRIPTION FROM COMPONENT,";
            sql += "SUPPLIERS WHERE COMPONENT.SUPPLIER_ID=SUPPLIERS.SUPPLIER_ID";
            rs= stmt.executeQuery(sql);
            
	    // analyze the result set

            while(rs.next()){
                
                ComponentBean cb = new ComponentBean();
                
                cb.setId(rs.getInt("COMPONENT_ID"));
                cb.setName(rs.getString("NAME"));
                cb.setSupplierName(rs.getString("SUPPLIER_NAME"));
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
    
    java.util.Collection getProductList() {
        return compList;
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
    
    // a main used for testing, remember that a bean can be run
    // without a container

    public static void main(String[] args){
        try{
	    CompListBean clb = new CompListBean();
	    System.out.println(clb.getXml());
        }
        catch(Exception e){
	    System.out.println(e.getMessage());
        }
    }
}

