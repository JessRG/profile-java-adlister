package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.UpdateAdsServlet", urlPatterns = "/update")
public class UpdateAdsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        String adId = request.getParameter("update_id");
        request.setAttribute("adId", adId);
        request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Need to add the functionality for updating an ad
        Long adId = Long.parseLong(request.getParameter("adId"));
        String title = request.getParameter("new_title");
        String description = request.getParameter("new_description");
        DaoFactory.getAdsDao().updateAd(adId, title, description);
        response.sendRedirect("/profile");
    }
}
