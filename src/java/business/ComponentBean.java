/*
 * ComponentBean.java
 * This JavaBean is used to implement the components of the
 * Pc Shop.
 *
 */
package business;

import java.io.Serializable;
import java.sql.*;

/**
 * This is a been used to deal with business operation related to the components
 * @author  Amin Khorsandi 
 */
public class ComponentBean implements Serializable{

    //Describe a component
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

    public void setPrice(Integer _price) {
        price = _price;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public int getStockNum() {
        return stock_num;
    }

    public void setStockNum(Integer _stockNum) {
        stock_num = _stockNum;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer _id) {
        id = _id;

    }

    public int getPid() {
        return pid;
    }

    public void setPid(Integer _pid) {
        pid = _pid;

    }

    public void setDescription(String _description) {
        description = _description;
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

    /**
     * This method is used to update the amount of the components in the Stock 
     * When Admin user add them trough the components Administrator page!
     * @param _url
     * @param _id
     * @param _quantity 
     */
    public void updateComp(String _url, Integer _id, Integer _quantity) {


        int comId = _id;
        int quantity = _quantity;


        String url = _url;
        Connection con = null;
        Statement stmt = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url);

            // Turn off transactions Auto commit
            con.setAutoCommit(false);

            stmt = con.createStatement();
            String sql = "UPDATE COMPONENT SET STOCK_NUM = (STOCK_NUM+" + quantity
                    + ") WHERE COMPONENT.COMPONENT_ID = '" + comId + "'";

            stmt.executeUpdate(sql);


            con.commit();
            //out.println("Order successful!Thanks for your business!");



        } catch (Exception e) {
            // Any error is grounds for rollback
            try {
                con.rollback();
            } catch (SQLException ignored) {
            }
            //out.println("Order failed. Please contact technical support.");
        } finally {
            // Clean up.
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ignored) {
            }
        }
    }
}
