package ru.igor.bancprocessingan.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import ru.igor.bancprocessingan.dao.HibernateUtill;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtill.destroyFactory();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateUtill.initFactory();
    } 
}
