/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.*;
import javax.servlet.*;


/**
 *
 * @author amin
 */
public class AdminServlet extends HttpServlet {

    private static String wareHousePage = null;
    private static String jdbcURL = null;
    private static CompListBean compList = null;
    private static Integer tmp = null;
    
    public void init (ServletConfig config) throws ServletException {
        super.init(config);
        
        
        
        wareHousePage = config.getInitParameter("ADMIN_WARE_HOUSE_PAGE");
        jdbcURL = config.getInitParameter("JDBC_URL");
        
        
        
        try{
                compList = new CompListBean(jdbcURL,tmp);
             }
            catch(Exception e){
            throw new ServletException(e);
        }
        
        
        
         ServletContext sc = getServletContext();
         sc.setAttribute("compList",compList);
 
             
    }
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        RequestDispatcher rd = null;
        
       
        
        
        if(request.getParameter("action").equals("adminPage")) {
           
            rd = request.getRequestDispatcher(wareHousePage); 
            rd.forward(request,response);
            
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
