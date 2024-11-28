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
        SIGNUP,SIGNIN,STUDENT, COURSE, ADMIN, REGISTRATION
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case SIGNUP:
                return new SignupBOImpl();
            case SIGNIN:
                return new SinginBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case ADMIN:
                return new AdminBOImpl();
            case COURSE:
                return new CourseBOImpl();
            case REGISTRATION:
                return new RegisterBOImpl();
            default:
                return null;
        }
    }

}
