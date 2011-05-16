/*
 * ComponentBean.java
 * This JavaBean is used to implement the components of the
 * Pc Shop.
 *
 */
package business;

/**
 *
 * @author  Amin Khorsandi 
 */
public class ComponentBean {

    // describe a book
    
    private int id;
    private String name;
    private int price;
   
    private int stock_num;
    private String description;
    private int pid;
    
    
    /** Creates a new instance of ComponentBean */
    public ComponentBean() {
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int _price) {
        price = _price;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String _name) {
        name=_name;
    }

    public int getStockNum() {
        return stock_num;
    }
    
    public void setStockNum(int _stockNum) {
    	stock_num =_stockNum;
    }

    
    public int getId() {
        return id;
    }
    
    public void setId( int _id) {
        id= _id;
        
    }
    
    public int getPId() {
        return pid;
    }
    
    public void setPId( int _pid) {
        pid= _pid;
        
    }
    
   
    public void setDescription(String _description) {
        description=_description;
    }

    public String getDescription() {
        return description;
    }

    // create an XML document describing the book
    
    public String getXml() {

	// use a Stringbuffer (not String) to avoid multiple
	// object creation

     StringBuffer xmlOut = new StringBuffer();
      
      xmlOut.append("<component>");
      xmlOut.append("<id>");
      xmlOut.append(id);
      xmlOut.append("</id>");      
      xmlOut.append("<name><![CDATA[");
      xmlOut.append(name);
      xmlOut.append("]]></name>");
      //xmlOut.append("<supplierName><![CDATA[");
      //xmlOut.append(supplierName);
      //xmlOut.append("]]></supplierName>");
      xmlOut.append("<price>");
      xmlOut.append(price);      
      xmlOut.append("</price>");
      xmlOut.append("<description><![CDATA[");
      xmlOut.append(description);      
      xmlOut.append("]]></description>");
      xmlOut.append("<amount>");
      xmlOut.append(stock_num);      
      xmlOut.append("</amount>");
      xmlOut.append("</component>");
      
      return xmlOut.toString();
    
        
    }   
}
