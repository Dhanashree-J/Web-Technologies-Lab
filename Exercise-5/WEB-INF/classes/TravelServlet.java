import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class TravelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String[] interests = request.getParameterValues("interest");
        String name=request.getParameter("name");
        out.println("<html><head><style>div img{ width:300px;height:250px;margin:10px;border-radius:20px;}div p{ text-align:centre;} a{text-decoration:none;color:black;} a:hover{text-decoration:underline;color:blue;} img:hover{transform:scale(1.2);}</style></head><body><center>");
        out.println("<h2>Your Sikkim Travel Recommendations</h2>");
        out.println("<p>Welcome <b>"+name+ "</b>");
        out.print("!!</p>");
        if (interests != null) {
            for (String interest : interests) {
                switch (interest) {
                    case "Nature":
                        out.println("<h3>Nature & Scenery</h3>");
                        out.println("<p>Explore the lush valleys and mountain views at places like</p>");
                        out.print("<div style='display:flex;justify-content:center;'>");
                        out.print("<div><a href='https://en.wikipedia.org/wiki/Lake_Tsomgo'><img src='https://www.travelogyindia.com/storage/app/itinerary/359/changu-lake-sikkim.jpg'></a>");
                        out.print("<p>Tsomgo Lake</p></div>");
                        out.print("<div><a href='https://en.wikipedia.org/wiki/Yumthang_Valley_of_Flowers'><img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQO5T0k4_6sPeBhi_hV-P6XE89q5n1LP9YPKw&s'></a><p>Yumthang Valley</p></div>");
                        out.print("<div><a href='https://en.wikipedia.org/wiki/Nathu_La'><img src='https://melangeoftales.com/wp-content/uploads/2021/01/me-at-nathula-scaled.jpeg'></a><p>Nathula pass</p></div>");
                        out.print("</div>");
                        break;
                    case "Culture":
                        out.println("<h3>Culture & Heritage</h3>");
                        out.println("<p>Visit the below places and immerse yourself to the heritage of Sikkim.</p>");
                        out.print("<div style='display:flex;justify-content:center;'>");
                        out.print("<div><a href='https://en.wikipedia.org/wiki/Rumtek_Monastery'><img src='https://www.elginhotels.com/wp-content/uploads/2020/03/rumtek-monastery-01.jpg.webp'></a>");
                        out.print("<p>Rumtek Monastery</p></div>");
                        out.print("<div><a href='https://en.wikipedia.org/wiki/Enchey_Monastery'><img src='https://www.esikkimtourism.in/wp-content/uploads/2019/03/enchy-monasteryyy-bnnnr.jpg'></a><p>Enchey Monastery</p></div>");
                        out.print("<div><a href='https://en.wikipedia.org/wiki/Buddha_Park_of_Ravangla'><img src='https://sikkimtourism.gov.in/Content/Pics/megaMenu/PlacesToGomap/Buddha-park-Ravangla.jpg'></a><p>Buddha park</p></div>");
                        out.print("</div>");
                        break;
                    case "Food":
                        out.println("<h3>Sikkimese Cuisine</h3>");
                        out.println("<p>Try delicious local dishes such as Momos, Thukpa, and Gundruk. Savor the unique flavors of Sikkimese food!</p>");
                        out.print("<div style='display:flex;justify-content:center;'>");
                        out.print("<div><a href='https://en.wikipedia.org/wiki/Momo_(food)'><img src='https://junifoods.com/wp-content/uploads/2023/11/Easy-Chicken-Momo-Dumplings-Sajilo-Kukhura-ko-Momo-%E0%A4%B8%E0%A4%9C%E0%A4%BF%E0%A4%B2%E0%A5%8B-%E0%A4%95%E0%A5%81%E0%A4%96%E0%A5%81%E0%A4%B0%E0%A4%BE%E0%A4%95%E0%A5%8B-%E0%A4%AE%E0%A4%AE-500x375.jpg'></a>");
                        out.print("<p>Momos</p></div>");
                        out.print("<div><a href='https://en.wikipedia.org/wiki/Thukpa'><img src='https://i0.wp.com/foodtrails25.com/wp-content/uploads/2019/09/How-to-make-Thukpa-Noodle-Soup.jpg?fit=1200%2C1200&ssl=1'></a><p>Thukpa</p></div>");
                        out.print("<div><a href='https://en.wikipedia.org/wiki/Gundruk'><img src='https://www.honeymoonbug.com/blog/wp-content/uploads/2022/10/gundruk-and-sinki-sikkim.jpg'></a><p>Gundruk</p></div>");
                        out.print("</div>");
                        break;
                    case "Festivals":
                        out.println("<h3>Festivals & Events</h3>");
                        out.println("<p>Experience the vibrant festivals like Losar, Saga Dawa, and the Pang Lhabsol festival to see Sikkim at its most colorful.</p>");
                        out.print("<div style='display:flex;justify-content:center;'>");
                        out.print("<div><a href='https://en.wikipedia.org/wiki/Losar#:~:text=Losar%20(Tibetan%3A%20%E0%BD%A3%E0%BD%BC%E0%BC%8B%E0%BD%A6%E0%BD%A2,%2C%20Nepal%2C%20India)%20tradition.'><img src='https://st.adda247.com/https://currentaffairs.adda247.com/wp-content/uploads/multisite/sites/5/2022/01/05085607/bhutan-losar-new-year.jpg'></a>");
                        out.print("<p>Losar</p></div>");
                        out.print("<div><a href='https://sikkimtourism.gov.in/Public/ExperienceSikkim/FairsAndFestivalDetails/FF20A077?type=Festival'><img src='https://www.tibettour.org/assets/images/kailash-tour/drepung-monastery-saga-dawa.jpg'></a><p>Saga Dawa/p></div>");
                        out.print("<div><a href='https://sikkimtourism.gov.in/Public/ExperienceSikkim/FairsAndFestivalDetails/FF20A071?type=Festival'><img src='https://i0.wp.com/travelshoebum.com/wp-content/uploads/2018/09/dsc3215.jpg?resize=1200%2C1812&ssl=1'></a><p>Pang Lhabsol</p></div>");
                        out.print("</div>");
                        break;
                }
            }
        } 
        else 
        {
            out.println("<p>Please select at least one interest to get recommendations!</p>");
        }
        
        out.println("<br><button><a href='web8.html'>Back to Travel Guide</a></button>");
        out.println("</center></body></html>");
        out.close();
    }
}
