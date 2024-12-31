package org.example.servlets;

import org.catalog.model.Album;
import org.catalog.service.AlbumService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListAlbum extends javax.servlet.http.HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AlbumService albumService = new AlbumService();
        List<Album> listAlbums = albumService.getAllAlbums();


        RequestDispatcher dispatcher = req.getRequestDispatcher("/Album.jsp");
        req.setAttribute("albums",listAlbums);
        dispatcher.forward(req,resp);
    }
}
