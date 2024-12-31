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

public class MainServlet extends javax.servlet.http.HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Main.jsp");
//        ExecutorService eS = new ExecutorService();
//        AlbumService aS = new AlbumService();
//        CompositionService cS = new CompositionService();
//
//        List<Executor> listE = eS.getAllExecutors();
//        List<Album> listA = aS.getAllAlbums();
//        List<Composition> listC = cS.getAllCompositions();
//
//        req.setAttribute("executors",listE);
//        req.setAttribute("albums",listA);
//        req.setAttribute("compositions",listC);

        dispatcher.forward(req, resp);

    }
}
