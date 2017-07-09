package domain;

import ca.krasnay.sqlbuilder.SelectBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.sql.*;
import java.util.stream.Collectors;

/**
 * This been is responsible to return a list of components based on some
 * criteria!
 * @author  Amin khorsandi
 */

public class ComponentList {

    public static final Logger log = LoggerFactory.getLogger(Component.class);

    private int productID = 0;


    public ComponentList() {}

    public ComponentList(String jdbcUrl) {

    }

    public List<Component> componentList (final String url) throws ClassNotFoundException, SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<Component> componentList = new ArrayList();

        try {
           
            // get a database connection and load the JDBC-driver
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url);
           
            statement = connection.createStatement();

            resultSet = statement.executeQuery(new SelectBuilder()
                    .column("PRODUCT_ID")
                    .column("COMPONENT.COMPONENT_ID")
                    .column("NAME")
                    .column("PRICE")
                    .column("DESCRIPTION")
                    .from("COMPONENT")
                    .join("COMP_PROD on COMPONENT.COMPONENT_ID = COMP_PROD.COMPONENT_ID").toString());

            while(resultSet.next()){
               
                Component component = new Component();
               
                component.setId(resultSet.getInt("COMPONENT_ID"));
                component.setPid(resultSet.getInt("PRODUCT_ID"));
                component.setName(resultSet.getString("NAME"));
                component.setPrice(resultSet.getInt("PRICE"));
                component.setDescription(resultSet.getString("DESCRIPTION"));

                componentList.add(component);
            }
        }
        catch(SQLException sqle){
            log.error("SQL exception thrown!");
            sqle.printStackTrace();
        }

        finally{
              resultSet.close();
              statement.close();
              connection.close();
        }
        return componentList;
    }

    public List<Component> componentFullList(String url) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<Component> componentList = new ArrayList();

        try {
           
            // get a database connection and load the JDBC-driver
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url);
           
            statement = connection.createStatement();

            resultSet = statement.executeQuery(new SelectBuilder()
            .column("COMPONENT_ID")
            .column("NAME")
            .column("PRICE")
            .column("DESCRIPTION")
            .column("STOCK_NUM")
            .from("COMPONENT").toString());

            while(resultSet.next()){
                Component component = new Component();

                component.setId(resultSet.getInt("COMPONENT_ID"));
                component.setPid(0);
                component.setName(resultSet.getString("NAME"));
                component.setStockNum(resultSet.getInt("STOCK_NUM"));
                component.setPrice(resultSet.getInt("PRICE"));
                component.setDescription(resultSet.getString("DESCRIPTION"));
                componentList.add(component);
            }
        }
        catch(SQLException sqle){
            log.error("Sql exception!");
            sqle.printStackTrace();
        }

        finally {
            resultSet.close();
            statement.close();
            connection.close();
        }
        return componentList;
    }

    /**
     * This constructor is used to produce a list of all available component from
     * the same category, for example if comp = "MB" then all the motherboards 
     * in the components stock will be returned and so on!
     * @param url
     * @param comp
     * @throws Exception 
     */
    public List<Component> componentListSameCategory(String url, String comp) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Component> componentList = new ArrayList();
        try{
           
            // get a database connection and load the JDBC-driver

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url);
           
            resultSet = statement.executeQuery(new SelectBuilder()
                    .column("COMPONENT_ID")
                    .column("DESCRIPTION")
                    .from("COMPONENT")
                    .where("COMPONENT.NAME = 'comp'")
                    .toString());

            while(resultSet.next()){
               
                Component component = new Component();

                component.setId(resultSet.getInt("COMPONENT_ID"));
                component.setPrice(resultSet.getInt("PRICE"));
                component.setDescription(resultSet.getString("DESCRIPTION"));
                componentList.add(component);
               
            }
       
        }

        catch(SQLException sqle){
            log.error("Sql exception!");
            sqle.printStackTrace();
        }

        finally{
              resultSet.close();
              statement.close();
              connection.close();
        }
        return componentList;
    }

    public int getProductID() {
        return productID;
    }
   
    public void setProductID(int _pid) {
        productID = _pid;
    }
   
    // create an XML document from the complist
    public static String getXml(List<Component> components) {
       
        StringBuffer stringBuffer = new StringBuffer();
       
        stringBuffer.append("<complist>");
        components.stream().map(e -> stringBuffer.append(e.getXml()));
        stringBuffer.append("</complist>");
       
        return stringBuffer.toString();
    }

    // search for a component by component ID
    public Optional<Component> getById(int id, List<Component> components) {
        List<Component> componentWithId = components.stream().filter(e-> e.getId() == id).collect(Collectors.toList());
        return componentWithId.size() == 0 ? Optional.empty() : Optional.of(componentWithId.get(0));
    }
   
     public String getXMLByProductID(String pid, List<Component> components) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<complist>");

        components.stream().filter(e-> e.getPid() == Integer.parseInt(pid)).map(e -> stringBuffer.append(e.getXml()));
        stringBuffer.append("</complist>");
       
        return stringBuffer.toString();
       
    }
   
     /**
      * This method has been added to support the add new component
      * Administrative task
      * @param id
      * @param amount
      * @throws Exception
      */
     public void setCount(int id,int amount) throws Exception{
         
        Connection conn = null;
        Statement stmt = null;
       
       
       
        try{
           
            // get a database connection and load the JDBC-driver

            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/pcshop?user=root&password=sesame");
           
            // create SQL statements to load the components into the list
            // each component is a Component object

           
            // first we get the current amount of the component
            // using the following query
           
            conn.setAutoCommit(false);
           
           
            stmt = conn.createStatement();
           
            String sql="UPDATE COMPONENT SET STOCK_NUM = (STOCK_NUM+9),";
            sql += "WHERE COMPONENT_ID = 'id'";
           
 


            //String sql = "select brand from PRODUCT where PRODUCT_ID = " + sp ;
           
           
           
            stmt.executeUpdate(sql);
       
            // analyze the result set

           
          conn.commit();
      //out.println("Order successful!  Thanks for your domain!");
    }
    catch (Exception e) {
      // Any error is grounds for rollback
      try {
        conn.rollback();
      }
      catch (SQLException ignored) { }
      //out.println("Order failed. Please contact technical support.");
    }
    finally {
      // Clean up.
      try {
        if (conn != null) conn.close();
      }
      catch (SQLException ignored) { }
    }
         
     }
}

