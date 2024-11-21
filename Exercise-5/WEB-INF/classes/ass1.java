import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Arrays;

public class ass1 extends HttpServlet 
{
    private final double[] toxicity = {7.5, 5, 9.1, 3, 6, 8, 6.9, 8.7, 5.4, 4};
    private final double[] energy = {6.4, 8.7, 8.5, 7, 6, 7.5, 6.9, 8, 9, 7.1};

    
    private final String[] foodItems = {
        "PaniPuri", "Pizza", "Burger", "Sandwich", "Cutlet",
        "French-fries", "Veg-roll", "Puffs", "Samosa", "Bhel-puri"
    };

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();


    String name = request.getParameter("name");
    String ageStr = request.getParameter("age");
    String gender = request.getParameter("gender");
    String[] selectedFoods = request.getParameterValues("food-item");

    
    int age = 0;
    int count = 0;
    double sum = 0.0;

    
    try {
        age = Integer.parseInt(ageStr);
        if (age < 1 || age > 100) {
            throw new NumberFormatException("Age out of range");
        }
    } catch (NumberFormatException e) {
        out.println("<html><body>");
        out.println("<script>alert('Invalid age provided. Please enter a valid age between 1 and 100');</script>");
        out.println("</body></html>");
        return;
    }

    
    if (selectedFoods != null) {
        count = selectedFoods.length;
        if (count > 5) {
            out.println("<html><body>");
            out.println("<script>alert('You can only choose up to 5 food items.');</script>");
            out.println("</body></html>");
            return;
        }
        
        for (String food : selectedFoods) {
            int index = Arrays.asList(foodItems).indexOf(food);
            if (index != -1) {
                sum += (energy[index] - toxicity[index]);
            }
        }
    }

    
    String prediction = predictDeathTimeline(sum, age);

    
    out.println("<html>");
    out.println("<head><title>Death Timeline Prediction</title>");
    out.println("<style>");
    out.println("body {"
            + "background-image: url('https://images.unsplash.com/photo-1638443523564-46b8a648c5a1?fm=jpg&w=3000&auto=format&fit=crop&q=60&ixlib=rb-4.0.3');"
            + "background-size: cover;"
            + "background-position: center;"
            + "height: 100vh;"
            + "margin: 0;"
            + "display: flex;"
            + "justify-content: center;"
            + "align-items: center;"
            + "font-family: Arial, sans-serif;"
            + "color: white;"
            + "}");
    out.println(".box {"
            + "background-color: rgba(0, 0, 0, 0.7);"
            + "border-radius: 10px;"
            + "padding: 30px;"
            + "text-align: center;"
            + "max-width: 500px;"
            + "box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.5);"
            + "}");
    out.println(".box img {"
            + "width: 100%;"
            + "height: auto;"
            + "border-radius: 10px;"
            + "}");
    out.println("h2 {"
            + "margin-top: 20px;"
            + "font-size: 1.5em;"
            + "}");
    out.println("p {"
            + "font-size: 1.2em;"
            + "}");
    out.println("</style></head>");
    out.println("<body>");
    out.println("<div class='box'>");
    out.println("<p>Selected Food Items:</p>");
    out.println("<p>" + (selectedFoods != null ? String.join("<br>", selectedFoods) : "None") + "</p>");
    out.println("<h2>" + prediction + "</h2>");
    out.println("<img src=\"https://media.tenor.com/sHCNiDzcMpMAAAAM/artc-dig-your-own-grave.gif\" alt=\"grave\">");
    out.println("</div>");
    out.println("</body>");
    out.println("</html>");

    out.close();
    }


    private String predictDeathTimeline(double sum, int age) {
        if (sum < 0) {
            if (age > 70) {
                return "You will die in 2 years.";
            } else if (age > 50 && age <= 70) {
                return "You will die in 7 years.";
            } else if (age > 35 && age <= 50) {
                return "You will die in 9 years.";
            } else {
                return "You will die in 12 years.";
            }
        } else if (sum < 3.5) {
            if (age > 70) {
                return "You will die in 4 years.";
            } else if (age > 50 && age <= 70) {
                return "You will die in 8 years.";
            } else if (age > 35 && age <= 50) {
                return "You will die in 10 years.";
            } else {
                return "You will die in 14 years.";
            }
        } else if (sum < 7) {
            if (age > 70) {
                return "You will die in 5 years.";
            } else if (age > 50 && age <= 70) {
                return "You will die in 10 years.";
            } else if (age > 35 && age <= 50) {
                return "You will die in 12 years.";
            } else {
                return "You will die in 16 years.";
            }
        } else {
            if (age > 70) {
                return "You will die in 10 years.";
            } else if (age > 50 && age <= 70) {
                return "You will die in 13 years.";
            } else if (age > 35 && age <= 50) {
                return "You will die in 17 years.";
            } else {
                return "You will die in 20 years.";
            }
        }
    }
}