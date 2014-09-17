import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Enumeration;

import javax.naming.*;
import javax.naming.directory.*;

public class Submit extends HttpServlet {
	private static final long serialVersionUID= 1L;
	 public static String base;
	  public static String filter;
	  public static String scope;
	  public Submit() {
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
				   base = "o=airius.com";
				   String tempo = "uid=" + request.getParameter("username");  
			       filter = tempo;
			        
			      Hashtable environment = new Hashtable();
			       environment.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
			       environment.put(Context.PROVIDER_URL,"ldap://bigyellowcat.cs.binghamton.edu:389");
			       DirContext context = new InitialDirContext(environment);
			       
			       SearchControls scope = new SearchControls();
			       scope.setSearchScope(SearchControls.SUBTREE_SCOPE);
			       // this limits the number of entries returned
			       scope.setCountLimit(10);
			       NamingEnumeration result = context.search(base, filter, scope);
			       // 
			       SearchResult srchresults;

			 while (result.hasMore())
			   {
			       srchresults = (SearchResult) result.next();
			       String dn = srchresults.getName();
			       String temp = "dn= " + dn ;
			       Attributes attrs = srchresults.getAttributes();
			       NamingEnumeration  ne = attrs.getAll();
			       while (ne.hasMore())
			       {
			         Attribute attr = (Attribute) ne.next();
			         String attrname = attr.getID() + ": ";
			         Enumeration values = attr.getAll();
			         if(attrname.equalsIgnoreCase("givenName: ")){
			        		 while (values.hasMoreElements())
			        		 session.setAttribute("sfname", values.nextElement());
			        	 }
			         if(attrname.equalsIgnoreCase("sn: ")){
			        	 while (values.hasMoreElements())
		        		 session.setAttribute("ssname", values.nextElement());
		        	 }
			         if(attrname.equalsIgnoreCase("cn: ")){
			        	 while (values.hasMoreElements())
		        		 session.setAttribute("slname", values.nextElement());
		        	 }
			         if(attrname.equalsIgnoreCase("mail: ")){
			        	 while (values.hasMoreElements())
		        		 session.setAttribute("smail",values.nextElement());
		        	 }
			         if(attrname.equalsIgnoreCase("departmentNumber: ")){
			        	 while (values.hasMoreElements())
			        		 session.setAttribute("sdept",values.nextElement());
			    	 }
			         if(attrname.equalsIgnoreCase("uid: ")){
			        	 while (values.hasMoreElements())
			        		 session.setAttribute("suid",values.nextElement());
			    	 }
			         
			       }
			    }
			}
			 catch (Exception e)
		      {
		        System.out.println("Execption: " + e.toString());
		      }
			ArrayList<String>  rName =  new ArrayList<String>();
			  ArrayList<String>  rFname =  new ArrayList<String>();
			  ArrayList<String>  rLastname =  new ArrayList<String>();
			  ArrayList<String>  rmailid =  new ArrayList<String>();
			  ArrayList<String>  rdept =  new ArrayList<String>();
			  ArrayList<String>  ruid =  new ArrayList<String>();
				
		filter = "sn=" + request.getParameter("lastname");
		
		 try
	     {
	       Hashtable<String,String> environment = new Hashtable<String,String>();
	       environment.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
	       environment.put(Context.PROVIDER_URL,"ldap://bigyellowcat.cs.binghamton.edu:389");
	       DirContext context = new InitialDirContext(environment);
	       SearchControls scope = new SearchControls();
	       scope.setSearchScope(SearchControls.SUBTREE_SCOPE);
	       NamingEnumeration result = context.search(base, filter, scope);
	       SearchResult srchresults = (SearchResult) result.next();
	       String dn = srchresults.getName();
	       String temp = "dn= " + dn + base;
	       Attributes attrs = srchresults.getAttributes();
	       NamingEnumeration  ne = attrs.getAll();
	       if(null == ne){
	    	   out.print( "Invalid credentials" );
				RequestDispatcher rd=request.getRequestDispatcher("./submittal.jsp");  
			    rd.include(request,response);
	       }
	       while (ne.hasMoreElements())
	       {
	         Attribute attr = (Attribute) ne.next();	
	         String attrname = attr.getID() + ": ";
	         Enumeration values = attr.getAll();
	         
	         if(attrname.equalsIgnoreCase("givenName: ")){
        		 while (values.hasMoreElements())
        			 rFname.add((String) values.nextElement());
        	 }
	         if(attrname.equalsIgnoreCase("sn: ")){
	        	 while (values.hasMoreElements())
	        		 rLastname.add((String) values.nextElement());
	    	 }
	         if(attrname.equalsIgnoreCase("cn: ")){
	        	 while (values.hasMoreElements())
	        		 rName.add((String) values.nextElement());
	    	 }
	         if(attrname.equalsIgnoreCase("mail: ")){
	        	 while (values.hasMoreElements())
	        		 rmailid.add((String) values.nextElement());
	    	 }
	         if(attrname.equalsIgnoreCase("departmentNumber: ")){
	        	 while (values.hasMoreElements())
	        		 rdept.add((String) values.nextElement());
	    	 }
	         if(attrname.equalsIgnoreCase("uid: ")){
	        	 while (values.hasMoreElements())
	        		 ruid.add((String) values.nextElement());
	    	 }
	       }
	     
	       
  		 session.setAttribute("rFname", rFname);
  		 session.setAttribute("rLastname", rLastname);
  		 session.setAttribute("rName", rName);
  		 session.setAttribute("rmailid", rmailid);
  		session.setAttribute("rdept", rdept);
  		session.setAttribute("ruid", ruid);
  		 
  		Class.forName ("com.mysql.jdbc.jdbc2.optional.MysqlDataSource").newInstance();
		
		Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		Connection con = DriverManager.getConnection("jdbc:mysql://bigyellowcat.cs.binghamton.edu/more", "more", "more7755");
		
    // Create statement
    Statement stmt = con.createStatement();
    
    String query = "SELECT * FROM AwardList WHERE status='ACTIVE'  AND AwardCount > 1";
    // Execute the query
    ResultSet rs = stmt.executeQuery(query);
  
    	ArrayList<String>  awardList =  new ArrayList<String>();
    	while(rs.next())
	      {
	          String strprodvalue = rs.getString("AwardName");
	          awardList.add(strprodvalue);   
	      }
      
    	session.setAttribute("awardList", awardList);
  		
  		RequestDispatcher rd=request.getRequestDispatcher("./Awards.jsp");  
	    rd.include(request,response);
	    }
	    catch (Exception e)
	      {
	        System.out.println("Execption: " + e.toString());
	      }
	  
		}
}
