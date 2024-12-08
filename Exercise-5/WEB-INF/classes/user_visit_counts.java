import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class user_visit_counts extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the username and password from the request
        String username = request.getParameter("username");
        String spot = request.getParameter("spot");
        // Set the content type of the response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Simulate user authentication (you can replace this with a database check later)
        if (username != null && !username.isEmpty()) {
            // Check if a cookie exists for this user to track the count
            Cookie[] cookies = request.getCookies();
            boolean found = false;
            int loginCount = 0;
            
            // Check if cookies are present
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("loginCount_" + username)) {
                        // Cookie exists for this username, get the current count and increment it
                        loginCount = Integer.parseInt(cookie.getValue());
                        loginCount++;
                        // Update the cookie with the new count
                        cookie.setValue(String.valueOf(loginCount));
                        cookie.setMaxAge(60 * 60 * 24); // Cookie expires in 1 day
                        response.addCookie(cookie);  // Add the updated cookie to the response
                        found = true;
                        break;
                    }
                }
            }

            // If no login count cookie found for this user, create a new one
            if (!found) {
                Cookie newCookie = new Cookie("loginCount_" + username, "1");
                newCookie.setMaxAge(60 * 60 * 24); // Cookie expires in 1 day
                response.addCookie(newCookie);
                loginCount = 1;
            }
            out.println("<html><head><style>");
            out.println("body { font-family: 'Arial', sans-serif; background-color: #1c1c1c; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; background-image: url('dark-sikkim-mountains.jpg'); background-size: cover; color: #f1f1f1; }");
            out.println(".message-container { background-color: rgba(0, 0, 0, 0.7); padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5); text-align: center; width: 350px; }");
            out.println("h2 { font-size: 24px; color: #f1f1f1; margin-bottom: 20px; }");
            out.println("p { font-size: 18px; color: #ddd; margin-top: 20px; }");
            out.println(".btn { padding: 10px; background-color: #4a7c6e; color: white; border: none; border-radius: 4px; cursor: pointer; margin:20px; }");
            out.println(".btn:hover { background-color: #3f6959; }");
            out.println("ul { list-style-type: none; padding: 0; text-align: center; font-size: 1.1em; margin-bottom: 40px; }");
            out.println("li { margin: 10px 0; text-align: center; color:white; }");
            out.println("</style></head><body><center>");
            out.println("<ul>");
            out.println("<li>Sikkim is known as the land of monasteries. Home to Rumtek Monastery, the largest in Sikkim.</li>");
            out.println("<li>Sikkim is India's second smallest state, bordered by Nepal, Bhutan, Tibet, and West Bengal.</li>");
            out.println("<li>Sikkim is home to the world's third-highest peak, Kanchenjunga. Kanchenjunga National Park is a UNESCO World Heritage site.</li>");
            out.println("</ul>");
            // Display the login count and a welcome message with styling
            out.println("<div class='message-container'>");
            out.println("<h2>Welcome, " + username + "!</h2>");
            out.println("<p>Your favorite spot is: <strong>" + spot + "</strong></p>");
            out.println("<p>This is your " + loginCount + "th login.</p>");
        } else {
            // If username or password is missing, show an error message with styling
            out.println("<div class='error-container'>");
            out.println("<h2>Please enter valid details</h2>");
        }
         out.println("<a style='text-decoration:none;' href='user_visit_counts.html' class='btn'>Go Back to Login</a>");
            out.println("</div>");
        out.println("</center></body></html>");
        out.close();
    }
}
