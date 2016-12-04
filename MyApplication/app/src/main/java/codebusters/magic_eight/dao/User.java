package codebusters.magic_eight.dao;

/**
 * Created by greg on 04/12/16.
 */

public class User {

    private String name;
    private String sign;

    public User() {
    }

    public User(String name, String sign) {
        this.name = name;
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
