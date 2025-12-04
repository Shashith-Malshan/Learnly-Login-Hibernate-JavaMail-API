package controller.impl;

import config.HibernateStudentUtil;
import controller.Login;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class LoginImpl implements Login {

    SessionFactory sessionFactory= HibernateStudentUtil.getSessionFactory();
    Session session= sessionFactory.openSession();

    @Override
    public Student getStudentById(String id) {
        Transaction transaction=session.beginTransaction();
        Student student=session.find(Student.class,id);
        transaction.commit();
        return student;
    }
}
