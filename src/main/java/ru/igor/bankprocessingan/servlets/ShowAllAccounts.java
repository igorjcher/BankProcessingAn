package ru.igor.bankprocessingan.servlets;

import ru.igor.bankprocessingan.entities.User;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.igor.bankprocessingan.dao.UserDao;

@WebServlet("/ShowAllAccounts")
public class ShowAllAccounts extends HttpServlet {

    @Inject
    UserDao userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //if(!((User)request.getSession().getAttribute(AttributeConst.USER)).isAdmin()) return;
        List<User> users = userService.findAllUsers();
        request.setAttribute(AttributeConst.USERS, users);
        
        request.getRequestDispatcher("/WEB-INF/jsp/showAllUsers.jsp").forward(request, response);
    }
}
