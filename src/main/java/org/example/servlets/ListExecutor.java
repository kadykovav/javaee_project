package org.example.servlets;

import org.catalog.model.Executor;
import org.catalog.service.ExecutorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListExecutor extends javax.servlet.http.HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExecutorService executorService = new ExecutorService();
        List<Executor> listExecutors = executorService.getAllExecutors();

        RequestDispatcher dispatcher = req.getRequestDispatcher("/Executor.jsp");
        req.setAttribute("executors",listExecutors);
        dispatcher.forward(req,resp);
    }


}
