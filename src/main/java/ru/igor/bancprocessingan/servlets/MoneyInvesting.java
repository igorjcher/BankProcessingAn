package ru.igor.bancprocessingan.servlets;

import ru.igor.bancprocessingan.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.igor.bancprocessingan.dao.UserService;

@WebServlet("/MoneyInvesting")
public class MoneyInvesting extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer moneyToInvest = Integer.valueOf(request.getParameter("moneytoinvest"));
        User user = (User) request.getSession().getAttribute(AttributeConst.USER);
        String name = user.getName();
        try {
            userService.deposit(name, moneyToInvest, user);
            request.getRequestDispatcher("/WEB-INF/jsp/balance.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute(AttributeConst.ERROR, e);
            request.getRequestDispatcher("ErrorPage").forward(request, response);
        }
    }
}
