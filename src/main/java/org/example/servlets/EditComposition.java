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

public class EditComposition extends javax.servlet.http.HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/EditComposition.jsp");
        CompositionService compositionService = new CompositionService();
        Long id = Long.parseLong(req.getParameter("id"));
        Composition composition = compositionService.getCompositionById(id);
        AlbumService albumService = new AlbumService();
        List<Album> albumList = albumService.getAllAlbums();

        req.getSession().setAttribute("albums", albumList);
        req.getSession().setAttribute("composition", composition);
        dispatcher.forward(req,resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String length = req.getParameter("length");
        Long albumId = Long.parseLong(req.getParameter("albumId"));

        AlbumService albumService = new AlbumService();
        Album album = albumService.getAlbumById(albumId);

        CompositionService compositionService = new CompositionService();
        Composition upComposition = compositionService.getCompositionById(id);

        upComposition.setName(name);
        upComposition.setLength(length);
        upComposition.setAlbum(album);

        compositionService.updateComposition(upComposition);

        resp.sendRedirect(req.getContextPath() + "/compositions");
    }
}
