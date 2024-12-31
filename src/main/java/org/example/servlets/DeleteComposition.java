package org.example.servlets;

import org.catalog.service.AlbumService;
import org.catalog.service.CompositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteComposition extends javax.servlet.http.HttpServlet{

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        CompositionService compositionService = new CompositionService();
        compositionService.deleteComposition(id);
        resp.sendRedirect(req.getContextPath() + "/compositions");
    }
}
