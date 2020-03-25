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

@WebServlet(urlPatterns = {"/UserCreation"})
public class UserCreation extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    public void init() throws ServletException {
        System.out.println("ínit() " + userService);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer balance = Integer.valueOf(request.getParameter("balance")); 
        String userName = request.getParameter("userName");
        Integer userAge = Integer.valueOf(request.getParameter("userAge")); 
        String email = request.getParameter("e-mail");
        
        User user = null;

        try {
            user = userService.createUser(email, userName, userAge, balance);
            request.getSession().setAttribute(AttributeConst.USER, user);
        } catch (IllegalArgumentException e) {
            request.setAttribute(AttributeConst.ERROR, e);
            request.getRequestDispatcher("CommonExceptions").forward(request, response);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/welcomePage.jsp").forward(request, response);
    }
}
