import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetCookiesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        PrintWriter printWriter = response.getWriter();
        printWriter.println("<html>");
        for(Cookie c : cookies){
            printWriter.println("<h1> " + c.getName() + " " + c.getValue() + " </h1>");
        }
        printWriter.println("</html");
    }
}
