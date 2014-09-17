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

public class EntranceAdminFunction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String BASE = "o=airius.com";
	public String FILTER;

	public EntranceAdminFunction() {
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

		String btnVal = request.getParameter("rdown");
		try {
			Class.forName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource")
					.newInstance();

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://bigyellowcat.cs.binghamton.edu/more", "more",
					"more7755");

			// Create statement
			Statement stmt = con.createStatement();
			if ("AccessControl".equals(btnVal)) {
				String r1 = request.getParameter("r1");

				if ("Add".equals(r1)) {

					String query1 = "INSERT INTO matable (uid,access) VALUES('"
							+ request.getParameter("r11")
							+ "','"
							+ request.getParameter("r12") + "')";
					// Execute the query
					int rs1 = stmt.executeUpdate(query1);
					if (rs1 == 1) {
						out.print("Added User");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					} else {
						out.print("Cannot add User");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					}
				} else if ("Delete".equals(r1)) {
					String query1 = "DELETE FROM matable WHERE uid='"
							+ request.getParameter("r11") + "' AND access='"
							+ request.getParameter("r12") + "'";
					// Execute the query
					int rs1 = stmt.executeUpdate(query1);
					if (rs1 == 1) {
						out.print("Deleted User");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					} else {
						out.print("Cannot Delete User");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					}
				}
			} else if ("AwardData".equals(btnVal)) {
				String r1 = request.getParameter("r2");

				if ("Add".equals(r1)) {

					String query1 = "INSERT INTO AwardList (AwardName,AwardCount,status) VALUES('"
							+ request.getParameter("r31")
							+ "','"
							+ request.getParameter("r32")
							+ "','"
							+ request.getParameter("r33") + "')";
					// Execute the query
					int rs1 = stmt.executeUpdate(query1);
					if (rs1 == 1) {
						out.print("Updated Award");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					} else {
						out.print("Cannot Update Award");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					}
				} else if ("Delete".equals(r1)) {
					String query1 = "DELETE FROM AwardList WHERE AwardName='"
							+ request.getParameter("r3") + "'";
					// Execute the query
					int rs1 = stmt.executeUpdate(query1);
					if (rs1 == 1) {
						out.print("Updated Award");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					} else {
						out.print("Cannot Update Award");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					}
				} else if ("ChangeS".equals(r1)) {
					String query1 = "UPDATE AwardList SET status='"
							+ request.getParameter("r33") + "' WHERE AwardName='"
							+ request.getParameter("r3") + "'";
					// Execute the query
					int rs1 = stmt.executeUpdate(query1);
					if (rs1 == 1) {
						out.print("Updated Award");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					} else {
						out.print("Cannot Update Award");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					}
				}else if ("ChangeC".equals(r1)) {
					String query1 = "UPDATE AwardList SET AwardCount='"
							+ request.getParameter("r32") + "' WHERE AwardName='"
							+ request.getParameter("r3") + "'";
					// Execute the query
					int rs1 = stmt.executeUpdate(query1);
					if (rs1 == 1) {
						out.print("Updated Award");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					} else {
						out.print("Cannot Update Award");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					}
				}
			} else if ("TQMCertificate".equals(btnVal)) {

			} else if ("News".equals(btnVal)) {
				String dateread = request.getParameter("r5");

				String month = dateread.substring(0, 2);
				String date = dateread.substring(3, 5);
				String year = dateread.substring(6, 10);

				String dateread1 = request.getParameter("r6");

				String month1 = dateread1.substring(0, 2);
				String date1 = dateread1.substring(3, 5);
				String year1 = dateread1.substring(6, 10);

				String query1 = "INSERT INTO news (news,enterdate,enddate) VALUES('"
						+ request.getParameter("r4")
						+ "','"
						+ year
						+ "-"
						+ month
						+ "-"
						+ date
						+ "','"
						+ year1
						+ "-"
						+ month1
						+ "-" + date1 + "')";
				// Execute the query
				int rs1 = stmt.executeUpdate(query1);
				if (rs1 == 1) {
					out.print("Added News");
					RequestDispatcher rd = request
							.getRequestDispatcher("./entranceAdminFunction.jsp");
					rd.include(request, response);
				} else {
					out.print("Added News");
					RequestDispatcher rd = request
							.getRequestDispatcher("./entranceAdminFunction.jsp");
					rd.include(request, response);
				}
			} else if ("ChangeMax".equals(btnVal)) {
				String query1 = "DELETE FROM MaxSubmission";
				int rs2 = stmt.executeUpdate(query1);
			
				String query2 = "INSERT INTO MaxSubmission(maxcount) VALUES('"
							+ request.getParameter("r7") + "')";
					// Execute the query
					int rs1 = stmt.executeUpdate(query2);
					if (rs1 == 1) {
						out.print("Changed Max Count");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					} else {
						out.print("Cannot Update Max Count");
						RequestDispatcher rd = request
								.getRequestDispatcher("./entranceAdminFunction.jsp");
						rd.include(request, response);
					}
				
			} else if ("Searchdrop".equals(btnVal)) {
				RequestDispatcher rd = request
						.getRequestDispatcher("./Reports.jsp");
				rd.include(request, response);
			} else if ("DeleteOld".equals(btnVal)) {
				RequestDispatcher rd = request
						.getRequestDispatcher("./Retention.jsp");
				rd.include(request, response);
			}else if ("Waiting".equals(btnVal)) {
			
	        String query = "SELECT * FROM Award WHERE Approval='WAITING'";
	        
	    	ArrayList<String>  suidApprove =  new ArrayList<String>();
        	ArrayList<String>  ruidApprove=  new ArrayList<String>();
        	ArrayList<String>  sdeptApprove=  new ArrayList<String>();
        	ArrayList<String>  rdeptApprove=  new ArrayList<String>();
        	ArrayList<String>  awardApprove=  new ArrayList<String>();
        	ArrayList<String>  rmailApprove=  new ArrayList<String>();
        	ArrayList<String>  smailApprove=  new ArrayList<String>();
        	 // Execute the query
	        ResultSet rs = stmt.executeQuery(query);
			
	          while(rs.next())
	          {
		          String strprodvalue = rs.getString("suid");
		          suidApprove.add(strprodvalue);
		          String strdet = rs.getString("ruid");
		          ruidApprove.add(strdet);
		          String strprodvalue1 = rs.getString("sdept");
		          sdeptApprove.add(strprodvalue1);
		          String strdet2 = rs.getString("rdept");
		          rdeptApprove.add(strdet2);
		          String strdet3 = rs.getString("award");
		          awardApprove.add(strdet3);
		          String strdet4 = rs.getString("smail");
		          smailApprove.add(strdet4);
		          String strdet5 = rs.getString("rmail");
		          rmailApprove.add(strdet5);
		          
		          
		      }
          
	          session.setAttribute("suidApprove", suidApprove);
	          session.setAttribute("ruidApprove", ruidApprove);
	          session.setAttribute("sdeptApprove", sdeptApprove);
	          session.setAttribute("rdeptApprove", rdeptApprove);
	          session.setAttribute("awardApprove", awardApprove);
	          session.setAttribute("smailApprove", smailApprove);
	          session.setAttribute("rmailApprove", rmailApprove);
	       		RequestDispatcher rd = request
						.getRequestDispatcher("./Waiting.jsp");
				rd.include(request, response);
			}

		} catch (SQLException e) {
			out.print("Error on authentication: " + e.toString());
			RequestDispatcher rd = request
					.getRequestDispatcher("./entranceAdminFunction.jsp");
			rd.include(request, response);
		} catch (InstantiationException e) {
			out.print("Error on authentication: " + e.toString());
			RequestDispatcher rd = request
					.getRequestDispatcher("./entranceAdminFunction.jsp");
			rd.include(request, response);
		} catch (IllegalAccessException e) {
			out.print("Error on authentication: " + e.toString());
			RequestDispatcher rd = request
					.getRequestDispatcher("./entranceAdminFunction.jsp");
			rd.include(request, response);
		} catch (ClassNotFoundException e) {
			out.print("Error on authentication: " + e.toString());
			RequestDispatcher rd = request
					.getRequestDispatcher("./entranceAdminFunction.jsp");
			rd.include(request, response);
		}
		out.close();
	}
}
