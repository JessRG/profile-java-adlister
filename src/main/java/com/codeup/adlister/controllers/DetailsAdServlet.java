package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DetailsAdServlet", urlPatterns = "/details")
public class DetailsAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ads", DaoFactory.getAdsDao().all());

        String adId = request.getParameter("id");
        int id = Integer.parseInt(adId);
        request.setAttribute("user", DaoFactory.getAdsDao().getUserInfo(id));
        //request.setAttribute("user", DaoFactory.getUsersDao().findUserwithAdId(id));
        //request.setAttribute("category", DaoFactory.getCategoriesDao().findCategorywithAdId(id));
        request.getRequestDispatcher("/WEB-INF/ads/adDetails.jsp").forward(request, response);
    }
}






