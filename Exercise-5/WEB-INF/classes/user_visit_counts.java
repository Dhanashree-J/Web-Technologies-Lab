import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class user_visit_counts extends HttpServlet {
    private int count;
    public void init() {
        count = 0;
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        count++;
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>User Visit Count</title>");
        out.println("<style>");
        out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #e3f2fd; color: #1c1c1c; text-align: center; padding: 50px; margin: 0; }");
        out.println("h4 { font-size: 1.6em; color: #0277bd; margin-bottom: 20px; }");
        out.println("ul { list-style-type: none; padding: 0; text-align: center; font-size: 1.1em; margin-bottom: 40px; }");
        out.println("li { margin: 10px 0; text-align: center; color: #444; }");
        out.println(".counter { margin-top: 30px; padding: 30px; background: #fff3e0; border-radius: 12px; display: inline-block; border: 3px solid #ffb74d; box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1); transition: transform 0.3s ease-in-out; }");
        out.println(".counter:hover { transform: scale(1.05); }");
        out.println("h3 { font-size: 2.2em; color: #ff5722; margin: 0; font-weight: bold; }");
        out.println("footer { font-size: 0.9em; color: #888; margin-top: 40px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h4>Sikkim - The State Where Nature Smiles!</h4>");
        out.println("<ul>");
        out.println("<li>Sikkim is known as the land of monasteries. Home to Rumtek Monastery, the largest in Sikkim.</li>");
        out.println("<li>Sikkim is India's second smallest state, bordered by Nepal, Bhutan, Tibet, and West Bengal.</li>");
        out.println("<li>Sikkim is home to the world's third-highest peak, Kanchenjunga. Kanchenjunga National Park is a UNESCO World Heritage site.</li>");
        out.println("</ul>");
        out.println("<div class='counter'>");
        out.println("<h3><b>Page Visits: " + count + "</b></h3>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
    public void destroy() {
        // Do nothing
    }
}
