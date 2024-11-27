import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

public class tour_delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aadhar = request.getParameter("aadhar");
        Connection connection=null;
        PreparedStatement stmt=null;
        PrintWriter out=response.getWriter();
        out.println("<html><body>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourdb","root","");
            String sql = "DELETE FROM tour_booking WHERE aadhar = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, aadhar);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                out.println("<h2>Deleted successfully.</h2>");
            } else {
                out.println("<h2>No record found.</h2>");
            }
            connection.close();
            stmt.close();
            out.println("</body></html>");
        } 
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();    
        } 
        finally {
            out.close();
        }
    }
}
