package lk.ijse.theculinaryacademy.bo;


import lk.ijse.theculinaryacademy.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        ADMIN,COURSE,HOME,LOGIN,REGISTRATION,STUDENT
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case ADMIN:
                return new AdminBOImpl();
            case COURSE:
                return new CourseBOImpl();
            case HOME:
                return new HomeBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case REGISTRATION:
                return new PlacePaymentBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            default:
                return null;
        }
    }

}
