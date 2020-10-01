package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adDetails", urlPatterns = "/details")
public class DetailsAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ads", DaoFactory.getAdsDao().all());

        String adId = request.getParameter("id");
        Long id = Long.parseLong(adId);
        request.setAttribute("ad", DaoFactory.getAdsDao().getUserInfo(id));
        request.setAttribute("user", DaoFactory.getUsersDao().findUserwithAdId(id));
        request.setAttribute("category", DaoFactory.getCategoriesDao().findCategorywithAdId(id));
        request.getRequestDispatcher("/WEB-INF/ads/adDetail.jsp").forward(request, response);
    }
}






