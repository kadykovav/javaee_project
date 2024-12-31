package org.example.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.catalog.model.Executor;
import org.catalog.service.ExecutorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = "/executorsajax")
public class ListExecutorsAjax extends javax.servlet.http.HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExecutorService executorService = new ExecutorService();
//
//        List<Executor> listExecutors = executorService.getAllExecutors();
        //
        request.setCharacterEncoding("UTF-8");
        String query = request.getParameter("query");

        List<Executor> listExecutors = executorService.findExecutors(query);
        //
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(listExecutors);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
//private static final Logger log = LoggerFactory.getLogger(ListExecutorsAjax.class);