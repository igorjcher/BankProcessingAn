package ru.igor.bankprocessingan.servlets;

import ru.igor.bankprocessingan.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.igor.bankprocessingan.dao.UserDao;

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {

    @Inject
    private UserDao userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (userService == null) {
            out.println("userInfo == null<br>");
            return;
        }

        String userName = request.getParameter("userName");
        String email = request.getParameter("e-mail");

        if (userName == null || email == null) {
            out.println("Invalid params: userName = " + userName + ", email = " + email + "<br>");
            return;
        }

        User user = userService.findUserByEmail(email);
        if (user != null && user.isAdmin()) {
            request.getSession().setAttribute(AttributeConst.USER, user);
            request.getRequestDispatcher("/AdminServlet").forward(request, response);

        } else if (user != null && user.isUser()) {
            request.getSession().setAttribute(AttributeConst.USER, user);
            request.getRequestDispatcher("/WEB-INF/jsp/welcomePage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/LoginServlet").forward(request, response);
        }
    }
}
