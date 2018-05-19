package org.amin.pcshop.domain;

import java.io.Serializable;
import java.sql.*;

/**
 * This is a been used to deal with domain operation related to the components
 * @author  Amin Khorsandi 
 */
public class Component implements Serializable{

    private int id;
    private String name;
    private int price;
    private int stockNum;
    private String description;
    private int pid;

    public Component() {

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getXml() {

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
        xmlOut.append(stockNum);
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
            //out.println("Order successful!Thanks for your domain!");

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
