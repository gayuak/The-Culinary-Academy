package lk.ijse.theculinaryacademy.config;

import lk.ijse.theculinaryacademy.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfig {

    private static SessionFactoryConfig factoryConfiguration;
    private final SessionFactory sessionFactory;
    private SessionFactoryConfig() throws IOException {
        sessionFactory = new Configuration().mergeProperties(Utility.getProperties())
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Student_Course.class)
                .buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance() throws IOException {
        return (factoryConfiguration==null) ? factoryConfiguration=new SessionFactoryConfig():factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

}
