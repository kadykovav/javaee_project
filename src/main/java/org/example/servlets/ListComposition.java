package org.example.servlets;

import org.catalog.model.Composition;
import org.catalog.service.CompositionService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListComposition extends javax.servlet.http.HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CompositionService compositionService = new CompositionService();
        List<Composition> compositionList = compositionService.getAllCompositions();

        RequestDispatcher dispatcher = req.getRequestDispatcher("/Composition.jsp");
        req.setAttribute("compositions",compositionList);
        dispatcher.forward(req,resp);
    }

}
