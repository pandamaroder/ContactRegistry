package com.example.demo.service;

import com.example.demo.model.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;


@Service
public class ContactService {

    @Value("${contacts.save-path}")
    private String savePath;

    private final Map<String, Contact> contacts = new HashMap<>();

    // Добавление нового контакта
    public void addContact(Contact contact) {
        contacts.put(contact.getEmail(), contact);
    }

    // Удаление контакта по email
    public void deleteContactByEmail(String email) {
        contacts.remove(email);
    }

    // Получение всех контактов
    public List<Contact> getAllContacts() {
        //Collections.unmodifiableMap(new HashMap<>());
        return List.copyOf(contacts.values());
    }

    // Сохранение контактов в файл
    public void saveContactsToFile() {
       // String userDirectory = new File("").getAbsolutePath();
       // Path path = Paths.get("")
       //         .toAbsolutePath();
        //поразбираться с NIO
       // Path pathMain = Paths.get("src/main/resources").;
        try (Writer writer = Files.newBufferedWriter(Paths.get(savePath), UTF_8)) {
            for (Contact contact : contacts.values()) {
                writer.write(contact.toString() + "\n");
            }
        } catch (IOException e) {

        }
    }
}
