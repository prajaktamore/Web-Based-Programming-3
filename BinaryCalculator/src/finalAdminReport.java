import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class finalAdminReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String BASE = "o=airius.com";
	public String FILTER;
	public finalAdminReport() {
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
		
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("MM-dd-yyyy"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		
		String month = dateNow.substring(0, 2);
		String date = dateNow.substring(3, 5);
		String year = dateNow.substring(6, 10);
        try {
			Class.forName ("com.mysql.jdbc.jdbc2.optional.MysqlDataSource").newInstance();
		
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			Connection con = DriverManager.getConnection("jdbc:mysql://bigyellowcat.cs.binghamton.edu/more", "more", "more7755");
			
			
        // Create statement
        Statement stmt = con.createStatement();
        
        String btnVal = request.getParameter("sub");
			if ("Delete".equals(btnVal)) {
				String sender = request.getParameter("r1");
				String receiver = request.getParameter("r2");
				String awardChange = request.getParameter("r3");
				String query = "DELETE FROM Award where suid='"+sender+"' AND ruid='"+receiver+"' AND award='"+awardChange+"'";
				// Execute the query
		        int rs = stmt.executeUpdate(query);
		        		        
		        if(rs ==1){
		        	out.print("Deleted");
		        	RequestDispatcher rd=request.getRequestDispatcher("./AdminAwardChange.jsp");  
				    rd.include(request,response);
		        }else{
		        	out.print("Failure");
		        	RequestDispatcher rd=request.getRequestDispatcher("./finalAdminReport.jsp");  
				    rd.include(request,response);
		        }
		        
			} else if ("update".equals(btnVal)) {
				String sender = request.getParameter("r1");
				String receiver = request.getParameter("r2");
				String awardOld = request.getParameter("r3");
				String awardChange = request.getParameter("r4");
				String query = "UPDATE Award SET award='"+awardChange+"', enterdate='"+ year+"-"+month+"-"+date+"' where suid='"+sender+"' AND ruid='"+receiver+"' AND award='"+awardOld+"'";
				// Execute the query
		        int rs = stmt.executeUpdate(query);
		        		        
		        if(rs ==1){
		        	out.print("Updated");
		        	RequestDispatcher rd=request.getRequestDispatcher("./AdminAwardChange.jsp");  
				    rd.include(request,response);
		        }else{
		        	out.print("Failure");
		        	RequestDispatcher rd=request.getRequestDispatcher("./finalAdminReport.jsp");  
				    rd.include(request,response);
		        }
			}
        
        } catch (InstantiationException e) {
        	RequestDispatcher rd=request.getRequestDispatcher("./finalAdminReport.jsp");  
		    rd.include(request,response);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			RequestDispatcher rd=request.getRequestDispatcher("./finalAdminReport.jsp");  
		    rd.include(request,response);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			RequestDispatcher rd=request.getRequestDispatcher("./finalAdminReport.jsp");  
		    rd.include(request,response);
			e.printStackTrace();
		} catch (SQLException e) {
			RequestDispatcher rd=request.getRequestDispatcher("./finalAdminReport.jsp");  
		    rd.include(request,response);
			e.printStackTrace();
		}
	}
}
