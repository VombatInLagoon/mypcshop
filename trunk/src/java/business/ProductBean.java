/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
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
    
    private int available;// Will Keep the available amount of this product

    
    
    /** Creates a new instance of ProductBean */
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

    
    public int getAvailabe() {
        return available;
    }

    public void setAvailable(int _available) {
        available = _available;

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
        xmlOut.append("<available>");
        xmlOut.append(available);
        xmlOut.append("</available>");
        
        xmlOut.append("</product>");

        return xmlOut.toString();


    }

    /**
     * This method has been added to make the bean able to add 
     * The new product into the DataBase
     * @param _url
     * @param _compId
     * @throws Exception 
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

            //Finaly set the product id and its Available Amount
            
            this.id = pId;
            computeAvailable(_url, _compId);



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
    
    
    
    /**
     * This method computes the price of each product
     * By summing up the prices of it's individual components and adding 
     * 10% to the total price
     * 
     * @param _url
     * @param _copmList  // This is the list of components of this product
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception 
     */
    public void computePrice(String _url,HashMap _copmList) throws SQLException
            , ClassNotFoundException, Exception{
        
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
    
    
    /**
     * This method will compute the number of available product of this brand
     * Based on the available amount of the components which are used in this 
     * Product.
     * @param _url
     * @param _copmList //this is the list of this product components 
     */
    private void computeAvailable(String _url,HashMap _copmList) 
            throws ClassNotFoundException, Exception{
        
        String url = _url;
        HashMap compList = _copmList; // This map will keep the amount of each component of the 
        // current product
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        ArrayList<Integer> avCompArray = new ArrayList<Integer>();
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url);
        
            stmt = conn.createStatement();
        
        
            
            String sql="SELECT * FROM COMPONENT";
            
//            String sqlp = "SELECT MB,CPU,RAM,VGA,MONITOR,HDD,OPTIC FROM PRODUCT"
//                    +"WHERE PRODUCT_ID='"+ this.id +"'";
            
            //This result set will keep the list of components of the product
            ResultSet rst = stmt.executeQuery(sql);
//            ResultSet rstp = stmt.executeQuery(sqlp);

            
            while(rst.next()){
                
                int idtmp = rst.getInt("COMPONENT_ID");
                int stocktmp = rst.getInt("STOCK_NUM");
                String name = rst.getString("NAME");
                
                if(compList.containsKey(idtmp)){
                    
                    if(name.equals("MB")){
                        int mbNum = 1;//rstp.getInt(name);
                        int avMb = stocktmp/mbNum;
                        avCompArray.add(avMb);
                        
                    }else if(name.equals("CPU")){
                        int cpuNum = this.cpu;//rstp.getInt(name);
                        int avCpu = stocktmp/cpuNum;
                        avCompArray.add(avCpu);
                        
                    }else if(name.equals("VGA")){
                        int vgaNum = this.vga;//rstp.getInt(name);
                        int avVga = stocktmp/vgaNum;
                        avCompArray.add(avVga);
                        
                    }else if(name.equals("RAM")){
                        int ramNum = this.ram;//rstp.getInt(name);
                        int avRam = stocktmp/ramNum;
                        avCompArray.add(avRam);
                        
                    }else if(name.equals("HDD")){
                        int hddNum = this.hdd;//rstp.getInt(name);
                        int avHdd = stocktmp/hddNum;
                        avCompArray.add(avHdd);
                        
                        
                    }else if(name.equals("MONITOR")){
                        int monitorNum = this.monitor;//rstp.getInt(name);
                        int avMonitor = stocktmp/monitorNum;
                        avCompArray.add(avMonitor);
                        
                    }else if(name.equals("OPTIC")){
                        int opticNum = this.optical;//rstp.getInt(name);
                        int avOptic = stocktmp/opticNum;
                        avCompArray.add(avOptic);
                        
                    }                                
                        
                }
            
                
            }
            
            
            Object avail = Collections.min(avCompArray);
            this.available = (Integer)avail;
            
            String sqlAmount="UPDATE PRODUCT SET "
                    +"AMOUNT='"+ this.available +"'"
                    +"WHERE PRODUCT_ID='"+ this.id +"'";
            
            stmt.executeUpdate(sqlAmount);
            
            
            
             
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
