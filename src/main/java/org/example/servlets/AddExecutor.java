package org.example.servlets;

import org.catalog.model.Executor;
import org.catalog.service.ExecutorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddExecutor extends javax.servlet.http.HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/AddExecutor.jsp");
        dispatcher.forward(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");

        ExecutorService executorService = new ExecutorService();
        Executor newExecutor = new Executor();
        newExecutor.setName(name);
        executorService.addExecutor(newExecutor);

        resp.setContentType("text/plain");
        resp.getWriter().write("OK");
    }
}
