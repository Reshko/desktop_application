package sample.connectToDb;

public class User {
    private String typeUser;
    private String loginUser;
    private String passwordUser;

    public User(String typeUser, String loginUser, String passwordUser) {
        this.typeUser = typeUser;
        this.loginUser = loginUser;
        this.passwordUser = passwordUser;
    }

    public User() {}

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }



}
