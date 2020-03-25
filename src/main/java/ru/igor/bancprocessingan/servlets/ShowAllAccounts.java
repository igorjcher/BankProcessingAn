package ru.igor.bancprocessingan.servlets;

import ru.igor.bancprocessingan.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.igor.bancprocessingan.dao.UserService;

@WebServlet("/ShowAllAccounts")
public class ShowAllAccounts extends HttpServlet {

    @Inject
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //if(!((User)request.getSession().getAttribute(AttributeConst.USER)).isAdmin()) return;
        List<User> users = userService.findAllUsers();
        request.setAttribute(AttributeConst.USERS, users);
        
        request.getRequestDispatcher("/WEB-INF/jsp/showAllUsers.jsp").forward(request, response);
    }
}
