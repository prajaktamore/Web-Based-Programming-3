/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.27
 * Generated at: 2014-05-04 17:52:50 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Reports_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
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
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>Submit Award</title>\r\n");
      out.write("<link href=\"more.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function checkDate(varBirthDate) {\r\n");
      out.write("\r\n");
      out.write("            var month = varBirthDate.substring(0, 2);\r\n");
      out.write("            var date = varBirthDate.substring(3, 5);\r\n");
      out.write("            var year = varBirthDate.substring(6, 10);\r\n");
      out.write("\r\n");
      out.write("\t\t\tif(parseInt(month) < 1 || parseInt(month) > 12){\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t\t}else if(parseInt(date) < 1 || parseInt(date) > 31){\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t\t}else if(parseInt(year) < 1900 || parseInt(year) > 2014){\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("            var myDate = new Date(year, month - 1, date);\r\n");
      out.write("\r\n");
      out.write("            var today = new Date();\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("            if (myDate > today) {\r\n");
      out.write("                return false;\r\n");
      out.write("            }\r\n");
      out.write("            else {\r\n");
      out.write("                return true;\r\n");
      out.write("            }\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function checktwoDate(vartoolcountDate,vartoolcountDate1) {\r\n");
      out.write("            var month = vartoolcountDate.substring(0, 2);\r\n");
      out.write("            var date = vartoolcountDate.substring(3, 5);\r\n");
      out.write("            var year = vartoolcountDate.substring(6, 10);\r\n");
      out.write("            var myDate = new Date(year, month - 1, date);\r\n");
      out.write("            \r\n");
      out.write("            var month1 = vartoolcountDate1.substring(0, 2);\r\n");
      out.write("            var date1 = vartoolcountDate1.substring(3, 5);\r\n");
      out.write("            var year1 = vartoolcountDate1.substring(6, 10);\r\n");
      out.write("            var myDate1 = new Date(year1, month1 - 1, date1);\r\n");
      out.write("            \r\n");
      out.write("            if (myDate > myDate1) {\r\n");
      out.write("                return false;\r\n");
      out.write("            }\r\n");
      out.write("            else {\r\n");
      out.write("                return true;\r\n");
      out.write("            }\r\n");
      out.write("}\r\n");
      out.write("function validateform() {\r\n");
      out.write("\t\tvar varBirthDate = /^(\\d{2,2})(\\/)(\\d{2,2})\\2(\\d{4}|\\d{4})$/;\r\n");
      out.write("\t\tif(!(varBirthDate.test(document.getElementById(\"r1\").value) && checkDate(document.getElementById(\"r1\").value)))\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\talert(\"Please enter a valid birth date MM/DD/YYYY and Date Not Greater Than Today.\");\r\n");
      out.write("\t\t\tdocument.getElementById(\"r1\").select();\r\n");
      out.write("\t\t\tdocument.getElementById(\"r1\").focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tdocument.getElementById('lr2').style.visibility='visible';\r\n");
      out.write("   \t\tdocument.getElementById('r2').style.visibility='visible';\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function validateform1() {\r\n");
      out.write("\t\tvar varBirthDate = /^(\\d{2,2})(\\/)(\\d{2,2})\\2(\\d{4}|\\d{4})$/;\r\n");
      out.write("\t\tif(!(varBirthDate.test(document.getElementById(\"r2\").value) && checkDate(document.getElementById(\"r2\").value)))\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\talert(\"Please enter a valid birth date MM/DD/YYYY and Date Not Greater Than Today.\");\r\n");
      out.write("\t\t\tdocument.getElementById(\"r2\").select();\r\n");
      out.write("\t\t\tdocument.getElementById(\"r2\").focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(!checktwoDate(document.getElementById(\"r1\").value,document.getElementById(\"r2\").value)){\r\n");
      out.write("\t\t\talert(\"Start Date Should Be less than End Date\");\r\n");
      out.write("\t\t\tdocument.getElementById(\"r1\").select();\r\n");
      out.write("\t\t\tdocument.getElementById(\"r1\").focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tdocument.getElementById('Search').style.visibility='visible';\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function validateform2() {\r\n");
      out.write("\t\t\t\tre = /^\\d+$/; \r\n");
      out.write("\t\t\t\tif(!re.test(document.getElementById(\"r3\").value))\r\n");
      out.write("\t\t\t\t{ \r\n");
      out.write("\t\t\t\t\talert(\"Error: password must contain at least one number (0-9)!\"); \r\n");
      out.write("\t\t\t\t\tdocument.getElementById(\"r3\").select();\r\n");
      out.write("\t\t\t\t\tdocument.getElementById(\"r3\").focus();\r\n");
      out.write("\t\t\t\t\treturn false; \r\n");
      out.write("\t\t\t\t} \r\n");
      out.write("\t\t\t\tdocument.getElementById('Search').style.visibility='visible';\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function validateform3() {\r\n");
      out.write("\t\t\tvar varCity = /^[a-zA-Z]*$/;\r\n");
      out.write("\t\t\tif(!(varCity.test(document.getElementById(\"r4\").value)))\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(\"Please enter a valid Serial ID, Serial ID can only contain alphabets and Integer\");\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"r4\").select();\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"r4\").focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tdocument.getElementById('Search').style.visibility='visible';\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function validateform4() {\r\n");
      out.write("\t\t\tvar varCity = /^[a-zA-Z]*$/;\r\n");
      out.write("\t\t\tif(!(varCity.test(document.getElementById(\"r5\").value)))\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(\"Please enter a valid Name, Name can only contain alphabets\");\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"r5\").select();\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"r5\").focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tdocument.getElementById('Search').style.visibility='visible';\r\n");
      out.write("}\r\n");
      out.write(" function changeTest1() {\r\n");
      out.write("   \t    \r\n");
      out.write("\t\tvar index = document.getElementById(\"rdownrot\").selectedIndex;\r\n");
      out.write("\t\tif(parseInt(index) == 1){\r\n");
      out.write("\t\t\tdocument.getElementById('lr3').style.visibility='visible';\r\n");
      out.write("    \t\tdocument.getElementById('r3').style.visibility='visible';\r\n");
      out.write("    \t\tdocument.getElementById('lr4').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r4').style.visibility='hidden';\r\n");
      out.write("    \t\t\r\n");
      out.write("    \t\tdocument.getElementById('Search').style.visibility='hidden';\r\n");
      out.write("\t\t}else if(parseInt(index) == 2){\r\n");
      out.write("\t\t\tdocument.getElementById('lr4').style.visibility='visible';\r\n");
      out.write("    \t\tdocument.getElementById('r4').style.visibility='visible';\r\n");
      out.write("    \t\tdocument.getElementById('lr3').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r3').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('Search').style.visibility='hidden';\r\n");
      out.write("\t\t}\r\n");
      out.write("}\r\n");
      out.write(" function changeTest() {\r\n");
      out.write("   \t    \r\n");
      out.write("\t\tvar index = document.getElementById(\"rdown\").selectedIndex;\r\n");
      out.write("\t\r\n");
      out.write("\t\t if(parseInt(index) == 1){\r\n");
      out.write("       \t\tdocument.getElementById('lr1').style.visibility='visible';\r\n");
      out.write("    \t\tdocument.getElementById('r1').style.visibility='visible';\r\n");
      out.write("    \t\t\r\n");
      out.write("    \t\tdocument.getElementById('lr2').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r2').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('lr3').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r3').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('lr4').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r4').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('lr5').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r5').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('rdownrot').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('lrdownrot').style.visibility='hidden';\r\n");
      out.write("    \t\t\r\n");
      out.write("    \t\tdocument.getElementById('Search').style.visibility='hidden';\r\n");
      out.write("    \t\t\r\n");
      out.write("       \t}else  if(parseInt(index) == 2){\r\n");
      out.write("       \t\tdocument.getElementById('lr1').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r1').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('lr2').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r2').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('lr5').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r5').style.visibility='hidden';\r\n");
      out.write("    \t\t\r\n");
      out.write("    \t\tdocument.getElementById('lr4').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r4').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('lr3').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r3').style.visibility='hidden';\r\n");
      out.write("    \t\t\r\n");
      out.write("    \t\t\r\n");
      out.write("    \t\tdocument.getElementById('rdownrot').style.visibility='visible';\r\n");
      out.write("    \t\tdocument.getElementById('lrdownrot').style.visibility='visible';\r\n");
      out.write("    \t\t\r\n");
      out.write("    \t\tdocument.getElementById('Search').style.visibility='hidden';\r\n");
      out.write("       \t}else  if(parseInt(index) == 3){\r\n");
      out.write("       \t\tdocument.getElementById('lr1').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r1').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('lr2').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r2').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('lr3').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r3').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('lr4').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('r4').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('rdownrot').style.visibility='hidden';\r\n");
      out.write("    \t\tdocument.getElementById('lrdownrot').style.visibility='hidden';\r\n");
      out.write("       \t\tdocument.getElementById('lr5').style.visibility='visible';\r\n");
      out.write("    \t\tdocument.getElementById('r5').style.visibility='visible';\r\n");
      out.write("    \t\t\r\n");
      out.write("    \t\tdocument.getElementById('Search').style.visibility='hidden';\r\n");
      out.write("       \t}\r\n");
      out.write(" }\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\t\r\n");
      out.write("<form method=\"POST\" action=\"Report\">\r\n");
      out.write("<br><input type=\"text\" readonly=\"readonly\" name=\"username\" id=\"username\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.username}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" style =\"background: #33CC99; height:55px; width: 600px;text-align:right;font-size:35px; border-radius: 2em; border:6px solid #339999;border-radius:29px; color: #fff; padding-left: 1.5em; outline: none; box-shadow: 0 4px 6px -5px hsl(0, 0%, 40%), inset 0px 4px 6px -5px hsl(0, 0%, 2%)\" >\r\n");
      out.write("\r\n");
      out.write("  <table width=\"page\" align=\"center\" >\r\n");
      out.write("  \t\t\t<tr>\r\n");
      out.write(" \t\t\t\t\t\t\r\n");
      out.write(" \t\t\t\t\t\t <td height=\"40\" align=\"center\"> <label style=\"color:#ffffff;font-size:24px\" >Select Report Search</label> </td>\r\n");
      out.write(" \t\t\t\t\t    <td>\r\n");
      out.write(" \t\t\t\t\t    <select name=\"rdown\" id=\"rdown\" onchange=\"changeTest()\">  \r\n");
      out.write("\t\t\t\t\t        <OPTION  value=\"-1\" label=\"--Select--\" selected=\"selected\">  \r\n");
      out.write("\t\t\t\t\t        <option value=\"Dates\">Dates</option>\r\n");
      out.write("\t\t\t\t\t        <option value=\"Rotary\">Rotary</option>\r\n");
      out.write("\t\t\t\t\t        <option value=\"Person\">Person</option>   \r\n");
      out.write("\t\t\t\t\t    </select> \r\n");
      out.write("\t\t\t\t\t    </td>  \r\n");
      out.write("    \t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t <td height=\"40\" align=\"center\"> <label id=\"lr1\" style=\"color:#ffffff;font-size:24px;visibility:hidden;\" >Please Enter Start Date</label> </td>\r\n");
      out.write("             \t\t <td align=\"center\"> <input id=\"r1\" name=\"r1\" onchange=\"validateform()\" type=\"text\" style =\"visibility:hidden;background: #33CC99; height:55px; width: 600px;text-align:right;font-size:35px; border-radius: 2em; border:6px solid #339999;border-radius:29px; color: #fff; padding-left: 1.5em; outline: none; box-shadow: 0 4px 6px -5px hsl(0, 0%, 40%), inset 0px 4px 6px -5px hsl(0, 0%, 2%)\"/></td>\r\n");
      out.write(" \t\t\t</tr>\r\n");
      out.write(" \t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t <td height=\"40\" align=\"center\"> <label id=\"lr2\" style=\"color:#ffffff;font-size:24px;visibility:hidden;\" >Please Enter End Date</label> </td>\r\n");
      out.write("             \t\t <td align=\"center\"> <input id=\"r2\" name=\"r2\" onchange=\"validateform1()\" type=\"text\" style =\"visibility:hidden;background: #33CC99; height:55px; width: 600px;text-align:right;font-size:35px; border-radius: 2em; border:6px solid #339999;border-radius:29px; color: #fff; padding-left: 1.5em; outline: none; box-shadow: 0 4px 6px -5px hsl(0, 0%, 40%), inset 0px 4px 6px -5px hsl(0, 0%, 2%)\"/></td>\r\n");
      out.write(" \t\t\t</tr>\r\n");
      out.write(" \t\t\t<tr>\r\n");
      out.write(" \t\t\t\t\t\t\r\n");
      out.write(" \t\t\t\t\t<td height=\"40\" align=\"center\"> <label id=\"lrdownrot\" style=\"color:#ffffff;font-size:24px;visibility:hidden;\" >Select Rotary Type</label> </td>\r\n");
      out.write(" \t\t\t\t\t    <td>\r\n");
      out.write(" \t\t\t\t\t    <select name=\"rdownrot\" id=\"rdownrot\" onchange=\"changeTest1()\" style=\"visibility:hidden\">  \r\n");
      out.write("\t\t\t\t\t        <OPTION  value=\"-1\" label=\"--Select--\" selected=\"selected\">  \r\n");
      out.write("\t\t\t\t\t        <option value=\"Dates\">Department</option>\r\n");
      out.write("\t\t\t\t\t        <option value=\"Rotary\">Serial Number</option>\r\n");
      out.write("\t\t\t\t\t    </select> \r\n");
      out.write("\t\t\t\t\t    </td>  \r\n");
      out.write("    \t\t</tr>\r\n");
      out.write(" \t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t <td height=\"40\" align=\"center\"> <label id=\"lr3\" style=\"color:#ffffff;font-size:24px;visibility:hidden;\" >Department Number</label> </td>\r\n");
      out.write("             \t\t <td align=\"center\"> <input id=\"r3\" name=\"r3\" onchange=\"validateform2()\" type=\"text\" style =\"visibility:hidden;background: #33CC99; height:55px; width: 600px;text-align:right;font-size:35px; border-radius: 2em; border:6px solid #339999;border-radius:29px; color: #fff; padding-left: 1.5em; outline: none; box-shadow: 0 4px 6px -5px hsl(0, 0%, 40%), inset 0px 4px 6px -5px hsl(0, 0%, 2%)\"/></td>\r\n");
      out.write(" \t\t\t</tr>\r\n");
      out.write(" \t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t <td height=\"40\" align=\"center\"> <label id=\"lr4\" style=\"color:#ffffff;font-size:24px;visibility:hidden;\" >Employee Serial</label> </td>\r\n");
      out.write("             \t\t <td align=\"center\"> <input id=\"r4\" name=\"r4\" onchange=\"validateform3()\" type=\"text\" style =\"visibility:hidden;background: #33CC99; height:55px; width: 600px;text-align:right;font-size:35px; border-radius: 2em; border:6px solid #339999;border-radius:29px; color: #fff; padding-left: 1.5em; outline: none; box-shadow: 0 4px 6px -5px hsl(0, 0%, 40%), inset 0px 4px 6px -5px hsl(0, 0%, 2%)\"/></td>\r\n");
      out.write(" \t\t\t</tr>\r\n");
      out.write(" \t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t <td height=\"40\" align=\"center\"> <label id=\"lr5\" style=\"color:#ffffff;font-size:24px;visibility:hidden;\" >Submitter or Recipient Name</label> </td>\r\n");
      out.write("             \t\t <td align=\"center\"> <input id=\"r5\" name=\"r5\" onchange=\"validateform4()\" type=\"text\" style =\"visibility:hidden;background: #33CC99; height:55px; width: 600px;text-align:right;font-size:35px; border-radius: 2em; border:6px solid #339999;border-radius:29px; color: #fff; padding-left: 1.5em; outline: none; box-shadow: 0 4px 6px -5px hsl(0, 0%, 40%), inset 0px 4px 6px -5px hsl(0, 0%, 2%)\"/></td>\r\n");
      out.write(" \t\t\t</tr>\r\n");
      out.write("  \t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td align=\"center\"><input type=\"submit\" name=\"Search\" id=\"Search\" value=\"Search\" style=\"visibility:hidden;background-color: #2e466e; color: #ffff; border-radius: 10px;border:6px solid #1f2f47;border-radius:29px;padding:16px 57px;text-shadow:0px 1px 0px #263666;font-size:20px;\">\r\n");
      out.write("\t\t\t</tr>\t  \r\n");
      out.write(" </table>\r\n");
      out.write(" </form>\r\n");
      out.write("</body>\r\n");
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
}