package org.example.servlets;

import org.catalog.model.Executor;
import org.catalog.service.ExecutorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditExecutor extends javax.servlet.http.HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/EditExecutor.jsp");
        ExecutorService executorService = new ExecutorService();
        Long id = Long.parseLong(req.getParameter("id"));
        Executor executor = executorService.getExecutorById(id);
        req.getSession().setAttribute("executor", executor);
        dispatcher.forward(req,resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("id"));
        ExecutorService executorService = new ExecutorService();
        Executor executor = executorService.getExecutorById(id);
        executor.setName(req.getParameter("name"));
        executorService.updateExecutor(executor);
        resp.sendRedirect(req.getContextPath() + "/executors");
    }
}
