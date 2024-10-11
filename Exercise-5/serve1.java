import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class serve1 extends HttpServlet
{
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.print("<html>");
        out.print("<body>");
        out.print("<h2>Your Servlet is ready!!..</h2>");
        out.print("</body>");
        out.print("</html>");
        out.close();
    }
}