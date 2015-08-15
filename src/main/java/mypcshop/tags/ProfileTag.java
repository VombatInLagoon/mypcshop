package tags;

import business.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;


/**
 * This tag is used for the conditions that 
 * @author amin
 */

public class ProfileTag extends TagSupport {

	private String url = null;

	public void setUrl(String _url) {
        url = _url;
      }

	public int doStartTag() throws JspException {
	    try {

		// get access to the session and to the request
 
		JspWriter out = pageContext.getOut();
		HttpSession sess = pageContext.getSession();
		HttpServletRequest request = 
		    (HttpServletRequest) pageContext.getRequest();

		// get the username and store it in the session

		String user = request.getRemoteUser();
		sess.setAttribute("currentUser",user);

		// create a profile bean and start populate it
		// store it in the request

		ProfileBean pb = new ProfileBean(url);
		pb.populate(user);
		request.setAttribute("profile", pb);
	    } catch (Exception e) {
		throw new JspException(
				       e.getMessage());
	    }
	    return SKIP_BODY;
	}
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
