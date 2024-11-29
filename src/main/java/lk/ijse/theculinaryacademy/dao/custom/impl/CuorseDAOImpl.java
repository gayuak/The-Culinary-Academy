package lk.ijse.theculinaryacademy.dao.custom.impl;

import lk.ijse.theculinaryacademy.config.SessionFactoryConfig;
import lk.ijse.theculinaryacademy.dao.custom.CourseDAO;
import lk.ijse.theculinaryacademy.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class CuorseDAOImpl implements CourseDAO {

    @Override
    public List<Course> getAll() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        String jpql = "FROM Course";
        Query<Course> query = session.createQuery(jpql, Course.class);
        return query.list();
    }

    @Override
    public boolean add(Course entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new Exception("Failed to add course", e);
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Course entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new Exception("Failed to update course", e);
        } finally {
            session.close();
        }
    }

    @Override
    public Course exist(String id) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        String jpql = "FROM Course c WHERE c.id = :id";
        Query<Course> query = session.createQuery(jpql, Course.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.uniqueResult();
    }

    @Override
    public boolean delete(Long id) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Course course = session.get(Course.class, id);
            if (course != null) {
                session.delete(course);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } finally {
            session.close();
        }
    }
}
