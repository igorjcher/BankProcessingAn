package ru.igor.bankprocessingan.exceptions;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.igor.bankprocessingan.servlets.AttributeConst;

@WebServlet("/ErrorPage")
public class ErrorPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Exception e = (Exception) request.getAttribute(AttributeConst.ERROR);
        
        PrintWriter out = response.getWriter();
        out.write("<h1>Error " + e.getMessage() + "</h1>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Exception e = (Exception) request.getAttribute(AttributeConst.ERROR);
        
        PrintWriter out = response.getWriter();
        out.write("<h1>Error " + e.getMessage() + "</h1>");
    }
}
