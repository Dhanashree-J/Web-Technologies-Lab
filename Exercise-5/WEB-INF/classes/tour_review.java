import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class tour_review extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String aadhar = request.getParameter("aadhar");
        String tourType = request.getParameter("tour_type");
        String favSpot = request.getParameter("fav_spot");
        int rating = Integer.parseInt(request.getParameter("rating"));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourdb","root", "");
            String insertQuery = "INSERT INTO tour_reviews (aadhar, name, tour_type, fav_spot, rating) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = con.prepareStatement(insertQuery);
            insertStmt.setString(1, aadhar);
            insertStmt.setString(2, name);
            insertStmt.setString(3, tourType);
            insertStmt.setString(4, favSpot);
            insertStmt.setInt(5, rating);
            int insertCount = insertStmt.executeUpdate();
            if (insertCount > 0) {
                out.println("<h2>Review submitted successfully!</h2>");
            } else {
                out.println("<h2>Error submitting the review.</h2>");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } finally {
            out.close();
        }
    }
}
