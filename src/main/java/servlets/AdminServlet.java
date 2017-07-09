package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.*;
import domain.Product;
import javax.servlet.*;
import java.sql.*;
import java.util.Collection;
import java.util.HashMap;

/**
 * This servlet is used to control the administrative task done
 * By the admin user in the administrator area of the application.
 * @author amin & soode
 */
public class AdminServlet extends HttpServlet {

    private static String wareHousePage = null;
    private static String productAdminPage = null;
    private static String compPage = null ;
    private static String jdbcURL = null;
    private static List<Component> compFullList = null;
    private static Integer tmp = null;
    private static ProductList productList = null;
    private static List<Component> compList = null;
    private static String addProductPage = null;
    private static String thankPage = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        wareHousePage = config.getInitParameter("ADMIN_WARE_HOUSE_PAGE");
        productAdminPage = config.getInitParameter("ADMIN_PRODUCT_PAGE");
        compPage = config.getInitParameter("ADMIN_PRODUCT_DETAIL_PAGE");
        addProductPage = config.getInitParameter("ADD_PRODUCT_PAGE");
        jdbcURL = config.getInitParameter("JDBC_URL");
        thankPage = config.getInitParameter("THANK_YOU_PAGE");

        try {
            compFullList = new ComponentList().componentFullList(jdbcURL);
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            productList = new ProductList(jdbcURL);
        }
        catch(Exception e){
            throw new ServletException(e);
        }

        try{
            compList = new ComponentList().componentList(jdbcURL);
        }
        catch(ClassNotFoundException e){
            throw new ServletException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ServletContext sc = getServletContext();
        sc.setAttribute("compFullList", compFullList);
        sc.setAttribute("productList", productList);
        sc.setAttribute("compList", compList);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher requestDispatcher;

        if (request.getParameter("action").equals("adminPageComponent")) {

            requestDispatcher = request.getRequestDispatcher(wareHousePage);
            requestDispatcher.forward(request, response);

        }
        else if(request.getParameter("action").equals("adminPageProduct")){

            requestDispatcher = request.getRequestDispatcher(productAdminPage);
            requestDispatcher.forward(request, response);

        }

        else if(request.getParameter("action").equals("addProduct")){

            request.setAttribute("complistcpu", new ComponentList().componentListSameCategory(jdbcURL, "CPU"));
            request.setAttribute("complisthdd", new ComponentList().componentListSameCategory(jdbcURL, "HDD"));
            request.setAttribute("complistmb", new ComponentList().componentListSameCategory(jdbcURL, "MB"));
            request.setAttribute("complistmonitor", new ComponentList().componentListSameCategory(jdbcURL, "MONITOR"));
            request.setAttribute("complistoptic", new ComponentList().componentListSameCategory(jdbcURL, "OPTIC"));
            request.setAttribute("complistram", new ComponentList().componentListSameCategory(jdbcURL, "RAM"));
            request.setAttribute("complistvga", new ComponentList().componentListSameCategory(jdbcURL, "VGA"));

            requestDispatcher = request.getRequestDispatcher(addProductPage);
            requestDispatcher.forward(request,response);

        }

        else if(request.getParameter("action").equals("addNewProduct")){

            PrintWriter out = response.getWriter();


            // This hash map is defined here to keep the list of
            // components of the new product
            HashMap compId = new HashMap();

            Product pb = new Product();

            pb.setName(request.getParameter("brand").trim());
            pb.setDescription(request.getParameter("name").trim());
            pb.setCpu(Integer.parseInt(request.getParameter("cpuAmount")));
            pb.setHdd(Integer.parseInt(request.getParameter("hddAmount")));
            pb.setMonitor(Integer.parseInt(request.getParameter("monitorAmount")));
            pb.setOptical(Integer.parseInt(request.getParameter("opticAmount")));
            pb.setRam(Integer.parseInt(request.getParameter("ramAmount")));
            pb.setVga(Integer.parseInt(request.getParameter("vgaAmount")));

            compId.put(Integer.parseInt(request.getParameter("Mb")),1);
            compId.put(Integer.parseInt(request.getParameter("Cpu"))
                    ,Integer.parseInt(request.getParameter("cpuAmount")));
            compId.put(Integer.parseInt(request.getParameter("Ram"))
                    ,Integer.parseInt(request.getParameter("ramAmount")));
            compId.put(Integer.parseInt(request.getParameter("Vga"))
                    ,Integer.parseInt(request.getParameter("vgaAmount")));
            compId.put(Integer.parseInt(request.getParameter("Hdd"))
                    ,Integer.parseInt(request.getParameter("hddAmount")));
            compId.put(Integer.parseInt(request.getParameter("Monitor"))
                    ,Integer.parseInt(request.getParameter("monitorAmount")));
            compId.put(Integer.parseInt(request.getParameter("Optic"))
                    ,Integer.parseInt(request.getParameter("opticAmount")));


            // First we must calculate the price of new product
            // And then we will add it to the DataBase

            pb.computePrice(jdbcURL,compId);
            //out.println("The action has been reached succesfully!");
            pb.addProduct(jdbcURL,compId);
            //out.println("The action has been reached succesfully!");

            try{
                productList = new ProductList(jdbcURL);
            }
            catch(Exception e){
                throw new ServletException(e);
            }

            ServletContext sc = getServletContext();
            sc.setAttribute("productList", productList);
            requestDispatcher = request.getRequestDispatcher(productAdminPage);
            requestDispatcher.forward(request,response);

        }

        else if (request.getParameter("action").equals("detail")){
            if(request.getParameter("productid")!= null){
                Product pb = productList.getById(
                        Integer.parseInt(request.getParameter("productid")));
                request.setAttribute("productid", pb);
            }
            else{
                throw new ServletException("No productid when viewing detail");
            }

            /**
             * We defined this to reload the bean which is used to show the
             * list of components of the product
             */
            try {
                compList = new ComponentList().componentList(jdbcURL);
            } catch (Exception e) {
                throw new ServletException(e);
            }
            ServletContext sc = getServletContext();
            sc.setAttribute("compList",compList);


            requestDispatcher = request.getRequestDispatcher(compPage);
            requestDispatcher.forward(request,response);
        }

        else if (request.getParameter("action").equals("addStock")) {
            if (request.getParameter("componentid") != null
                    && request.getParameter("quantity") != null) {


                response.setContentType("text/plain");
                PrintWriter out = response.getWriter();

                Connection con = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(jdbcURL);

                    // Turn on transactions
                    con.setAutoCommit(false);
                    int tmp = Integer.parseInt(request.getParameter("componentid"));
                    int count = Integer.parseInt(request.getParameter("quantity"));
                    Statement stmt = con.createStatement();
                    String sql = "UPDATE COMPONENT SET STOCK_NUM = (STOCK_NUM+"+count+
                            ") WHERE COMPONENT.COMPONENT_ID = '"+tmp+"'";

                    stmt.executeUpdate(sql);


                    con.commit();
                    out.println("Order successful!Thanks for your domain!");

                    requestDispatcher = request.getRequestDispatcher(wareHousePage);
                    requestDispatcher.forward(request, response);

                } catch (Exception e) {
                    // Any error is grounds for rollback
                    try {
                        con.rollback();
                    } catch (SQLException ignored) {
                    }
                    out.println("Order failed. Please contact technical support.");
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
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet
    (HttpServletRequest request, HttpServletResponse response
    )
            throws ServletException
            , IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost
    (HttpServletRequest request, HttpServletResponse response
    )
            throws ServletException
            , IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo

    () {
        return "Short description";
    }// </editor-fold>
}

