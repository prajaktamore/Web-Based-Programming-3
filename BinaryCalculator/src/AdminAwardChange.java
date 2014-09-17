import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminAwardChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String BASE = "o=airius.com";
	public String FILTER;

	public AdminAwardChange() {
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
			String str2 = request.getParameter("dr1");
			String str3 = request.getParameter("r9");
			String str4 = request.getParameter("dr9");

			ResultSet rs = null;
			if (request.getParameter("r1") != null && request.getParameter("r1") != "" && request.getParameter("dr1") != null && request.getParameter("dr1") != "") {
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

			} else if (request.getParameter("r9") != null && request.getParameter("r9") != "" && request.getParameter("dr9") != null && request.getParameter("dr9") != "") {
				String month = str3.substring(0, 2);
				String date = str3.substring(3, 5);
				String year = str3.substring(6, 10);

				String month1 = str4.substring(0, 2);
				String date1 = str4.substring(3, 5);
				String year1 = str4.substring(6, 10);

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

			} else if (request.getParameter("r2") != null && request.getParameter("r2") != "") {

				String query = "SELECT * FROM Award WHERE suid='" + request.getParameter("r2") + "'";
				// Execute the query
				rs = stmt.executeQuery(query);

			}else if (request.getParameter("r3") != null && request.getParameter("r3") != "") {

				String query = "SELECT * FROM Award WHERE slname='" + request.getParameter("r3") + "'";
				// Execute the query
				rs = stmt.executeQuery(query);

			}else if (request.getParameter("r4") != null && request.getParameter("r4") != "") {

				String query = "SELECT * FROM Award WHERE ruid='" + request.getParameter("r4") + "'";
				// Execute the query
				rs = stmt.executeQuery(query);

			}else if (request.getParameter("r5") != null && request.getParameter("r5") != "") {

				String query = "SELECT * FROM Award WHERE rlname='" + request.getParameter("r5") + "'";
				// Execute the query
				rs = stmt.executeQuery(query);

			} else if (request.getParameter("r6") != null && request.getParameter("r6") != "") {

				String query = "SELECT * FROM Award WHERE sdept='" + request.getParameter("r6") + "'";
				// Execute the query
				rs = stmt.executeQuery(query);

			} else if (request.getParameter("r7") != null && request.getParameter("r7") != "") {

				String query = "SELECT * FROM Award WHERE rdept='" + request.getParameter("r7") + "'";
				// Execute the query
				rs = stmt.executeQuery(query);

			}else if (request.getParameter("r10") != null && request.getParameter("r10") != "") {

				String query = "SELECT * FROM Award LIMIT "+ request.getParameter("r10");
				// Execute the query
				rs = stmt.executeQuery(query);

			}else if (request.getParameter("r8") != null && request.getParameter("r8") != "") {

				String query = "SELECT * FROM Award WHERE award='" + request.getParameter("r8") + "'";
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
					.getRequestDispatcher("/finalAdminReport.jsp");
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