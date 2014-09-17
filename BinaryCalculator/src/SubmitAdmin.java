import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPException;


public class SubmitAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String BASE = "o=airius.com";
	public String FILTER;

	public SubmitAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);

		String n = request.getParameter("username");
		String p = request.getParameter("password");

		if (n == null || p == null || n == "" || p == "") {
			out.print("Invalid credentials");
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.include(request, response);
		}
		LDAPConnection ld = null;
		int status = -1;
		try {
			ld = new LDAPConnection();
			/* Connect to server */
			String MY_HOST = "bigyellowcat.cs.binghamton.edu:389";
			int MY_PORT = 389;

			ld.connect(MY_HOST, MY_PORT);

			/* Authenticate to the server */
			String DN = "uid=" + n + ", ou=People, o=airius.com";
			String PW = p;
			ld.authenticate(3, DN, PW);

	  		Class.forName ("com.mysql.jdbc.jdbc2.optional.MysqlDataSource").newInstance();
			
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			Connection con = DriverManager.getConnection("jdbc:mysql://bigyellowcat.cs.binghamton.edu/more", "more", "more7755");
			
	    // Create statement
	    Statement stmt = con.createStatement();
	    
	    String query = "SELECT * FROM matable";
	    // Execute the query
	    ResultSet rs = stmt.executeQuery(query);
	  
	    	ArrayList<String>  uid1 =  new ArrayList<String>();
	    	ArrayList<String>  userpassword1 =  new ArrayList<String>();
	    	ArrayList<String>  access1 =  new ArrayList<String>();
	    	
	    	while(rs.next())
		      {
		          String str1 = rs.getString("uid");
		          uid1.add(str1);
		          
		          String str3 = rs.getString("access");
		          access1.add(str3);
		      }
	    	Boolean admin = Boolean.FALSE;
	    	Boolean adminmanager = Boolean.FALSE;
	    	
	    	for (int i = 0; i < uid1.size(); i++) {
				if(uid1.get(i).equals(n) && (access1.get(i).equalsIgnoreCase("AdministratorManager"))){
					adminmanager = Boolean.TRUE;
				}else if(uid1.get(i).equals(n) && (access1.get(i).equalsIgnoreCase("Administrator"))){
					admin = Boolean.TRUE;
				}
			}
	    	
	    	
	    	String query1 = "SELECT * FROM AwardList WHERE status='ACTIVE' AND AwardCount > 1";
		    // Execute the query
		    ResultSet rs1 = stmt.executeQuery(query1);
		  
		    	ArrayList<String>  aawardname =  new ArrayList<String>();
		    	
		    	while(rs1.next())
			      {
			          String str1 = rs1.getString("AwardName");
			          aawardname.add(str1);
			      }
		    	
		    	
	    	if(adminmanager){
				out.print("Authentication successful");
				session.setAttribute("username", n);
				session.setAttribute("aawardname", aawardname);
				response.sendRedirect("./entranceAdminFunction.jsp");
			}else if(admin){
				out.print("Authentication successful");
				session.setAttribute("username", n);
				session.setAttribute("aawardname", aawardname);
				response.sendRedirect("./entranceAdminFunction.jsp");
			}else{
				out.print("Access denied.  Contact the YEA! Administrator at x3301");
				response.sendRedirect("./AdminFunctionLogin.jsp");
			}
	    	
		} catch (LDAPException e) {
			if (e.getLDAPResultCode() == LDAPException.INVALID_CREDENTIALS) {
				out.print("Invalid credentials");
				RequestDispatcher rd = request
						.getRequestDispatcher("./AdminFunctionLogin.jsp");
				rd.include(request, response);
			} else if (e.getLDAPResultCode() == LDAPException.NO_SUCH_OBJECT) {
				out.print("No such user");
				RequestDispatcher rd = request
						.getRequestDispatcher("./AdminFunctionLogin.jsp");
				rd.include(request, response);
			} else {
				out.print("Error on authentication: " + e.toString());
				RequestDispatcher rd = request
						.getRequestDispatcher("./AdminFunctionLogin.jsp");
				rd.include(request, response);
			}
		} catch (SQLException e) {
			out.print("Error on authentication: " + e.toString());
			RequestDispatcher rd = request
					.getRequestDispatcher("./AdminFunctionLogin.jsp");
			rd.include(request, response);
		} catch (InstantiationException e) {
			out.print("Error on authentication: " + e.toString());
			RequestDispatcher rd = request
					.getRequestDispatcher("./AdminFunctionLogin.jsp");
			rd.include(request, response);
		} catch (IllegalAccessException e) {
			out.print("Error on authentication: " + e.toString());
			RequestDispatcher rd = request
					.getRequestDispatcher("./AdminFunctionLogin.jsp");
			rd.include(request, response);
		} catch (ClassNotFoundException e) {
			out.print("Error on authentication: " + e.toString());
			RequestDispatcher rd = request
					.getRequestDispatcher("./AdminFunctionLogin.jsp");
			rd.include(request, response);
		}
		out.close();
		/* Done, so disconnect */
		if ((ld != null) && ld.isConnected()) {
			try {
				ld.disconnect();
			} catch (LDAPException e) {
				System.out.println("Error: " + e.toString());
			}
		}
	}

}
