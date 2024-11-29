package lk.ijse.theculinaryacademy.dao.custom.impl;

import lk.ijse.theculinaryacademy.config.SessionFactoryConfig;
import lk.ijse.theculinaryacademy.dao.custom.UserDAO;
import lk.ijse.theculinaryacademy.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class UserDAOImpl  implements UserDAO {
    @Override
    public List<User> getAll() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        String jpql = "FROM User";
        Query<User> query = session.createQuery(jpql, User.class);
        return query.list();
    }

    @Override
    public boolean add(User entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new Exception("Failed to update User", e);
        }
    }

    @Override
    public User exist(String id) throws Exception {
       Session session = SessionFactoryConfig.getInstance().getSession();
            String jpql = "FROM User u WHERE u.id = :id";
            Query<User> query = session.createQuery(jpql, User.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            return query.uniqueResult();
    }


    @Override
    public boolean delete(Long id) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
            transaction.commit();
            return true;
        } else {
            transaction.rollback();
            return false;
        }
    }
}

