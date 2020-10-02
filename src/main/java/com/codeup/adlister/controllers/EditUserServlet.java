package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditUserServlet", urlPatterns = "/profile/edit")
public class EditUserServlet extends HttpServlet {

    //    DOGET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/edit_user.jsp").forward(request, response);
    }

    //    DOPOST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // grabs parameters from form
        String new_username = request.getParameter("new_username");
        String new_email = request.getParameter("new_email");
        // tracks which user is currently logged in
        User currentUser = (User) request.getSession().getAttribute("user");


        String new_password = "";
        // checks to see if passwords match
        if(request.getParameter("new_password").equals(request.getParameter("confirm_new_password"))){
            // if they're =, we will assign the password to a new variable
            new_password = request.getParameter("new_password");
        }

        // these are all pretty much doing the same thing. if [the input box] is not empty,
        // we will contact the daofactory to access the users information and will update the
        // current user's information based off of what was filled in.
        if (!new_username.isEmpty()){
            currentUser = DaoFactory.getUsersDao().updateUsername(new_username, currentUser);
        }
        if (!new_password.isEmpty()){
            currentUser = DaoFactory.getUsersDao().updatePassword(new_password, currentUser);
        }
        if (!new_email.isEmpty()){
            currentUser = DaoFactory.getUsersDao().updateEmail(new_email, currentUser);
        }
        if (new_email.isEmpty() && new_password.isEmpty() && new_username.isEmpty()){
            response.sendRedirect("/profile/edit");
        } else {
            request.getSession().setAttribute("user", currentUser);
            response.sendRedirect("/profile");
        }
    }
}