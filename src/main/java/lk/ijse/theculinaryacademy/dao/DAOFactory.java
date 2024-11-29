package lk.ijse.theculinaryacademy.dao;

import lk.ijse.theculinaryacademy.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENT, COURSE, USER, PAYMENT, STUDENT_COURSE
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case COURSE:
                return new CuorseDAOImpl();
            case USER:
                return new UserDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case STUDENT_COURSE:
                return new Student_CourseDAOImpl();
            default:
                return null;
        }
    }


}
