package lk.ijse.theculinaryacademy.dao.custom.impl;


import com.example.hibernatelayered.config.FactoryConfiguration;
import com.example.hibernatelayered.dao.custom.CustomerDAO;
import com.example.hibernatelayered.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {


    @Override
    public List<Customer> getAll()  throws Exception  {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Customer> list = session.createNativeQuery("SELECT * FROM Customer", Customer.class).list();
        transaction.commit();
        session.close();
        return list;
    }
    //delete dbconnection
    //delete SQLutil
    //clear method body in DAOIMPL
    //add xml configuration file
    //write DOAImpl method body
        //open session
        //open transaction
        //save()/update();
        //commit
        //close the session
    @Override
    public boolean exist(String id) throws Exception  {
        return false;
    }

    @Override
    public boolean delete(String id)  throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("delete from Customer where id='"+id+"'",Customer.class).executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Customer entity)  throws Exception  {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean add(Customer entity)  throws Exception  {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }
}
