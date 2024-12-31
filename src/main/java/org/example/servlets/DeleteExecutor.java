package org.example.servlets;

import org.catalog.service.ExecutorService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteExecutor extends javax.servlet.http.HttpServlet{

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        ExecutorService executorService = new ExecutorService();
        executorService.deleteExecutor(id);
        resp.sendRedirect(req.getContextPath() + "/executors");
    }
}
