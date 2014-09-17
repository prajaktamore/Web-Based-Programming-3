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


public class changeAwardWinners extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String BASE = "o=airius.com";
	public String FILTER;

	public changeAwardWinners() {
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

		String name = request.getParameter("username");
		String award = request.getParameter("award");
		String change = request.getParameter("rdownlist");
		
		String query = "UPDATE Award SET award='"+change+"' WHERE ruid='"+name+"' AND award='"+award.trim()+"'";

		out.print(query);
		// default data source name
		String url = "jdbc:mysql://bigyellowcat.cs.binghamton.edu/more";

		try {
			Class.forName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource")
					.newInstance();

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://bigyellowcat.cs.binghamton.edu/more", "more",
					"more7755");

			// Create statement
			Statement stmt = con.createStatement();

			// Execute the query
			int rs = stmt.executeUpdate(query);
			
			if(rs ==1){
				String query1 = "UPDATE AwardList SET AwardCount=AwardCount-1 WHERE AwardName='"+ change +"'";
		        
		        int rs1 = stmt.executeUpdate(query1 );
		        if(rs1 == 1 )
		        {
			        String query2 = "DELETE FROM Award WHERE ruid='" + name +"' AND award='"+change+"'";
			        
			        int rs2 = stmt.executeUpdate(query2 );
			        
			        if(rs2 == 1 )
			        {
				        String query3 = "SELECT * FROM Award WHERE ruid='"
								+ session.getAttribute("username") + "' AND Approval='APPROVED'";
						// Execute the query
						ResultSet rs3 = stmt.executeQuery(query3);
			
						ArrayList<String> award1 = new ArrayList<String>();
						ArrayList<String> details = new ArrayList<String>();
						while (rs3.next()) {
							String strprodvalue = rs3.getString("award");
							award1.add(strprodvalue);
							String strdet = rs3.getString("diff");
							details.add(strdet);
						}
						
						session.setAttribute("award", award1);
						session.setAttribute("details", details);
						
						if (rs3 != null) {
							out.print("Your Award is Changed");
							out.print("Your Award="+change +" is Mailed");
							RequestDispatcher rd = request
									.getRequestDispatcher("./entranceEmployee.jsp");
							rd.include(request, response);
						} 
			        }
		        }
			}
			else {
				out.print("Failure");
			}

		} catch (InstantiationException e) {
			RequestDispatcher rd = request
					.getRequestDispatcher("./entranceAwardWinners.jsp");
			rd.include(request, response);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			RequestDispatcher rd = request
					.getRequestDispatcher("./entranceAwardWinners.jsp");
			rd.include(request, response);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			RequestDispatcher rd = request
					.getRequestDispatcher("./entranceAwardWinners.jsp");
			rd.include(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			RequestDispatcher rd = request
					.getRequestDispatcher("./entranceAwardWinners.jsp");
			rd.include(request, response);
			e.printStackTrace();
		}
	}
}