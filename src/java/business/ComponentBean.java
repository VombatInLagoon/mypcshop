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
    private String supplierName;
    private String description;
    
    
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

    public String getSupplierName() {
        return supplierName;
    }
    
    public void setSupplierName(String _supplierName) {
    	supplierName=_supplierName;
    }

    
    public int getId() {
        return id;
    }
    
    public void setId( int _id) {
        id= _id;
        
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
      xmlOut.append("<supplierName><![CDATA[");
      xmlOut.append(supplierName);
      xmlOut.append("]]></supplierName>");
      xmlOut.append("<price>");
      xmlOut.append(price);      
      xmlOut.append("</price>");
      xmlOut.append("<description><![CDATA[");
      xmlOut.append(description);      
      xmlOut.append("]]></description>");   
      xmlOut.append("</component>");
      
      return xmlOut.toString();
    
        
    }   
}
