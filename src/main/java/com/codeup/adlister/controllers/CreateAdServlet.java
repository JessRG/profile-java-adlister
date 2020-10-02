package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        // Create list for categories then store list into session
        request.setAttribute("allCategories", DaoFactory.getCategoriesDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
            .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Grab the user's entered info for the new ad
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        // Grab the list of categories from create.jsp
        String[] categories = request.getParameterValues("category");
        User user = (User) request.getSession().getAttribute("user");
        Ad ad = new Ad(
            user.getId(),
            title,
            description
        );

        try {
            // Grab the recently created ad id
            long adId = DaoFactory.getAdsDao().insert(ad);
            // Create the relationship between ad and category
            for(String catId : categories) {
                DaoFactory.getAdsDao().setAdCategories(adId, Long.parseLong(catId));
            }
            response.sendRedirect("/ads");
        } catch (RuntimeException e) {
            request.setAttribute("stickyTitle", title);
            request.setAttribute("stickyDescription", description);
            request.setAttribute("allCategories", DaoFactory.getCategoriesDao().all());
            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
        }
    }
}
