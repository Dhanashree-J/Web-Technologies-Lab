import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class view_reviews extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourdb","root","");
            String query = "SELECT aadhar, name, tour_type, fav_spot, rating FROM tour_reviews";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            out.println("<html><head><title>Reviews Summary</title></head><body><center>");
            out.println("<h1>Reviews Summary</h1>");
            out.println("<table border='1' cellpadding='10' cellspacing='0'>");
            out.println("<tr><th>Aadhar</th><th>Name</th><th>Tour Type</th><th>Favourite Spot</th><th>Rating</th></tr>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("aadhar") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("tour_type") + "</td>");
                out.println("<td>" + rs.getString("fav_spot") + "</td>");
                out.println("<td>" + rs.getInt("rating") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<a href='database.html'><button style='padding:5px;margin:10px;'>Back to Dashboard</button></a>");
            out.println("</center></body></html>");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } finally {
            out.close();
        }
    }
}
