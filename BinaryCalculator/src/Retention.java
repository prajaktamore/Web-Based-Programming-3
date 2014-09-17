import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Retention extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String BASE = "o=airius.com";
	public String FILTER;

	public Retention() {
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

		String dateread = request.getParameter("r1");

		String month = dateread.substring(0, 2);
		String date = dateread.substring(3, 5);
		String year = dateread.substring(6, 10);

		String query = "DELETE FROM Award WHERE enterdate <'" + year + "-" + month + "-" + date + "'";

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

			if (rs == 1) {
				out.print("DELETED");
				RequestDispatcher rd = request
						.getRequestDispatcher("./entranceAdmin.jsp");
				rd.include(request, response);
			} else {
				out.print("Failure");
				RequestDispatcher rd = request
						.getRequestDispatcher("./Retention.jsp");
				rd.include(request, response);
			}
		} catch (InstantiationException e) {
			RequestDispatcher rd = request
					.getRequestDispatcher("./Retention.jsp");
			rd.include(request, response);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			RequestDispatcher rd = request
					.getRequestDispatcher("./Retention.jsp");
			rd.include(request, response);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			RequestDispatcher rd = request
					.getRequestDispatcher("./Retention.jsp");
			rd.include(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			RequestDispatcher rd = request
					.getRequestDispatcher("./Retention.jsp");
			rd.include(request, response);
			e.printStackTrace();
		}
	}
}
