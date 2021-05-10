package com.rusanov.fitnessApp;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Login login = null;
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
        try {
            login = new Login("src/main/resources/users.xml");
            System.out.println("Enter Login:");
            String loginName = reader.readLine();
            login.startTraining(new User(loginName));
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}
