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
    private final Logger logger;


    public ContactInitialaizer(Logger logger) {
        this.logger = logger;
    }



    public List<Contact> initContactsFromFile(String fileName) {
        List<Contact> contacts = new ArrayList<>();

         if (!fileName.endsWith(".txt")) {
            throw new IllegalArgumentException("Неподдерживаемый формат файла: " + fileName);
        }
        Contact lastContact = null;
        try (InputStream inputStream = ContactInitialaizer.class.getClassLoader().getResourceAsStream(fileName)
        ) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {

                    String name = parts[0];
                    if (!name.matches("[a-zA-Z\\s]+")) {
                        logger.error("Некорректный формат имени в файле {}: {}. Последний добавленный контакт {}", fileName, name,  lastContact);
                    }
                    String phoneNumber = parts[1];
                    if (!phoneNumber.matches("[^a-zA-Z]+")) {
                        logger.error("Некорректный формат номера телефона в файле {}: {}. Последний добавленный контакт {}", fileName, phoneNumber,  lastContact);
                    }
                    String email = parts[2];
                    if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                        logger.error("Некорректный формат email в файле {}: {}. Последний добавленный контакт {}", fileName, email, lastContact);
                    }
                    lastContact = new Contact(name, phoneNumber, email); // Обновляем последний добавленный контакт
                    contacts.add(lastContact);
                } else {
                    logger.error("Некорректный формат строки в файле либо некорректный формат файла: {}", line);
                }
            }
        } catch (IOException e) {
            logger.error("Ошибка при чтении файла", e);
        }
        return contacts;
    }

    public List<Contact> initContactsFromFile() {
        return  initContactsFromFile("contacts.txt");
    }
}
