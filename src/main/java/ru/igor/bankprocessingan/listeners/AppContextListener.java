package ru.igor.bankprocessingan.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import ru.igor.bankprocessingan.db.HibernateUtill;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtill.destroyFactory();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Before init Hibernate factory.");
        HibernateUtill.initFactory();
        System.out.println("After init Hibernate factory.");
    } 
}
