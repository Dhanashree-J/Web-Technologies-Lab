import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class sample extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String num1Str = request.getParameter("number1");
        String num2Str = request.getParameter("number2");
        String operator = request.getParameter("operation");
        double result = 0.0;
        int number1 = Integer.parseInt(num1Str);
        int number2 = Integer.parseInt(num2Str);
        switch (operator) 
        {
            case "add":
                result = number1 + number2;
                break;
            case "sub":
                result = number1 - number2;
                break;
            case "multiply":
                result = number1 * number2;
                break;
            case "divide":
                if (number2 != 0) 
                {
                    result = number1 / number2;
                } 
                else 
                {
                    out.println("<html><body><center><p>Error: Division by zero is not allowed.</p></center></body></html>");
                    return;
                }
                break;
            case "mod":
                if (number2 != 0) 
                {
                    result = number1 % number2;
                } 
                else 
                {
                    out.println("<html><body><center><p>Error: Division by zero is not allowed.</p></center></body></html>");
                    return;
                }
                break;
            case "exp":
                result = Math.pow(number1, number2);
        }
        out.println("<html><body style=\"background-image:url('https://image.slidesdocs.com/responsive-images/background/handdrawn-style-blue-cute-education-math-lovely-powerpoint-background_a698b5341c__960_540.jpg');background-size:100%;\">");
        out.println("<center style='margin-top:18%;'s>");
        out.println("<h2>Your result!!!");
        out.println("<p style=\"border:1px solid black;margin:10px 38%;padding:10px;text-align:center;\">Result is: <br>" + result + "</p>");
        out.println("</center></body></html>");
    }
}
