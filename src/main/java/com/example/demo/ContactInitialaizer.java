package com.example.demo;

import com.example.demo.model.Contact;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Profile("initFromFile")
@Component
public class ContactInitialaizer {


    public ContactInitialaizer() {
        System.out.println("ContactInit bean created");
    }

    public List<Contact> initContactsFromFile() {

        // Path path = Path.of("C:\\Users\\olya-\\IdeaProjects\\ContactRegistry\\src\\main\\resources");
        //InputStream resourceAsStream = ContactInitialaizer.class.getClassLoader().getResourceAsStream("contacts.txt");
        List<Contact> contacts = new ArrayList<>();
        // Чтение контактов из файла по указанному пути
        try (InputStream inputStream = ContactInitialaizer.class.getClassLoader().getResourceAsStream("contacts.txt")
        ) {
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
        return contacts;
    }
}
