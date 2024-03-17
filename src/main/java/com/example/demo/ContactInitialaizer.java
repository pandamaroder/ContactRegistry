package com.example.demo;

import com.example.demo.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Profile("initFromFile")
@Component
public class ContactInitialaizer {


    public static final Logger LOGGER = LoggerFactory.getLogger(ContactInitialaizer.class);
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

        /*    [WARNING] /C:/Users/olya-/IdeaProjects/ContactRegistry/src/main/java/com/example/demo/ContactInitialaizer.java:[37,56] [DefaultCharset] Implicit use of the platform default charset, which can result in differing behaviour between JVM executions or incorrect behavior if the encoding of the data source doesn't match expectations.
                    /C:/Users/olya-/IdeaProjects/ContactRegistry/src/main/java/com/example/demo/ContactInitialaizer.java:[37,56] [DefaultCharset] Implicit use of the platform default charset, which can result in differing behaviour between JVM executions or incorrect behavior if the encoding of the data source doesn't match expectations.
        */
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8));
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
                    LOGGER.error("Некорректный формат строки в файле: {}", line);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Ошибка при чтении файла contacts.txt", e);
        }
        return contacts;
    }
}
