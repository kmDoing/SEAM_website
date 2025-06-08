package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginSWE
 */
@WebServlet("/LoginSWE")
public class LoginSEAM extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginSEAM() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Turn off caching
		response.setHeader("Cache-Control", "no-cache");
 
		// Get session and set logged in attribute.
		HttpSession sess = request.getSession();
		sess.setAttribute("loggedIn", "false");

		String userName = request.getParameter("userName");
   		    	
    	// If they forgot password, send it to them.
    	String forgotPswd = request.getParameter("forgotPswd");
    	if (forgotPswd != null && !forgotPswd.equals("")) {
    		String[] pswdInfo = {"guest","guest"};

			request.setAttribute("message", "username = " + pswdInfo[0] + ", password = " + pswdInfo[1]);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;	    			
    	}
    	
    	// Check user credentials.
		String userPswd = request.getParameter("userPswd");

		boolean pass = userName.equals("guest") && userPswd.equals("guest");
		if (pass) {
			// Successful log in and pass on.
			sess.setAttribute("loggedIn", userName);
			request.setAttribute("user", userName);
			request.getRequestDispatcher("/time_vulture.png").forward(request, response);
			return;
		}
		
    	// Return and display problem.
		request.setAttribute("errorMessage", "incorrect username or password");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Turn off caching
		response.setHeader("Cache-Control", "no-cache");
 
		// Get session and set logged in attribute.
		HttpSession sess = request.getSession();
		sess.setAttribute("loggedIn", "false");
    		    	
		// Check user credentials.
		String userName = request.getParameter("userName");
		String userPswd = request.getParameter("userPswd");
		String realName = request.getParameter("realName");
		String realEmail = request.getParameter("realEmail");
		String realTitle = request.getParameter("realTitle");
		String institution = request.getParameter("institution");
		
		boolean taken = userName.equals("guest");

		String error = null;
		if (taken) {
			error = "User Name is already in taken, please choose another.";
			request.setAttribute("errorMessage", error);
			request.getRequestDispatcher("/createAccount.jsp").forward(request, response);
			return;
		}
		
		//error = sendEmail("kristina.doing-harris@utah.edu", message);
		
		ArrayList<String> creds = new ArrayList<String>(6);
		creds.add(userName);
		creds.add(userPswd);
		creds.add(realName);
		creds.add(realEmail);
		creds.add(realTitle);
		creds.add(institution);
		
		request.setAttribute("creds", creds);

		// Return and display problem.
		request.getRequestDispatcher("/showCreds.jsp").forward(request, response);
	}

}
