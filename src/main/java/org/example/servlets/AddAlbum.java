package org.example.servlets;

import org.catalog.model.Album;
import org.catalog.model.Executor;
import org.catalog.service.AlbumService;
import org.catalog.service.ExecutorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddAlbum extends javax.servlet.http.HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ExecutorService executorService = new ExecutorService();
        List<Executor> executorList = executorService.getAllExecutors();

        req.setAttribute("executors", executorList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/AddAlbum.jsp");
        dispatcher.forward(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String genre = req.getParameter("genre");
        System.out.println(req.getParameterMap().toString());
        String nameExecutor = req.getParameter("executorName");
        System.out.println();
        System.out.println(req.getParameter("executorName"));
        System.out.println();


        ExecutorService executorService = new ExecutorService();
        Executor executor = executorService.getExecutorByName(nameExecutor);

        AlbumService albumService = new AlbumService();
        Album newAlbum = new Album();

        newAlbum.setName(name);
        newAlbum.setExecutor(executor);
        newAlbum.setGenre(genre);

        albumService.addAlbum(newAlbum);

        resp.sendRedirect(req.getContextPath() + "/albums");
    }
}
