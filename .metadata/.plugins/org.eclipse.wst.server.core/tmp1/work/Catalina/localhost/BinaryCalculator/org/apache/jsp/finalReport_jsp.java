/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.27
 * Generated at: 2014-05-04 17:46:56 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Enumeration;

public final class finalReport_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fcore_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fcore_005fout_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fcore_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fcore_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fcore_005fforEach_0026_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fcore_005fout_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"ShowError.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<link href=\"more.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<title>Report</title>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\tfunction printData(){\r\n");
      out.write("\t\t\talert(\"You Report is Printed!!\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction changePercentile(){\r\n");
      out.write("\t");

		try {
				Class.forName ("com.mysql.jdbc.jdbc2.optional.MysqlDataSource").newInstance();
			
				Class.forName("com.mysql.jdbc.Driver").newInstance(); 
				Connection con = DriverManager.getConnection("jdbc:mysql://bigyellowcat.cs.binghamton.edu/more", "more", "more7755");
				
	       
				 // Create statement
		    Statement stmt = con.createStatement();
		
	        ResultSet rs1 = stmt.executeQuery("SELECT * FROM Award");
			
	        int rowCount = 0;  
	    	while ( rs1.next() )  
	    	{  
	    	    // Process the row.  
	    	    rowCount++;  
	    	}
	    	
			ResultSet rs2 = stmt.executeQuery("SELECT maxcount FROM MaxSubmission LIMIT 1");
			  
	        ArrayList<Integer>  maxCount =  new ArrayList<Integer>();
	    	while(rs2.next())
		      {
		          int strprodvalue = Integer.parseInt(rs2.getString("maxcount"));
		          maxCount.add(strprodvalue);   
		      }
	    	session.setAttribute("totSub", rowCount);
	    	session.setAttribute("totAllowSub", maxCount.get(0));
	    	double percentile = (((double)rowCount)/maxCount.get(0)) * 100;
			session.setAttribute("percentile", percentile);
		    
	        } catch (InstantiationException e) {
	        	RequestDispatcher rd=request.getRequestDispatcher("./finalReport.jsp");  
			    rd.include(request,response);
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				RequestDispatcher rd=request.getRequestDispatcher("./finalReport.jsp");  
			    rd.include(request,response);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				RequestDispatcher rd=request.getRequestDispatcher("./finalReport.jsp");  
			    rd.include(request,response);
				e.printStackTrace();
			} catch (SQLException e) {
				RequestDispatcher rd=request.getRequestDispatcher("./finalReport.jsp");  
			    rd.include(request,response);
				e.printStackTrace();
			}
			
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body onload=\"changePercentile()\">\r\n");
      out.write("<form method=\"POST\">\r\n");
      out.write("<br><input type=\"text\" readonly=\"readonly\" name=\"username\" id=\"username\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.username}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" style =\"background: #33CC99; height:55px; width: 600px;text-align:right;font-size:35px; border-radius: 2em; border:6px solid #339999;border-radius:29px; color: #fff; padding-left: 1.5em; outline: none; box-shadow: 0 4px 6px -5px hsl(0, 0%, 40%), inset 0px 4px 6px -5px hsl(0, 0%, 2%)\" >\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <h1>Report Results</h1>\r\n");
      out.write("        <table width=\"page\" align=\"center\" >\r\n");
      out.write("            <!-- column headers -->\r\n");
      out.write("            <tr>\r\n");
      out.write("                ");
      if (_jspx_meth_core_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </tr>\r\n");
      out.write("            <!-- column data -->\r\n");
      out.write("            ");
      if (_jspx_meth_core_005fforEach_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            <tr>\r\n");
      out.write("\t\t\t\t\t <td height=\"40\" align=\"center\"> <label id=\"lr1\" style=\"color:#ffffff;font-size:24px;\" >Total Submissions</label> </td>\r\n");
      out.write("             \t\t <td align=\"center\"> <input id=\"r1\" name=\"r1\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.totSub}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" type=\"text\" style =\"background: #33CC99; height:55px; width: 600px;text-align:right;font-size:35px; border-radius: 2em; border:6px solid #339999;border-radius:29px; color: #fff; padding-left: 1.5em; outline: none; box-shadow: 0 4px 6px -5px hsl(0, 0%, 40%), inset 0px 4px 6px -5px hsl(0, 0%, 2%)\"/></td>\r\n");
      out.write(" \t\t\t</tr>\r\n");
      out.write(" \t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t <td height=\"40\" align=\"center\"> <label id=\"lr1\" style=\"color:#ffffff;font-size:24px;\" >Total Allowed Submissions</label> </td>\r\n");
      out.write("             \t\t <td align=\"center\"> <input id=\"r1\" name=\"r1\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.totAllowSub}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" type=\"text\" style =\"background: #33CC99; height:55px; width: 600px;text-align:right;font-size:35px; border-radius: 2em; border:6px solid #339999;border-radius:29px; color: #fff; padding-left: 1.5em; outline: none; box-shadow: 0 4px 6px -5px hsl(0, 0%, 40%), inset 0px 4px 6px -5px hsl(0, 0%, 2%)\"/></td>\r\n");
      out.write(" \t\t\t</tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("\t\t\t\t\t <td height=\"40\" align=\"center\"> <label id=\"lr1\" style=\"color:#ffffff;font-size:24px;\" >Percentage Summary</label> </td>\r\n");
      out.write("             \t\t <td align=\"center\"> <input id=\"r1\" name=\"r1\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.percentile}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" type=\"text\" style =\"background: #33CC99; height:55px; width: 600px;text-align:right;font-size:35px; border-radius: 2em; border:6px solid #339999;border-radius:29px; color: #fff; padding-left: 1.5em; outline: none; box-shadow: 0 4px 6px -5px hsl(0, 0%, 40%), inset 0px 4px 6px -5px hsl(0, 0%, 2%)\"/></td>\r\n");
      out.write(" \t\t\t</tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("            <td><input type=\"button\" name=\"Print\" value=\"Print\" onClick=\"printData()\" style=\"background-color: #2e466e; color: #ffff; border-radius: 10px;border:6px solid #1f2f47;border-radius:29px;padding:16px 57px;text-shadow:0px 1px 0px #263666;font-size:20px;\"></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("        \r\n");
      out.write("        </form>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_core_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  core:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_core_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fcore_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_core_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_core_005fforEach_005f0.setParent(null);
    // /finalReport.jsp(85,16) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_core_005fforEach_005f0.setVar("columnName");
    // /finalReport.jsp(85,16) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_core_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/finalReport.jsp(85,16) '${colNames}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${colNames}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int[] _jspx_push_body_count_core_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_core_005fforEach_005f0 = _jspx_th_core_005fforEach_005f0.doStartTag();
      if (_jspx_eval_core_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                    <th>");
          if (_jspx_meth_core_005fout_005f0(_jspx_th_core_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_core_005fforEach_005f0))
            return true;
          out.write("</th>\r\n");
          out.write("                ");
          int evalDoAfterBody = _jspx_th_core_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_core_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_core_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_core_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_core_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fcore_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_core_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_core_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_core_005fforEach_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_core_005fforEach_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  core:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_core_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fcore_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_core_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_core_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_core_005fforEach_005f0);
    // /finalReport.jsp(86,24) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_core_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${columnName}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    int _jspx_eval_core_005fout_005f0 = _jspx_th_core_005fout_005f0.doStartTag();
    if (_jspx_th_core_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fcore_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_core_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fcore_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_core_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_core_005fforEach_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  core:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_core_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fcore_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_core_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_core_005fforEach_005f1.setParent(null);
    // /finalReport.jsp(90,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_core_005fforEach_005f1.setVar("row");
    // /finalReport.jsp(90,12) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_core_005fforEach_005f1.setItems(new org.apache.jasper.el.JspValueExpression("/finalReport.jsp(90,12) '${resultReport}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${resultReport}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int[] _jspx_push_body_count_core_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_core_005fforEach_005f1 = _jspx_th_core_005fforEach_005f1.doStartTag();
      if (_jspx_eval_core_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                <tr>\r\n");
          out.write("                    ");
          if (_jspx_meth_core_005fforEach_005f2(_jspx_th_core_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_core_005fforEach_005f1))
            return true;
          out.write("\r\n");
          out.write("                </tr>\r\n");
          out.write("            ");
          int evalDoAfterBody = _jspx_th_core_005fforEach_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_core_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_core_005fforEach_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_core_005fforEach_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_core_005fforEach_005f1.doFinally();
      _005fjspx_005ftagPool_005fcore_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_core_005fforEach_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_core_005fforEach_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_core_005fforEach_005f1, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_core_005fforEach_005f1)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  core:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_core_005fforEach_005f2 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fcore_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_core_005fforEach_005f2.setPageContext(_jspx_page_context);
    _jspx_th_core_005fforEach_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_core_005fforEach_005f1);
    // /finalReport.jsp(92,20) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_core_005fforEach_005f2.setVar("column");
    // /finalReport.jsp(92,20) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_core_005fforEach_005f2.setItems(new org.apache.jasper.el.JspValueExpression("/finalReport.jsp(92,20) '${row}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${row}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int[] _jspx_push_body_count_core_005fforEach_005f2 = new int[] { 0 };
    try {
      int _jspx_eval_core_005fforEach_005f2 = _jspx_th_core_005fforEach_005f2.doStartTag();
      if (_jspx_eval_core_005fforEach_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                         <td>");
          if (_jspx_meth_core_005fout_005f1(_jspx_th_core_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_core_005fforEach_005f2))
            return true;
          out.write("</td>\r\n");
          out.write("                    ");
          int evalDoAfterBody = _jspx_th_core_005fforEach_005f2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_core_005fforEach_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_core_005fforEach_005f2[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_core_005fforEach_005f2.doCatch(_jspx_exception);
    } finally {
      _jspx_th_core_005fforEach_005f2.doFinally();
      _005fjspx_005ftagPool_005fcore_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_core_005fforEach_005f2);
    }
    return false;
  }

  private boolean _jspx_meth_core_005fout_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_core_005fforEach_005f2, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_core_005fforEach_005f2)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  core:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_core_005fout_005f1 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fcore_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_core_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_core_005fout_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_core_005fforEach_005f2);
    // /finalReport.jsp(93,29) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_core_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${column}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    int _jspx_eval_core_005fout_005f1 = _jspx_th_core_005fout_005f1.doStartTag();
    if (_jspx_th_core_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fcore_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_core_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fcore_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_core_005fout_005f1);
    return false;
  }
}