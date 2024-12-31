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

public class EditAlbum extends javax.servlet.http.HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/EditAlbum.jsp");
        AlbumService albumService = new AlbumService();
        Long id = Long.parseLong(req.getParameter("id"));
        Album album = albumService.getAlbumById(id);
        ExecutorService executorService = new ExecutorService();
        List<Executor> executorList = executorService.getAllExecutors();

        req.getSession().setAttribute("executors", executorList);
        req.getSession().setAttribute("album", album);
        dispatcher.forward(req,resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String genre = req.getParameter("genre");
        Long executorId = Long.parseLong(req.getParameter("executorId"));

        ExecutorService executorService = new ExecutorService();
        Executor executor = executorService.getExecutorById(executorId);

        AlbumService albumService = new AlbumService();
        Album upAlbum = albumService.getAlbumById(id);

        upAlbum.setName(name);
        upAlbum.setGenre(genre);
        upAlbum.setExecutor(executor);

        albumService.updateAlbum(upAlbum);

        resp.sendRedirect(req.getContextPath() + "/albums");
    }
}
