/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author amin
 */
public class ProductBean {
    // describe a book

    private int id;
    private String brand;
    private String description;
    private double price;
    private int cpu;
    private int vga;
    private int ram;
    private int hdd;
    private int monitor;
    private int optical;

    /** Creates a new instance of ComponentBean */
    public ProductBean() {
    }

    
    // getter and setters
    
    public double getPrice(){
        return price;
    }
    
    public void setPrice(double _price){
        price = _price;
    }
    
    public int getCpu() {
        return cpu;
    }

    public void setCpu(int _cpu) {
        cpu = _cpu;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int _ram) {
        ram = _ram;
    }

    public int getVga() {
        return vga;
    }

    public void setVga(int _vga) {
        vga = _vga;
    }

    public int getHdd() {
        return hdd;
    }

    public void setHdd(int _hdd) {
        hdd = _hdd;
    }

    public int getMonitor() {
        return monitor;
    }

    public void setMonitor(int _monitor) {
        monitor = _monitor;
    }

    public int getOptical() {
        return optical;
    }

    public void setOptical(int _optical) {
        optical = _optical;
    }

    public String getName() {
        return brand;
    }

    public void setName(String _brand) {
        brand = _brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        id = _id;

    }

    public void setDescription(String _description) {
        description = _description;
    }

    public String getDescription() {
        return description;
    }

    // create an XML document describing the product
    public String getXml() {

        // use a Stringbuffer (not String) to avoid multiple
        // object creation

        StringBuffer xmlOut = new StringBuffer();

        xmlOut.append("<product>");
        xmlOut.append("<id>");
        xmlOut.append(id);
        xmlOut.append("</id>");
        xmlOut.append("<brand><![CDATA[");
        xmlOut.append(brand);
        xmlOut.append("]]></brand>");
        xmlOut.append("<description><![CDATA[");
        xmlOut.append(description);
        xmlOut.append("]]></description>");
        xmlOut.append("<price>");
        xmlOut.append(price);
        xmlOut.append("</price>");
        xmlOut.append("</product>");

        return xmlOut.toString();


    }

    /**
     * This method has been added to make the bean able to add 
     * The new product into the DataBase
     */
    public void addProduct(String _url, HashMap _compId) throws Exception {

        String url = _url;
        HashMap compId = _compId;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Iterator itr = compId.entrySet().iterator();




        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url);

            pstmt = conn.prepareStatement("INSERT INTO PRODUCT "
                    + "(BRAND,DESCRIPTION,PRICE,MB,CPU,VGA,RAM,OPTIC,HDD,MONITOR)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)");
            
            pstmt.setString(1, brand);
            pstmt.setString(2, description);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, 1);
            pstmt.setInt(5, cpu);
            pstmt.setInt(6, vga);
            pstmt.setInt(7, ram);
            pstmt.setInt(8, optical);
            pstmt.setInt(9, hdd);
            pstmt.setInt(10, monitor);


            pstmt.executeUpdate();
            
            
            // Determine the Product Id of the newly added product
            // We need this to update the COMP_PROD table
            

            String sqlPId = "SELECT * FROM PRODUCT WHERE "
                        + "PRODUCT.BRAND = '"+ brand +"'" ;
            
            
            Statement stmt = conn.createStatement();
            ResultSet rstmp = stmt.executeQuery(sqlPId);
            
            int pId=0;
            
            while(rstmp.next()){
                 pId = rstmp.getInt("PRODUCT_ID");
            }
            rstmp.close();
            
            String sql = "INSERT INTO COMP_PROD "
                    + "(COMPONENT_ID,PRODUCT_ID)"
                    + "VALUES(?,?)";

            //Iterate over the collection of component set of new product
            // and add them to COMP_PROD table
            while (itr.hasNext()) {


                pstmt = conn.prepareStatement(sql);



                Map.Entry pairs = (Map.Entry) itr.next();

                pstmt.setInt(1, (Integer) pairs.getKey());
                pstmt.setInt(2, pId);
                pstmt.executeUpdate();

            }

            //Finaly set the product id
            
            this.id=pId;




        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            try {
                rs.close();
                //ts.close();
            } catch (Exception e) {
            }
            try {
                pstmt.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

        //updateCompProductTable(url,compId);



    }
    
    
    public void computePrice(String _url,HashMap _copmList) throws SQLException, ClassNotFoundException, Exception{
        
        String url = _url;
        HashMap compList = _copmList; // This map will keep the amount of each component of the 
                                // current product
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        
        double totalPrice = 0;
        
        //Iterator itr = compId.entrySet().iterator();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url);
        
            stmt = conn.createStatement();
        
        
            
            String sql="SELECT COMPONENT_ID,PRICE FROM COMPONENT";
                    
            
            //This result set will keep the list of components of the product
            ResultSet rst = stmt.executeQuery(sql);
            
            
            // temporary variables to keep the amount of different component
            // in the product

            
            
            
            
            while(rst.next()){
                
                int idtmp = rst.getInt("COMPONENT_ID");
                double pricetmp = rst.getDouble("PRICE");
                
                if(compList.containsKey(idtmp)){
                    totalPrice = totalPrice + (pricetmp*(Integer)compList.get(idtmp));
                }
            }
                
            
                
                
                this.price = totalPrice + totalPrice * 0.1;
                
                //Finaly we will save the price in permanet storage
                
                
                
        
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            try {
                rs.close();
                //ts.close();
            } catch (Exception e) {
            }
            try {
                stmt.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
        
        
    }
    
}
