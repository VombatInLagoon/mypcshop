/*
 * ProductListBean.java
 *
 */
package domain;

import java.util.*;
import java.sql.*;
/**
 *
 * @author  Amin khorsandi
 */
public class ProductList {
    
    private Collection productList;
    private String url=null;

    // this constructor is not really used in the application
    // but is here for testing purpose

    public ProductList() throws Exception{
      this(
          "jdbc:mysql://localhost/pcshop?user=root&password=sesame");
    }
    
    /** Creates a new instance of ProductList */

    public ProductList(String _url) throws Exception {
        url=_url;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        productList = new ArrayList();    // a list
        try{
            
	    // get a database connection and load the JDBC-driver

            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
	    // create SQL statements to load the products into the list
	    

            stmt = conn.createStatement();
            String sql="SELECT PRODUCT_ID, BRAND , ";
            sql += "DESCRIPTION,PRICE FROM PRODUCT";
            rs= stmt.executeQuery(sql);
            
	    // analyze the result set

            while(rs.next()){
                
                Product pb = new Product();
                
                pb.setId(rs.getInt("PRODUCT_ID"));
                pb.setName(rs.getString("BRAND"));
                pb.setDescription(rs.getString("DESCRIPTION"));
                pb.setPrice(rs.getDouble("PRICE"));
                pb.populateCompIdAmountMap(url);
                pb.computeAvailable(url, pb.getCompIdAmount());
                //pb.setAvailable(pb.getAvailabe());
                productList.add(pb);
                
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
    
    // return the product list
    
    public java.util.Collection getProductList() {
        return productList;
    }
    
    
    public void setProductList(java.util.Collection _productList){
        productList = _productList;
    }
    
    // create an XML document from the complist

    public String getXml() {
        
        Product pb=null;
        Iterator iter = productList.iterator();
        StringBuffer buff = new StringBuffer();
        
        buff.append("<productlist>");
        while(iter.hasNext()){
            pb=(Product)iter.next();
            buff.append(pb.getXml());
        }
        buff.append("</productlist>");        
        
        return buff.toString();
    }
    

    // search for a component by component ID

    public Product getById(int id) {
	Product pb = null;
	Iterator iter = productList.iterator();
        
	while(iter.hasNext()){
	    pb=(Product)iter.next();
	    if(pb.getId()== id){
                return pb;
	    }
	}
	return null;
    }
    
    public String getNameById(String id) {
	Product pb = null;
	Iterator iter = productList.iterator();
        
	while(iter.hasNext()){
	    pb=(Product)iter.next();
	    if(pb.getId()== Integer.parseInt(id))
                {
                return pb.getName();
	    }
	}
	return null;
    }
    
    
    
    
    // a main used for testing, remember that a bean can be run
    // without a container

    public static void main(String[] args){
        try{
	    ProductList plb = new ProductList();
	    System.out.println(plb.getXml());
        }
        catch(Exception e){
	    System.out.println(e.getMessage());
        }
    }
}

