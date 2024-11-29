package lk.ijse.theculinaryacademy.dao.custom.impl;

import lk.ijse.theculinaryacademy.config.SessionFactoryConfig;
import lk.ijse.theculinaryacademy.dao.custom.Student_CourseDAO;
import lk.ijse.theculinaryacademy.entity.Student_Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class Student_CourseDAOImpl implements Student_CourseDAO {
    @Override
    public List<Student_Course> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean add(Student_Course entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student_Course entity) throws Exception {
        return false;
    }

    @Override
    public Student_Course exist(String id) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }


}
