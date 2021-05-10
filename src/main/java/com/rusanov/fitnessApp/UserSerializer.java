package com.rusanov.fitnessApp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class UserSerializer {

    private final JAXBContext context;

    private final String propertiesFile;

    public UserSerializer(String propertiesFile) throws JAXBException {
        this.context = JAXBContext.newInstance(Users.class);
        this.propertiesFile = propertiesFile;
    }

    public void usersToXml(Users users) throws JAXBException, IOException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(users, new FileOutputStream(propertiesFile.toString()) {
        });
    }

    public Users xmlToUsers() throws JAXBException, IOException {
         Unmarshaller unmarshaller = context.createUnmarshaller();
         return (Users) unmarshaller.unmarshal(new File(propertiesFile));
    }
}
