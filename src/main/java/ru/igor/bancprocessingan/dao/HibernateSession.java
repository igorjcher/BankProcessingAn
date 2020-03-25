package ru.igor.bancprocessingan.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import org.hibernate.Session;

@RequestScoped
public class HibernateSession {
    private Session session;
    
    @PostConstruct
    public void postConstruct() {
        session = HibernateUtill.openSession();
    }

    @PreDestroy
    private void preDestroy() {
        HibernateUtill.closeSession(session);
    }
    
    public Session get() {
        return session;
    }
}
