package ru.igor.bankprocessingan.servlets;

import ru.igor.bankprocessingan.entities.User;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.igor.bankprocessingan.dao.UserDao;

@WebServlet("/MoneyInvesting")
public class MoneyInvesting extends HttpServlet {

    @Inject
    private UserDao userService;

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
