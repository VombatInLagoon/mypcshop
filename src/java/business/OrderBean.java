package business;

import java.sql.*;
import java.util.*;
import java.io.*;

// save an order in the database

public class OrderBean  {

  private Connection con;
  private PreparedStatement orderPstmt;
  private PreparedStatement orderItemPstmt;
  private PreparedStatement tmpCompPstmt;
  private PreparedStatement tmpProdPstmt;
  private PreparedStatement updateCompPstmt;
  private PreparedStatement updateProductAmount;
  
  private PreparedStatement stmt = null;
  private ResultSet rs=null;

  private String url;
  private ShoppingBean sb;
  private String buyerName;
  private String shippingAddress;
  private String shippingZipcode;
  private String shippingCity;

    private static String orderSQL;
    private static String orderItemSQL;
    private static String tmpCompSQL;
    private static String tmpProdSQL;
    private static String updateCompSQL;
    private static String productAmountUpdateSQL;
    
  public OrderBean(String _url, ShoppingBean _sb, String _buyerName, 
		   String _shippingAddress, String _shippingZipcode, 
		   String _shippingCity){
    url = _url;
    sb = _sb;
    buyerName=_buyerName;
    shippingAddress=_shippingAddress;
    shippingZipcode=_shippingZipcode;
    shippingCity=_shippingCity;
  }

  /**
   * Saves an order in the database
   */
  public void saveOrder() throws Exception{
    orderSQL="INSERT INTO ORDERS(BUYER_NAME,";
    orderSQL += " SHIPPING_ADRESS, SHIPPING_ZIPCODE, SHIPPING_CITY)";
    orderSQL += " VALUES(?,?,?,?)";
    
    
    
    
    try{

	// load the driver and get a connection

	Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection(url);

	// turn off autocommit to handle transactions yourself

	con.setAutoCommit(false);
	orderPstmt = con.prepareStatement(orderSQL);
	orderPstmt.setString(1, buyerName);
	orderPstmt.setString(2, shippingAddress);
	orderPstmt.setString(3, shippingZipcode);
	orderPstmt.setString(4, shippingCity);
	orderPstmt.execute();

	// now hanlde all items in the cart

	saveOrderItems();
	sb.clear();
        
	con.commit(); // end the transaction
        
        
        
        
    }
    catch(Exception e){
	try{
	    con.rollback();    // failed, rollback the database
	}
	catch(Exception ee){}
	throw new Exception("Error saving Order", e);
    }
    finally{
	try {
	    rs.close();
	}
	catch(Exception e) {}       
	try {
	    stmt.close();
	}
	catch(Exception e) {}
	try{
	    orderPstmt.close();
	}
	catch(Exception e){}
	try{
	    orderItemPstmt.close();
	}
	catch(Exception e){}
	try{
	    con.close();
	}
	catch(Exception e){}
    }
  }

/**
 * Saves the different items in the order
 */
  private void saveOrderItems() throws Exception{

      // get the value of the last stored AUTO_INCREMENT variable
      // i. e. ORDER_ID

      
      
      orderItemSQL ="INSERT INTO ORDER_ITEMS(ORDER_ID, ";
      orderItemSQL += "PRODUCT_ID, QUANTITY) VALUES (?,?,?)";
      
      tmpCompSQL = "SELECT COMPONENT.COMPONENT_ID,COMPONENT.STOCK_NUM,COMPONENT.NAME "
              + "FROM COMPONENT INNER JOIN COMP_PROD WHERE COMP_PROD.PRODUCT_ID= ? "
              + "AND COMPONENT.COMPONENT_ID=COMP_PROD.COMPONENT_ID";
      
      tmpProdSQL = "SELECT MB,CPU,VGA,RAM,HDD,MONITOR,OPTIC FROM PRODUCT WHERE PRODUCT_ID = ?";
      updateCompSQL = "UPDATE COMPONENT SET STOCK_NUM = (STOCK_NUM - ?) WHERE COMPONENT_ID=?";
      
     
      productAmountUpdateSQL = "UPDATE PRODUCT SET AMOUNT = AMOUNT-(?) WHERE PRODUCT_ID=?";
      
      
      stmt = con.prepareStatement("SELECT LAST_INSERT_ID()");
      rs = stmt.executeQuery();
      rs.next();
      int orderId=rs.getInt(1);

      Iterator iter = ((Collection)sb.getCart()).iterator();
      ProductBean pb = null;
      Object tmpArr[];

      //Loop over the entire collection, i.e the entire shopping cart
 
      orderItemPstmt = con.prepareStatement(orderItemSQL);
      tmpCompPstmt = con.prepareStatement(tmpCompSQL);
      tmpProdPstmt = con.prepareStatement(tmpProdSQL);
      updateCompPstmt = con.prepareStatement(updateCompSQL);
      updateProductAmount = con.prepareStatement(productAmountUpdateSQL);
      
      while(iter.hasNext()){
          
	  tmpArr = (Object[])iter.next();
	  pb = (ProductBean)tmpArr[0];
          orderItemPstmt.setInt(1,orderId);
          orderItemPstmt.setInt(2,pb.getId());
          orderItemPstmt.setInt(3,((Integer)tmpArr[1]).intValue());  
          orderItemPstmt.execute();
          
          updateProductAmount.setInt(1,((Integer)tmpArr[1]).intValue() );
          updateProductAmount.setInt(2,pb.getId());
          updateProductAmount.execute();
          
          tmpCompPstmt.setInt(1,pb.getId() );
          ResultSet rstComp = tmpCompPstmt.executeQuery();
          
          tmpProdPstmt.setInt(1, pb.getId());
          ResultSet rstProd = tmpProdPstmt.executeQuery();
          rstProd.next();
          while(rstComp.next()){
              int tmpCompID = rstComp.getInt("COMPONENT_ID");
              String tmpCompName = rstComp.getString("NAME");
              
              
              
              updateCompPstmt.setInt(1, rstProd.getInt(tmpCompName)
                      *((Integer)tmpArr[1]).intValue());
              updateCompPstmt.setInt(2, tmpCompID);
              updateCompPstmt.execute();
              
              
              
          }
          
      }
      
  }
}



