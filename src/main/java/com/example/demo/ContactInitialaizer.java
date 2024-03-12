package com.example.demo;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Profile("initFromFile")

@Component
public class ContactInitialaizer {
//java -jar app.jar --spring.profiles.active=initFromFile
    @Value("${contacts.initFromFile}")
    private final boolean initFromFile;
    @Value("${contacts.fileLocation}")
    private final String contactsFileLocation;
    @Autowired
    private final ResourceLoader resourceLoader;

    public ContactInitialaizer(@Value("${contacts.initFromFile}") boolean initFromFile,
                               @Value("${contacts.fileLocation}") String contactsFileLocation,
                               ResourceLoader resourceLoader) {
        this.initFromFile = initFromFile;
        this.contactsFileLocation = contactsFileLocation;
        this.resourceLoader = resourceLoader;
    }

    public List<Contact> initContactsFromFile() {

        Path path = Path.of("C:\\Users\\olya-\\IdeaProjects\\ContactRegistry\\src\\main\\resources");

        List<Contact> contacts = new ArrayList<>();
        if (initFromFile) {

            // Чтение контактов из файла по указанному пути
            try (InputStream inputStream = new FileInputStream(String.valueOf(path.resolve(contactsFileLocation)))) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 3) {
                        String name = parts[0];
                        String phoneNumber = parts[1];
                        String email = parts[2];
                        Contact contact = new Contact(name, phoneNumber, email);
                        contacts.add(contact);
                    } else {
                        System.err.println("Некорректный формат строки в файле: " + line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    return contacts;
    }
}
