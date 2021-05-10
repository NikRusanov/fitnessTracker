package com.rusanov.fitnessApp;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class Login {
    
    private final String path;

    private UserSerializer serializer   ;
    public Login(String path) throws JAXBException {
        this.path = path;
        serializer = new UserSerializer(path);
    }
    
    
    public User register(User user) throws JAXBException, IOException {

        Users users;
        if(Files.exists(Path.of(path))) {
            users = serializer.xmlToUsers();
            for(var savedUser : users.getUsers()) {
                if(savedUser.equals(user)) {
                    user.setLastSession(savedUser.getLastSession());
                    user.setCalories(savedUser.getCalories());
                }
            }
        } else {
            users = new Users();
        }
        if(!users.getUsers().contains(user)) {
            users.addUser(user);
        }  else {
            System.out.println("Hello  " + user.getLogin());
        }
        serializer.usersToXml(users);
        return user;
    }

    public void startTraining(User user) throws JAXBException, IOException {
        User currentUser  = register(user);

        Users users = serializer.xmlToUsers();
        Set<User> usersSet =  users.getUsers();
        System.out.println(currentUser.toString());

        Training training = new Training();
        training.start();

        System.out.println("Press any key to stop training.");
        System.in.read();
        training.stop();

        Double currentSessionCalories = training.getWastedCalories();
        users.update(user, currentSessionCalories);
        serializer.usersToXml(users);

    }

}
