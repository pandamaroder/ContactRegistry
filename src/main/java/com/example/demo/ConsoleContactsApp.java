package com.example.demo;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Scanner;


public class ConsoleContactsApp {
    @Autowired
    private ContactInitialaizer contactInitialaizer;
    private final ContactService contactService;
    private final Scanner scanner;

    public ConsoleContactsApp(ContactService contactService, Scanner scanner) {
        this.contactService = contactService;
        this.scanner = scanner;
    }



    @PostConstruct
    public void start() {
        contactService.setContacts(contactInitialaizer.initContactsFromFile());
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Просмотреть контакты");
            System.out.println("2. Добавить новый контакт");
            System.out.println("3. Удалить контакт по email");
            System.out.println("4. Сохранить контакты в файл");
            System.out.println("5. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1:
                    viewContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    deleteContact();
                    break;
                case 4:
                    saveContactsToFile();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void viewContacts() {
        System.out.println("Список контактов:");
        for (Contact contact : contactService.getAllContacts().values()) {
            System.out.println(contact);
        }
    }

    private void addContact() {
        System.out.println("Введите полное имя:");
        String name = scanner.nextLine();
        System.out.println("Введите номер телефона:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Введите адрес электронной почты:");
        String email = scanner.nextLine();

        Contact contact = new Contact(name, phoneNumber, email);
        contactService.addContact(contact);
        System.out.println("Контакт успешно добавлен.");
    }

    private void deleteContact() {
        System.out.println("Введите адрес электронной почты контакта для удаления:");
        String email = scanner.nextLine();
        contactService.deleteContactByEmail(email);
        System.out.println("Контакт с email " + email + " успешно удален.");
    }

    private void saveContactsToFile() {
        System.out.println("Введите имя файла для сохранения контактов:");
        String fileName = scanner.nextLine();
        contactService.saveContactsToFile(fileName);
        System.out.println("Контакты успешно сохранены в файл " + fileName);
    }
}
