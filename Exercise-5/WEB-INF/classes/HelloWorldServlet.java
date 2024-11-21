import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
public class HelloWorldServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
         response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("Hello Woorld!");
        out.close();
    }
}