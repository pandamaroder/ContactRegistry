package com.example.demo.service;

import com.example.demo.ContactInitialaizer;
import com.example.demo.model.Contact;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Data
public class ContactService {

    private Map<String, Contact> contacts = new HashMap<>();

    // Добавление нового контакта
    public void addContact(Contact contact) {
        contacts.put(contact.getEmail(), contact);
    }

    // Удаление контакта по email
    public void deleteContactByEmail(String email) {
        contacts.remove(email);
    }

    // Получение всех контактов
    public Map<String, Contact> getAllContacts() {
        return contacts;
    }

    // Сохранение контактов в файл (пример)
    public void saveContactsToFile(String fileName) {
        // Здесь код для сохранения контактов в файл
    }
}
