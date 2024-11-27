import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class view_bookings extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourdb","root", "");
            String query = "SELECT aadhar, name, tour_type, tour_date FROM tour_booking";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            out.println("<html><head><title>Bookings Summary</title></head><body><center>");
            out.println("<h1>Bookings Summary</h1>");
            out.println("<table border='1' cellpadding='10' cellspacing='0'>");
            out.println("<tr><th>Aadhar</th><th>Name</th><th>Tour Type</th><th>Date</th></tr>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("aadhar") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("tour_type") + "</td>");
                out.println("<td>" + rs.getString("tour_date") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<a href='database.html'><button style='padding:5px;margin:7px;'>Back to Dashboard</button></a>");
            out.println("</center></body></html>");
            con.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } 
        finally {
            out.close();
        }
    }
}
