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


public class AwardWinner extends HttpServlet {
		private static final long serialVersionUID = 1L;
		public String BASE = "o=airius.com";
		public String FILTER;
		public AwardWinner() {
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
			
			String n=request.getParameter("username");  
			String p=request.getParameter("password");  

			if(n == null || p == null || n =="" || p ==""){
				out.print( "Invalid credentials" );
				RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");  
			    rd.include(request,response);
			}
			LDAPConnection ld = null;
			int status = -1;
			try {
				ld = new LDAPConnection();
				/* Connect to server */
				String MY_HOST = "bigyellowcat.cs.binghamton.edu:389";
				int MY_PORT = 389;
				
				ld.connect( MY_HOST, MY_PORT );

				/* Authenticate to the server */
				String DN = "uid="+n + ", ou=People, o=airius.com";
				String PW = p;
				ld.authenticate( 3, DN, PW );
				
				Class.forName ("com.mysql.jdbc.jdbc2.optional.MysqlDataSource").newInstance();
				
				Class.forName("com.mysql.jdbc.Driver").newInstance(); 
				Connection con = DriverManager.getConnection("jdbc:mysql://bigyellowcat.cs.binghamton.edu/more", "more", "more7755");
				
	        // Create statement
	        Statement stmt = con.createStatement();
	        
	        String query = "SELECT * FROM Award WHERE ruid='"+n+"' AND Approval='APPROVED'";
	        
	     // Create statement
	        Statement stmt1 = con.createStatement();
	        
	        String query1 = "SELECT * FROM AwardList WHERE status='ACTIVE' AND AwardCount > 1";
	        
	        // Execute the query
	        ResultSet rs = stmt.executeQuery(query);
	      
	     // Execute the query
	        ResultSet rs1 = stmt1.executeQuery(query1);
	        
	        	ArrayList<String>  award =  new ArrayList<String>();
	        	ArrayList<String>  details =  new ArrayList<String>();
	          while(rs.next())
	          {
		          String strprodvalue = rs.getString("award");
		          award.add(strprodvalue);
		          String strdet = rs.getString("diff");
		          details.add(strdet);
		      }
	          
	          ArrayList<String>  awardlist1 =  new ArrayList<String>();
	          while(rs1.next())
	          {
		          String strprodvalue = rs1.getString("AwardName");
		          awardlist1.add(strprodvalue);
		      }
		        if(rs != null && rs1 != null){
			          session.setAttribute("awardlist1", awardlist1);
			          
			          session.setAttribute("ruidwinner", n);
			          session.setAttribute("award", award);
			          session.setAttribute("details", details);

		        	out.print( "Authentication successful" );
					session.setAttribute("username", n);
		    		 response.sendRedirect("./entranceAwardWinners.jsp");
		        }	
			}
			catch( LDAPException e ) {
				if ( e.getLDAPResultCode() == LDAPException.INVALID_CREDENTIALS ) {
					out.print( "Invalid credentials" );
					RequestDispatcher rd=request.getRequestDispatcher("./AwardwinnerLogin.jsp");  
				    rd.include(request,response);
				} else if ( e.getLDAPResultCode() == LDAPException.NO_SUCH_OBJECT ) {
					out.print( "No such user" );
					RequestDispatcher rd=request.getRequestDispatcher("./AwardwinnerLogin.jsp");  
				    rd.include(request,response);
				} else {
					out.print( "Error on authentication: " + e.toString() );
					RequestDispatcher rd=request.getRequestDispatcher("./AwardwinnerLogin.jsp");  
				    rd.include(request,response);
				}
			}catch (InstantiationException e) {
	        	RequestDispatcher rd=request.getRequestDispatcher("./AwardwinnerLogin.jsp");  
			    rd.include(request,response);
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				RequestDispatcher rd=request.getRequestDispatcher("./AwardwinnerLogin.jsp");  
			    rd.include(request,response);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				RequestDispatcher rd=request.getRequestDispatcher("./AwardwinnerLogin.jsp");  
			    rd.include(request,response);
				e.printStackTrace();
			} catch (SQLException e) {
				RequestDispatcher rd=request.getRequestDispatcher("./AwardwinnerLogin.jsp");  
			    rd.include(request,response);
				e.printStackTrace();
			}
			out.close();  
			/* Done, so disconnect */
			if ( (ld != null) && ld.isConnected() ) {
				try {
				    ld.disconnect();
				} catch ( LDAPException e ) {
					System.out.println( "Error: " + e.toString() );
				}
			}
			}
}
