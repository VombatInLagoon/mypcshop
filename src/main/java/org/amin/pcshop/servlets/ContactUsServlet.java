/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import org.amin.pcshop.helper.MailUtilGmail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This servlet is used to send the user's contact messages via Gmail email 
 * facilities
 * @author amin & soode
 */
public class ContactUsServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
                // get parameters from the request
        String firstName = request.getParameter("first_name");
        //String lastName = request.getParameter("last_name");
        String emailAddress = request.getParameter("email");
        String comments = request.getParameter("comments"); 

        
        
        // send email to user
        String toUser = emailAddress;
        String fromPcShop = "email_list@murach.com";
        String subject = "Welcome to our email list";
        String body = "Dear " + firstName+ ",\n\n" +
            "Thanks for your comment. We'll make sure to send " +
            "you announcements about new products and promotions.\n" +
            "Have a great day and thanks again!\n\n" +
            "Pc Shop 2011 Team";
        boolean isBodyHTML = false;

        try
        {
            MailUtilGmail.sendMail(toUser, fromPcShop, subject, body, isBodyHTML);
        }
        catch (MessagingException e)
        {
            String errorMessage = 
                "ERROR: Unable to send email. " + 
                    "Check Tomcat logs for details.<br>" +
                "NOTE: You may need to configure your system " + 
                    "as described in chapter 15.<br>" +
                "ERROR MESSAGE: " + e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            this.log(
                "Unable to send email. \n" +
                "Here is the email you tried to send: \n" +
                "=====================================\n" +
                "TO: " + emailAddress + "\n" +
                "FROM: " + fromPcShop + "\n" +
                "SUBJECT: " + subject + "\n" +
                "\n" +
                body + "\n\n");
        }
        
        
        String toPcShop = "aminkhorsandi@gmail.com";
        String fromUser = emailAddress;
        String subjectPcShop = "Welcome to our email list";
        String bodyPcShop = comments;
        boolean isBodyHTMLPc = false;

        try
        {
            MailUtilGmail.sendMail(toPcShop, fromUser, subjectPcShop, bodyPcShop, isBodyHTMLPc);
        }
        catch (MessagingException e)
        {
            String errorMessage = 
                "ERROR: Unable to send email. " + 
                    "Check Tomcat logs for details.<br>" +
                "NOTE: You may need to configure your system " + 
                    "as described in chapter 15.<br>" +
                "ERROR MESSAGE: " + e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            this.log(
                "Unable to send email. \n" +
                "Here is the email you tried to send: \n" +
                "=====================================\n" +
                "TO: " + emailAddress + "\n" +
                "FROM: " + fromPcShop + "\n" +
                "SUBJECT: " + subject + "\n" +
                "\n" +
                body + "\n\n");
        }
        
        
        
        
        // forward request and response to JSP page
        String url = "/contactUs/display_email_entry.jsp";
        RequestDispatcher dispatcher =
             getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);        
        
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
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(ContactUsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(ContactUsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
