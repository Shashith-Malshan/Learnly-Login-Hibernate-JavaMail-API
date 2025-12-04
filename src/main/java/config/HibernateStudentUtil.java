package config;

import model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateStudentUtil {
    private static SessionFactory sessionFactory;
    private static Configuration config = new Configuration();

    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            config.configure();
            config.addAnnotatedClass(Student.class);

            sessionFactory = config.buildSessionFactory();
        }
        return sessionFactory;
    }

}
