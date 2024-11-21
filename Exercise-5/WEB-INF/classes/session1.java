import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class session1 extends HttpServlet 
{
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String Spots = request.getParameter("selectedSpots");
        String[] selectedSpots=Spots.split(",");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String feedback = request.getParameter("feedback");
        PrintWriter out=response.getWriter();
        out.println("<html><head><style>li{list-style-type:none;}body{background-image:url('https://png.pngtree.com/background/20211215/original/pngtree-cute-two-color-spring-gradient-color-simple-background-picture-image_1450404.jpg');background-size:cover;}</style></head><body><center>");
        out.println("<h2>Hi " + name + ", here are your selections!</h2>");
        out.println("<h3>Your Favorite Spots:</h3>");
        if (selectedSpots != null && selectedSpots.length > 0) {
            out.println("<ul>");
            for (String spot : selectedSpots) {
                out.println("<li>" + spot + "</li>");
            }
            out.println("</ul>");
        } 
        else {
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