package org.catalog.service;

import org.catalog.model.Album;
import org.catalog.model.Composition;
import org.catalog.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class CompositionService {

    public List<Composition> getAllCompositions() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Composition> query = session.createQuery("from Composition", Composition.class);
            List<Composition> compositions = query.list();
            for (Composition c : compositions){
                Hibernate.initialize(c.getAlbum());
            }
            return compositions;
        }
    }

    public Composition getCompositionById(Long id){//read
        try(Session session = HibernateUtil.getSession()) {
            Composition composition = session.get(Composition.class,id);
            Hibernate.initialize(composition.getAlbum());
            return composition;
        }
    }

    public void addComposition(Composition composition){//create
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.persist(composition);
            transaction.commit();
        }
    }
    
    public void updateComposition(Composition composition){//create
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.merge(composition);
            transaction.commit();
        }
    }
    
    public void deleteComposition(Long id){//create
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Composition composition = session.get(Composition.class,id);
            if (composition!=null){
                session.remove(composition);
            }
            transaction.commit();
        }
    }
}
