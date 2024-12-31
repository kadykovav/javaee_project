package org.example;

import org.catalog.model.*;
import org.catalog.service.*;
import org.catalog.util.HibernateUtil;
import org.hibernate.Session;


public class App
{
    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSession();

        session.beginTransaction();


        ExecutorService executorService = new ExecutorService();
        System.out.println(executorService.getExecutorByName("Executor 3").getId());

//        for (int i = 0; i < 5; i++) {
//            Executor executor = new Executor();
//            executor.setName("Исполнитель " + (i+1));
//            executorService.addExecutor(executor);
//        }
////
//        AlbumService albumService = new AlbumService();
//        Long count = 1L;
//        for (int i = 0; i < 5; i++) {
//            Album album = new Album();
//            album.setName("Альбом " + count);
//            album.setGenre("Жанр " + count);
//            album.setExecutor(executorService.getExecutorById( count ) );
//
//            albumService.addAlbum(album);
//            count++;
//        }
//        count = 1L;
//        CompositionService compositionService = new CompositionService();
//
//        for (int i = 0; i < 5; i++) {
//            Composition composition = new Composition();
//            composition.setName("Композиция " + count);
//            composition.setLength(String.valueOf(count));
//            composition.setAlbum(albumService.getAlbumById( count ) );
//
//            compositionService.addComposition(composition);
//            count++;
//        }

        session.getTransaction().commit();
    }
}
