import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class tour_booking extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String aadhar = request.getParameter("aadhar");
        String email = request.getParameter("email");
        String tourType = request.getParameter("tour_type");
        String tourDate = request.getParameter("tour_date");
        int noOfPeople = Integer.parseInt(request.getParameter("no_of_people"));
        try {
            Class.forName("com.mysql.jdbc.Driver");+
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourdb", "root", "");
            String selectQuery = "SELECT * FROM tour_booking WHERE aadhar = ?";
            PreparedStatement selectStmt = con.prepareStatement(selectQuery);
            selectStmt.setString(1, aadhar);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                String updateQuery = "UPDATE tour_booking SET name = ?, email = ?, tour_type = ?, tour_date = ?, no_of_people = ? WHERE aadhar = ?";
                PreparedStatement updateStmt = con.prepareStatement(updateQuery);
                updateStmt.setString(1, name);
                updateStmt.setString(2, email);
                updateStmt.setString(3, tourType);
                updateStmt.setDate(4, Date.valueOf(tourDate));
                updateStmt.setInt(5, noOfPeople);
                updateStmt.setString(6, aadhar);
                int updateCount = updateStmt.executeUpdate();
                if (updateCount > 0) {
                    out.println("<h2>Tour booking updated successfully!</h2>");
                } else {
                    out.println("<h2>Error updating the tour booking.</h2>");
                }
            } else {
                String insertQuery = "INSERT INTO tour_booking (aadhar, name, email, tour_type, tour_date, no_of_people) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = con.prepareStatement(insertQuery);
                insertStmt.setString(1, aadhar);
                insertStmt.setString(2, name);
                insertStmt.setString(3, email);
                insertStmt.setString(4, tourType);
                insertStmt.setDate(5, Date.valueOf(tourDate));
                insertStmt.setInt(6, noOfPeople);

                int insertCount = insertStmt.executeUpdate();
                if (insertCount > 0) {
                    out.println("<h2>Tour booked successfully!</h2>");
                } else {
                    out.println("<h2>Error booking the tour.</h2>");
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } 
        finally {
            out.close();
        }
    }
}
