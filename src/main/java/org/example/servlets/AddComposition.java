package org.example.servlets;

import org.catalog.model.Album;
import org.catalog.model.Composition;
import org.catalog.model.Executor;
import org.catalog.service.AlbumService;
import org.catalog.service.CompositionService;
import org.catalog.service.ExecutorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddComposition extends javax.servlet.http.HttpServlet{
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AlbumService albumService = new AlbumService();
        List<Album> albumList = albumService.getAllAlbums();

        req.setAttribute("albums", albumList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/AddComposition.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String length = req.getParameter("length");
        Long albumId = Long.parseLong(req.getParameter("albumId"));

        AlbumService albumService = new AlbumService();
        Album album = albumService.getAlbumById(albumId);

        CompositionService compositionService = new CompositionService();

        Composition newComposition = new Composition();

        newComposition.setName(name);
        newComposition.setAlbum(album);
        newComposition.setLength(length);

        compositionService.addComposition(newComposition);

        resp.sendRedirect(req.getContextPath() + "/compositions");
    }
}
