import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class compatibility extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data for each individual (as before)
        String man1Cuisine = request.getParameter("man1_cuisine");
        String man1Exercise = request.getParameter("man1_exercise");
        String man1Movie = request.getParameter("man1_movie");
        String man1Travel = request.getParameter("man1_travel");
        String man1Weather = request.getParameter("man1_weather");
        String man1Vacation = request.getParameter("man1_vacation");

        String man2Cuisine = request.getParameter("man2_cuisine");
        String man2Exercise = request.getParameter("man2_exercise");
        String man2Movie = request.getParameter("man2_movie");
        String man2Travel = request.getParameter("man2_travel");
        String man2Weather = request.getParameter("man2_weather");
        String man2Vacation = request.getParameter("man2_vacation");

        String man3Cuisine = request.getParameter("man3_cuisine");
        String man3Exercise = request.getParameter("man3_exercise");
        String man3Movie = request.getParameter("man3_movie");
        String man3Travel = request.getParameter("man3_travel");
        String man3Weather = request.getParameter("man3_weather");
        String man3Vacation = request.getParameter("man3_vacation");

        String woman1Cuisine = request.getParameter("woman1_cuisine");
        String woman1Exercise = request.getParameter("woman1_exercise");
        String woman1Movie = request.getParameter("woman1_movie");
        String woman1Travel = request.getParameter("woman1_travel");
        String woman1Weather = request.getParameter("woman1_weather");
        String woman1Vacation = request.getParameter("woman1_vacation");

        String woman2Cuisine = request.getParameter("woman2_cuisine");
        String woman2Exercise = request.getParameter("woman2_exercise");
        String woman2Movie = request.getParameter("woman2_movie");
        String woman2Travel = request.getParameter("woman2_travel");
        String woman2Weather = request.getParameter("woman2_weather");
        String woman2Vacation = request.getParameter("woman2_vacation");

        String woman3Cuisine = request.getParameter("woman3_cuisine");
        String woman3Exercise = request.getParameter("woman3_exercise");
        String woman3Movie = request.getParameter("woman3_movie");
        String woman3Travel = request.getParameter("woman3_travel");
        String woman3Weather = request.getParameter("woman3_weather");
        String woman3Vacation = request.getParameter("woman3_vacation");

        // Initialize compatibility scores matrix
        int[][] compatibilityMatrix = new int[3][3];  // 3 men, 3 women
        
        // Populate the compatibility matrix based on form data (comparing preferences)
        compatibilityMatrix[0][0] = comparePreferences(man1Cuisine, woman1Cuisine) +
                                    comparePreferences(man1Exercise, woman1Exercise) +
                                    comparePreferences(man1Movie, woman1Movie) +
                                    comparePreferences(man1Travel, woman1Travel) +
                                    comparePreferences(man1Weather, woman1Weather) +
                                    comparePreferences(man1Vacation, woman1Vacation);
        
        compatibilityMatrix[0][1] = comparePreferences(man1Cuisine, woman2Cuisine) +
                                    comparePreferences(man1Exercise, woman2Exercise) +
                                    comparePreferences(man1Movie, woman2Movie) +
                                    comparePreferences(man1Travel, woman2Travel) +
                                    comparePreferences(man1Weather, woman2Weather) +
                                    comparePreferences(man1Vacation, woman2Vacation);
        
        compatibilityMatrix[0][2] = comparePreferences(man1Cuisine, woman3Cuisine) +
                                    comparePreferences(man1Exercise, woman3Exercise) +
                                    comparePreferences(man1Movie, woman3Movie) +
                                    comparePreferences(man1Travel, woman3Travel) +
                                    comparePreferences(man1Weather, woman3Weather) +
                                    comparePreferences(man1Vacation, woman3Vacation);
        
        compatibilityMatrix[1][0] = comparePreferences(man2Cuisine, woman1Cuisine) +
                                    comparePreferences(man2Exercise, woman1Exercise) +
                                    comparePreferences(man2Movie, woman1Movie) +
                                    comparePreferences(man2Travel, woman1Travel) +
                                    comparePreferences(man2Weather, woman1Weather) +
                                    comparePreferences(man2Vacation, woman1Vacation);
        
        compatibilityMatrix[1][1] = comparePreferences(man2Cuisine, woman2Cuisine) +
                                    comparePreferences(man2Exercise, woman2Exercise) +
                                    comparePreferences(man2Movie, woman2Movie) +
                                    comparePreferences(man2Travel, woman2Travel) +
                                    comparePreferences(man2Weather, woman2Weather) +
                                    comparePreferences(man2Vacation, woman2Vacation);
        
        compatibilityMatrix[1][2] = comparePreferences(man2Cuisine, woman3Cuisine) +
                                    comparePreferences(man2Exercise, woman3Exercise) +
                                    comparePreferences(man2Movie, woman3Movie) +
                                    comparePreferences(man2Travel, woman3Travel) +
                                    comparePreferences(man2Weather, woman3Weather) +
                                    comparePreferences(man2Vacation, woman3Vacation);
        
        compatibilityMatrix[2][0] = comparePreferences(man3Cuisine, woman1Cuisine) +
                                    comparePreferences(man3Exercise, woman1Exercise) +
                                    comparePreferences(man3Movie, woman1Movie) +
                                    comparePreferences(man3Travel, woman1Travel) +
                                    comparePreferences(man3Weather, woman1Weather) +
                                    comparePreferences(man3Vacation, woman1Vacation);
        
        compatibilityMatrix[2][1] = comparePreferences(man3Cuisine, woman2Cuisine) +
                                    comparePreferences(man3Exercise, woman2Exercise) +
                                    comparePreferences(man3Movie, woman2Movie) +
                                    comparePreferences(man3Travel, woman2Travel) +
                                    comparePreferences(man3Weather, woman2Weather) +
                                    comparePreferences(man3Vacation, woman2Vacation);
        
        compatibilityMatrix[2][2] = comparePreferences(man3Cuisine, woman3Cuisine) +
                                    comparePreferences(man3Exercise, woman3Exercise) +
                                    comparePreferences(man3Movie, woman3Movie) +
                                    comparePreferences(man3Travel, woman3Travel) +
                                    comparePreferences(man3Weather, woman3Weather) +
                                    comparePreferences(man3Vacation, woman3Vacation);

        // Initialize current matches
        int[] currentMatches = new int[3];  // -1 means no match, otherwise the index of the woman matched
        Arrays.fill(currentMatches, -1);
        
        // Proposals tracker
        boolean[] womanProposedTo = new boolean[3];  // Tracks if a woman has been proposed to

        // Loop until all men are matched
        boolean allMatched = false;
        while (!allMatched) {
            allMatched = true;
            for (int man = 0; man < 3; man++) {
                if (currentMatches[man] == -1) {  // If this man is not yet matched
                    // Try proposing to the highest preference woman that has not yet rejected him
                    for (int woman = 0; woman < 3; woman++) {
                        int score = compatibilityMatrix[man][woman];

                        if (currentMatches[woman] == -1 || score > compatibilityMatrix[currentMatches[woman]][woman]) {
                            // If the woman is not yet matched or prefers the new man
                            currentMatches[woman] = man;
                            womanProposedTo[woman] = true;  // Mark this woman as matched
                            break;
                        }
                    }
                }
            }

            // Check if all men are matched
            for (int i = 0; i < 3; i++) {
                if (currentMatches[i] == -1) {
                    allMatched = false;
                    break;
                }
            }
        }

        // Response Output - Compatibility Matrix Table
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><style>body{background-image:url('https://img.freepik.com/free-vector/elegant-abstract-gold-background-with-shiny-elements_23-2148827899.jpg');background-size:cover;}</style></head><body><center>");
        out.println("<h2>Compatibility Matrix</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>Man / Woman</th><th>Woman 1</th><th>Woman 2</th><th>Woman 3</th></tr>");
        for (int i = 0; i < 3; i++) {
            out.print("<tr><td>Man " + (i + 1) + "</td>");
            for (int j = 0; j < 3; j++) {
                out.print("<td>" + compatibilityMatrix[i][j] + "</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
        
        // Output the best matches
        out.println("<h2>Best Matches</h2>");
        for (int i = 0; i < 3; i++) {
            String bestMatch = "Woman " + (currentMatches[i] + 1);
            out.println("<h3>Best Match for Man " + (i + 1) + ": " + bestMatch + "</h3>");
        }
        out.println("</center/></body></html>");
    }

    // Method to compare preferences (1 if they match, 0 if they don't)
    private int comparePreferences(String manPreference, String womanPreference) {
        return manPreference.equals(womanPreference) ? 1 : 0;
    }
}
