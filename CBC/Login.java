package CBC;

public class Login {

    /**
     * Login function, hardcoded for educational purposes
     * @param userName string supplying userName for login
     * @param password string supplying password for login
     * @return boolean true - navigate to login, false = show error message
     */
    public static Boolean login(String userName, String password){
        if(userName == "Patrick" && password == "pizza"){
            return true;
        }
        return false;
    }


}
