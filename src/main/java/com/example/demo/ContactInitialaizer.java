package com.example.demo;

import com.example.demo.model.Contact;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

    public List<Contact> initContactsFromFile(String fileName) {
        List<Contact> contacts = new ArrayList<>();

        // Чтение контактов из файла по указанному пути
        try (InputStream inputStream = ContactInitialaizer.class.getClassLoader().getResourceAsStream(fileName)
        ) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {

                    String name = parts[0];
                    if (!name.matches("[a-zA-Z\\s]+")) {
                        LOGGER.warn("Некорректный формат имени в файле {}: {}", fileName, name);
                    }
                    String phoneNumber = parts[1];
                    if (!phoneNumber.matches("[^a-zA-Z]+")) {
                        LOGGER.warn("Некорректный формат номера телефона в файле {}: {}", fileName, phoneNumber);
                    }
                    String email = parts[2];
                    if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                        LOGGER.warn("Некорректный формат email в файле {}: {}", fileName, email);
                    }
                    Contact contact = new Contact(name, phoneNumber, email);
                    contacts.add(contact);
                } else {
                    LOGGER.error("Некорректный формат строки в файле либо некорректный формат файла: {}", line);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Ошибка при чтении файла contacts.txt", e);
        }
        return contacts;
    }

    public List<Contact> initContactsFromFile() {
        return  initContactsFromFile("contacts.txt");
    }
}
