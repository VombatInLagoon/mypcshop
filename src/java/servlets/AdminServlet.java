/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.*;
import javax.servlet.*;
import java.sql.*;

/**
 *
 * @author amin
 */
public class AdminServlet extends HttpServlet {

    private static String wareHousePage = null;
    private static String jdbcURL = null;
    private static CompListBean compFullList = null;
    private static Integer tmp = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);



        wareHousePage = config.getInitParameter("ADMIN_WARE_HOUSE_PAGE");
        jdbcURL = config.getInitParameter("JDBC_URL");



        try {
            compFullList = new CompListBean(jdbcURL, tmp);
        } catch (Exception e) {
            throw new ServletException(e);
        }



        ServletContext sc = getServletContext();
        sc.setAttribute("compFullList", compFullList);


    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher rd = null;



        if (request.getParameter("action").equals("adminPage")) {

            rd = request.getRequestDispatcher(wareHousePage);
            rd.forward(request, response);

        } else if (request.getParameter("action").equals("addStock")) {
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
                    out.println("Order successful!  Thanks for your business!");
                    
                    rd = request.getRequestDispatcher(wareHousePage);
                    rd.forward(request, response);
                    
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
