package org.catalog.service;

import org.catalog.model.Executor;
import org.catalog.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class ExecutorService {

    public Executor getExecutorByName(String name) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Executor> query = session.createQuery(
                "FROM Executor WHERE name = :name", Executor.class
            );
            query.setParameter("name", name);
            return query.uniqueResult();
        }
    }

    public List<Executor> findExecutors(String prefix) {
        List<Executor> allExecutors = getAllExecutors();
        return allExecutors.stream()
                .filter(executor -> executor.getName().toLowerCase().startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<Executor> getAllExecutors() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Executor> query = session.createQuery("from Executor", Executor.class);
            return query.list();
        }
    }

    public Executor getExecutorById(Long id){
        try(Session session = HibernateUtil.getSession()) {
            return session.get(Executor.class, id);
        }
    }

    public void addExecutor(Executor executor){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.persist(executor);
            transaction.commit();
        }
    }

    public void updateExecutor(Executor executor){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.merge(executor);
            transaction.commit();
        }
    }
    
    public void deleteExecutor(Long id){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Executor executor = session.get(Executor.class,id);
            if (executor!=null){
                session.remove(executor);
            }
            transaction.commit();
        }
    }
}
