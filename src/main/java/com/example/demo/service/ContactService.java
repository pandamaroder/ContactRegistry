package com.example.demo.service;

import com.example.demo.ContactInitialaizer;
import com.example.demo.model.Contact;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ContactService {

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
        return List.of(contacts.values().stream().toList());
    }

    // Сохранение контактов в файл (пример)
    public void saveContactsToFile(String fileName) {
        // Здесь код для сохранения контактов в файл
    }
}
