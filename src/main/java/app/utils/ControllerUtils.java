package app.utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ControllerUtils {

    public static void showPage(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    public static boolean checkUserSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            ControllerUtils.showPage(request, response, JspPathUtils.LOGIN_PAGE);
            return false;
        }
        return true;
    }

    public static long ParseStringToLong(String line) {
        return line.equals("") ? 0 : Long.valueOf(line);
    }
}
