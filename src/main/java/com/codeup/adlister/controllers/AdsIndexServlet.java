package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/ads")
public class AdsIndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ads", DaoFactory.getAdsDao().all());
        String searchTerm = request.getParameter("search");
        String searchCategory = request.getParameter("category");

        // Search By Ad title or description
        if(searchTerm == null || searchCategory == null) {
            request.setAttribute("ads", DaoFactory.getAdsDao().all());
        } else {
//            String search = request.getParameter("search");
            request.setAttribute("ads", DaoFactory.getAdsDao().search(searchTerm));
        }

        // Search Ad by category selection
        if (searchCategory != null) {
            Long catId = Long.parseLong(searchCategory);
            request.setAttribute("ads", DaoFactory.getAdsDao().search(catId));
        }

        request.setAttribute("allCategories", DaoFactory.getCategoriesDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }
}
