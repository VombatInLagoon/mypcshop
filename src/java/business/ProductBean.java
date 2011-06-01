/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author amin
 */
public class ProductBean {
    // describe a book

    private int id;
    private String brand;
    private String description;
    private int cpu;
    private int vga;
    private int ram;
    private int hdd;
    private int monitor;
    private int optical;

    /** Creates a new instance of ComponentBean */
    public ProductBean() {
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

    // create an XML document describing the book
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
        xmlOut.append("</product>");

        return xmlOut.toString();


    }

    /**
     * This method has been added to make the bean able to add 
     * The new product into the DataBase
     */
    public void addProduct(String _url,HashMap _compId) throws Exception {

        String url = _url;
        HashMap compId = _compId;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection coll = compId.values();

        
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url);

            pstmt = conn.prepareStatement("INSERT INTO PRODUCT "
                    + "(BRAND,DESCRIPTION,MB,CPU,VGA,RAM,OPTIC,HDD,MONITOR)"
                    + "VALUES(?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, brand);
            pstmt.setString(2, description);
            pstmt.setInt(3, 1);
            pstmt.setInt(4, cpu);
            pstmt.setInt(5, vga);
            pstmt.setInt(6, ram);
            pstmt.setInt(7, optical);
            pstmt.setInt(8, hdd);
            pstmt.setInt(9, monitor);


            pstmt.executeUpdate();
            
            
            
            


            



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

        updateCompProductTable(url,compId);



    }

    private void updateCompProductTable(String _url,HashMap _compId) throws SQLException, ClassNotFoundException, Exception{
        
        String url = _url;
        HashMap compId = _compId;
        Collection coll = compId.values();
        
        while(coll.iterator().hasNext()){
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
         try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url);

        
        
        String sql = "INSERT INTO COMP_PROD "+
                    "(COMPONENT_ID,PRODUCT_ID)"+
                    "VALUES(?,?)";
         
            
            
            pstmt.setInt(1, (Integer)coll.iterator().next());
            pstmt.setInt(2, this.getId());
            pstmt.executeUpdate();
            
         
         
         } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
          finally {
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

    }
    }
}
