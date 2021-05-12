package com.rusanov.fitnessApp;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    private String login;
    private Double calories = 0.0;
    private Double lastSession = 0.0;

    public User(){}
    public User(String login) {
        this.login = login;
    }



    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }


    public Double getLastSession() {
        return lastSession;
    }

    public void setLastSession(Double lastSession) {
        this.lastSession = lastSession;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }


    @Override
    public String toString() {
        return  "\nTotal calories burned:" + calories +
                "\n On last session:" + lastSession;
    }
}
