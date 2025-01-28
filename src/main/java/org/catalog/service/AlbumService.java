package org.catalog.service;

import org.catalog.model.Album;
import org.catalog.model.Executor;
import org.catalog.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class AlbumService {

    public List<Album> getAllAlbums() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Album> query = session.createQuery("from Album", Album.class);
            List<Album> albums = query.list();
            for (Album album : albums) {
                Hibernate.initialize(album.getExecutor());
            }
            return albums;
        }
    }
    
    public Album getAlbumById(Long id){//read
        try(Session session = HibernateUtil.getSession()) {
            Album album = session.get(Album.class, id);
            Hibernate.initialize(album.getExecutor());
            return album;
        }
    }

    public void addAlbum(Album album){//create
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.persist(album);
            transaction.commit();
        }
    }
    
    public void updateAlbum(Album album){//create
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.merge(album);
            transaction.commit();
        }
    }
    
    public void deleteAlbum(Long id){//create
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Album album = session.get(Album.class,id);
            if (album!=null){
                session.remove(album);
            }
            transaction.commit();
        }
    }
}
