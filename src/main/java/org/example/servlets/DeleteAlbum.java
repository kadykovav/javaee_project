package org.example.servlets;

import org.catalog.service.AlbumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAlbum extends javax.servlet.http.HttpServlet{

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        AlbumService albumService = new AlbumService();
        albumService.deleteAlbum(id);
        resp.sendRedirect(req.getContextPath() + "/albums");
    }
}
