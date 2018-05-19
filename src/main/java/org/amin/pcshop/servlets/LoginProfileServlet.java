package servlets;


import org.amin.pcshop.domain.Profile;


import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** * Servlet implementation class LoginProfileServlet */
/**
 * This servlet is used to control the Log in process of user to the 
 * Profile area which makes them able to change their profile information
 * 
 * 
 * @author Amin & Soode
 */


public class LoginProfileServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        try {
            Profile user = new Profile();
            String userName = request.getParameter("un");
            HashMap<String, Boolean> roles = null;
            
            
            user.setUser(userName);
            user.setPassword(request.getParameter("pw"));
            
            //The login method of the Profile class is used to check the
            //User eligiblity to enter the profile change area!
            user = Profile.login(user);
            if (user.getValid()) {
                HttpSession session = request.getSession(true);
                
                user.populate(userName);
                roles = user.getRoles();
                
                session.setAttribute("roles", roles);
                session.setAttribute("currentUser", userName);
                session.setAttribute("profile", user);

                response.sendRedirect("profile.jsp");
            } else {
                response.sendRedirect("login_error_profile.jsp");
            }
//error page 
        } catch (Throwable theException) {
            System.out.println(theException);
        }
    }
}