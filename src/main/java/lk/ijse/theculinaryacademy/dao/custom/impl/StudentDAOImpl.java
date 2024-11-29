package lk.ijse.theculinaryacademy.dao.custom.impl;

import lk.ijse.theculinaryacademy.config.SessionFactoryConfig;
import lk.ijse.theculinaryacademy.dao.custom.StudentDAO;
import lk.ijse.theculinaryacademy.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> getAll() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        String jpql = "FROM Student";
        Query<Student> query = session.createQuery(jpql, Student.class);
        return query.list();
    }

    @Override
    public boolean add(Student entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            try {
                session.update(entity);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                throw new Exception("Failed to update student", e);
            }

    }


    @Override
    public Student exist(String id) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        String jpql = "FROM Student s WHERE s.studentId = :id";
        Query<Student> query = session.createQuery(jpql, Student.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.uniqueResult();
    }

    @Override
    public boolean delete(Long id) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, id);
        if (student != null) {
            session.delete(student);
            transaction.commit();
            return true;
        } else {
            transaction.rollback();
            return false;
        }
    }
}
