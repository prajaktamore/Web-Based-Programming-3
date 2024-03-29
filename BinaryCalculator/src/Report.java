import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String BASE = "o=airius.com";
	public String FILTER;

	public Report() {
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

		try {
			String award = null;

			Class.forName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource")
					.newInstance();

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://bigyellowcat.cs.binghamton.edu/more", "more",
					"more7755");

			// Create statement
			Statement stmt = con.createStatement();
			String str1 = request.getParameter("r1");
			String str2 = request.getParameter("r2");
			String str3 = request.getParameter("r3");
			String str4 = request.getParameter("r4");
			String str5 = request.getParameter("r5");
			ResultSet rs = null;
			if (str1 != null && str1 != "" && str2 != null && str2 != "") {
				String month = str1.substring(0, 2);
				String date = str1.substring(3, 5);
				String year = str1.substring(6, 10);

				String month1 = str2.substring(0, 2);
				String date1 = str2.substring(3, 5);
				String year1 = str2.substring(6, 10);

				String query = "Select *  from  Award where enterdate between '"
						+ year
						+ "-"
						+ month
						+ "-"
						+ date
						+ "' and '"
						+ year1
						+ "-" + month1 + "-" + date1 + "'";
				// Execute the query
				rs = stmt.executeQuery(query);

			} else if (str3 != null && str3 != "") {

				String query = "SELECT * FROM Award WHERE sdept='" + str3 + "'";
				// Execute the query
				rs = stmt.executeQuery(query);

			} else if (str4 != null && str4 != "") {

				String query = "SELECT * FROM Award WHERE suid='" + str4
						+ "' OR ruid='" + str4 + "'";
				// Execute the query
				rs = stmt.executeQuery(query);

			} else if (str5 != null && str5 != "") {
				String query = "SELECT * FROM Award WHERE sname='" + str5
						+ "' OR rname='" + str5 + "'";
				// Execute the query
				rs = stmt.executeQuery(query);

			}
			List<String> colNames = new ArrayList<String>();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();
			for (int i = 1; i <= numberOfColumns; i++) {
				colNames.add(rsMetaData.getColumnName(i));
			}

			List<ArrayList<String>> dataMap = new ArrayList<ArrayList<String>>();
			while (rs.next()) {
				ArrayList<String> map = new ArrayList<String>();
				for (int i = 1; i <= numberOfColumns; i++) {
					String colData = rs.getString(i);
					map.add(colData);
				}
				dataMap.add(map);
			}

			request.setAttribute("colNames", colNames);
			request.setAttribute("resultReport", dataMap);
			RequestDispatcher rd = request
					.getRequestDispatcher("/finalReport.jsp");
			rd.forward(request, response);

		} catch (InstantiationException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/Reports.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/Reports.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/Reports.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/Reports.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
	}

}