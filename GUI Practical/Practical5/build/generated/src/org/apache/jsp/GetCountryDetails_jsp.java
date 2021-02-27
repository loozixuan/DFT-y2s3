package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import domain.Country;

public final class GetCountryDetails_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Display Country Details</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("       \n");
      out.write("       \n");
      out.write("       ");
      domain.Country country = null;
      synchronized (session) {
        country = (domain.Country) _jspx_page_context.getAttribute("country", PageContext.SESSION_SCOPE);
        if (country == null){
          country = new domain.Country();
          _jspx_page_context.setAttribute("country", country, PageContext.SESSION_SCOPE);
        }
      }
      out.write("\n");
      out.write("       ");
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("country"), request);
      out.write("\n");
      out.write("       \n");
      out.write("       <h2>Add Country</h2>\n");
      out.write("       ");

           if(country.getName() == null || country.getFullName() == null || country.getCapital() == null){
               out.println("Country name, full name and capital is required");
               return;
           }
       
      out.write("\n");
      out.write("       \n");
      out.write("       <p>You entered the following data</p>\n");
      out.write("       <p>Country Name: ");
      out.print( country.getName());
      out.write("</p>\n");
      out.write("       <p>Official Name: ");
      out.print( country.getFullName());
      out.write("</p>\n");
      out.write("       <p>Capital: ");
      out.print( country.getCapital());
      out.write("</p>\n");
      out.write("       \n");
      out.write("        <!-- Set the action for processing the answers -->\n");
      out.write("        <form method=\"post\" action=\"SaveCountryDetails.jsp\">\n");
      out.write("            <input type=\"submit\" value=\"Confirm\">\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
