package com.loginproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.loginproject.service.LoginService;
 
public class LoginServlet extends HttpServlet {
    private LoginService service = null;
 
    public void init(ServletConfig config) throws ServletException {
        System.out.println("initialized");
        service = new LoginService();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd = null;
        if((username == null || username.isEmpty()) || (password == null || password.isEmpty())){
            rd = request.getRequestDispatcher("failure.jsp");
            rd.forward(request, response);
        }else{
            String message = service.doLogin(username, password);
            if(message.equals("SUCCESS")){
                rd = request.getRequestDispatcher("success.jsp");
                rd.forward(request, response);
            }else if(message.equals("FAILURE")){
                rd = request.getRequestDispatcher("failure.jsp");
                rd.forward(request, response);
            }
        }
    }
}