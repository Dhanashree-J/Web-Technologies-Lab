import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class session extends HttpServlet {

    // Map of spots with their respective image URLs
    private static final Map<String, String> spotImages = new HashMap<>() {{
        put("Gangtok", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/97/M.G._Marg%2C_Gangtok_01.jpg/800px-M.G._Marg%2C_Gangtok_01.jpg");
        put("Tsomgo Lake", "https://www.esikkimtourism.in/wp-content/uploads/2019/04/nathulanewoctbr.jpg");
        put("Nathula Pass", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/02/3c/94/7e/pass-of-listening-ears.jpg?w=900&h=500&s=1");
        put("Yumthang Valley", "https://www.sikkimtourismindia.com/uploads/yumthang-valley.jpg");
        put("Rumtek Monastery", "https://www.karmapa.org/wp-content/uploads/Rumtek_Monastery_-_Inside_Close_View-1400px-cropped.jpg");
        put("BabaMandir", "https://www.exoticmiles.com/gangtok/baba-mandir/");
        put("Buddha Park", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/ef/73/74/awesomeness-overloaded.jpg?w=1200&h=-1&s=1");
        put("Hanuman Tok", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/05/51/32/b1/hanuman-tok.jpg?w=1200&h=1200&s=1");
        put("Banjhakri falls", "https://www.flamingotravels.co.in/_next/image?url=https%3A%2F%2Fimgcdn.flamingotravels.co.in%2FImages%2FPlacesOfInterest%2FBan-Jhakri-Falls-Sikkim-3.jpg&w=1080&q=90");
        put("Himalayan Zoological Park", "https://www.fundayholidays.com/wp-content/uploads/2020/07/Himalayan-Zoological-Park-1.jpg");
    }};

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String name = request.getParameter("name");
        String[] selectedSpots = request.getParameterValues("tourist-places");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String sessionMethod = request.getParameter("session-type"); // Get the selected session handling method

        // Feedback based on rating
        String feedback = (rating >= 7) ? "Thank you for a great rating! We are glad you enjoyed your experience!" :
                "We are sorry you did not have a better experience. We hope your next visit will be more enjoyable.";

        // Handle session data based on selected method
        if (sessionMethod.equals("HttpSession")) 
        {
            HttpSession session = request.getSession(true); // Create a new session if not already present
            session.setAttribute("name", name);
            session.setAttribute("selectedSpots", selectedSpots);
            session.setAttribute("rating", rating);
            session.setAttribute("feedback", feedback);
        } 
        else if (sessionMethod.equals("cookies")) {
            // Store data in cookies with encoding to avoid invalid characters
            Cookie nameCookie = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
            String selectedSpotsEncoded = URLEncoder.encode(String.join(",", selectedSpots), "UTF-8");
            Cookie spotsCookie = new Cookie("selectedSpots", selectedSpotsEncoded);
            Cookie ratingCookie = new Cookie("rating", String.valueOf(rating));
            Cookie feedbackCookie = new Cookie("feedback", URLEncoder.encode(feedback, "UTF-8"));
            // Add cookies to response
            response.addCookie(nameCookie);
            response.addCookie(spotsCookie);
            response.addCookie(ratingCookie);
            response.addCookie(feedbackCookie);
        }

        else if (sessionMethod.equals("Hidden Form Fields")) {
            // Use hidden form fields to pass data
            out.println("<html><body><center>");
            out.println("<form action='session2' method='post'>");
            out.println("<input type='hidden' name='name' value='" + name + "'>");
            out.println("<input type='hidden' name='selectedSpots' value='" + String.join(",", selectedSpots) + "'>");
            out.println("<input type='hidden' name='rating' value='" + rating + "'>");
            out.println("<input type='hidden' name='feedback' value='" + feedback + "'>");
            out.println("<input type='submit' value='Go to next page'></form>");
            out.println("</center></body></html>");
            return; // Exit here to prevent further processing in this servlet
        } 
        else if (sessionMethod.equals("URL Rewriting")) {
            // Use URL Rewriting
            String url = response.encodeURL("session1?name=" + name + "&selectedSpots=" + String.join(",", selectedSpots) +
                    "&rating=" + rating + "&feedback=" + feedback);
            out.print("<html><head><style>a:hover{color:red;background-color:#D3D3D3;} a{text-decoration:none;font-size:20px;padding:7px;margin:5px;}</style></head><body><a href='"+url+"'>Visit the next page</a></body></html>");  
            return; // Exit after redirect
        }

        // Display user name, selected spots, and rating
        out.println("<html><head><style>li{list-style-type:none;}body{background-image:url('https://png.pngtree.com/background/20211215/original/pngtree-cute-two-color-spring-gradient-color-simple-background-picture-image_1450404.jpg');background-size:cover;}</style></head><body><center>");
        out.println("<h2>Hi " + name + ", here are your selections!</h2>");
        out.println("<h3>Your Favorite Spots:</h3>");

        if (selectedSpots != null && selectedSpots.length > 0) {
            out.println("<ul>");
            for (String spot : selectedSpots) {
                out.println("<li>" + spot + "</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<p>No spots selected.</p>");
        }

        out.println("<h3>Your Rating: " + rating + "</h3>");
        out.println("<p>" + feedback + "</p>");

        // Display selected images
        if (selectedSpots != null && selectedSpots.length > 0) {
            out.println("<h3>Images of Your Selected Spots:</h3>");
            for (String spot : selectedSpots) {
                String imageUrl = spotImages.get(spot);
                if (imageUrl != null) {
                    out.println("<div style='display: inline-block; text-align: center; margin: 10px;'>");
                    out.println("<img src='" + imageUrl + "' alt='" + spot + "' style='width:250px;height:200px;margin:10px;border-radius:7px;'><br>");
                    out.println("<p>" + spot + "</p>");
                    out.println("</div>");
                }
            }
        }
        out.println("<br><img style='height:300px;' src='https://i.pinimg.com/originals/94/89/32/948932dbf09e0581a16ef9e2a4df50ca.gif'></center></body></html>");
    }
}
