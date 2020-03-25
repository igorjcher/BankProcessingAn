package ru.igor.bancprocessingan.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.igor.bancprocessingan.dao.UserService;
import ru.igor.bancprocessingan.entities.User;

@WebServlet("/MoneyTransfer")
public class MoneyTransfer extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String accountTo = request.getParameter("accountTo");
        Integer amount = Integer.parseInt(request.getParameter("amount"));
        User user = (User) request.getSession().getAttribute(AttributeConst.USER);
        try {
            userService.transfer(accountTo, amount, user);
            request.getRequestDispatcher("/WEB-INF/jsp/balance.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute(AttributeConst.ERROR, e);
            request.getRequestDispatcher("ErrorPage").forward(request, response);
        }
    }
}
