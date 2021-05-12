package com.rusanov.fitnessApp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users implements Serializable {

    @XmlElement(name = "user")
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User newUser) {
        users.add(newUser);
    }

    public boolean isExist(User user) {
        return users.contains(user);
    }

    public void update(User user, Double currentSessionCalories) {
        Double calories = user.getCalories();
        users.remove(user);
        user.setCalories( user.getCalories() + currentSessionCalories);
        user.setLastSession(currentSessionCalories);

        users.add(user);
    }
}
