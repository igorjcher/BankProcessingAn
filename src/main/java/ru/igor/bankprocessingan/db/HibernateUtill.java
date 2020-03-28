package ru.igor.bankprocessingan.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtill {
    private static SessionFactory sessionFactory;

    public static synchronized void initFactory() {
        if (sessionFactory != null) return;
        
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            System.out.println("Hibernate sessionFactory has been created.");
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static synchronized void destroyFactory() {
        if (sessionFactory == null) return;
        
        sessionFactory.close();
        sessionFactory = null;
    }
    
    public static synchronized Session openSession() {
        return sessionFactory.openSession();
    }
    
    public static synchronized void closeSession(Session session) {
        session.close();
    }
}
