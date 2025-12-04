package controller.impl;

import config.HibernateStudentUtil;
import controller.NewUser;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class NewUserImpl implements NewUser {

    SessionFactory sessionFactory= HibernateStudentUtil.getSessionFactory();
    Session session= sessionFactory.openSession();

    @Override
    public void save(Student student) {
        Transaction transaction=session.beginTransaction();
        session.persist(student);
        transaction.commit();

    }

    @Override
    public String getLastId() {
        String hql = "SELECT u.id FROM Student u ORDER BY u.id DESC";
        return session.createQuery(hql, String.class)
                .setMaxResults(1)
                .uniqueResult();
    }
}
