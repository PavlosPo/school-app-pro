package gr.aueb.cf.schoolappPro.dto;

public class UserUpdateDTO extends BaseDTO{
    private String username;
    private String password;

    public UserUpdateDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
