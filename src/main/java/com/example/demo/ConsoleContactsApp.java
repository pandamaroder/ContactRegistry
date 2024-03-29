package com.example.demo;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component("main")
@Scope("prototype") // каждый раз новый экземпляр //Singletone
// делаем бин осведомленным - знают про спринг
public class ConsoleContactsApp {

    private final ContactInitialaizer contactInitialaizer;
    private final ContactService contactService;
    private final Scanner scanner;

    // Доп функционал
    @Value("#{contactsNames.![fullName  +  phoneNumber]}")
    public void setContactsNames(List<String> contactsNames) {
        this.contactsNames = contactsNames;
    }

    public List<String> getContactsNames() {
        return contactsNames;
    }

    private List<String> contactsNames;


    public ConsoleContactsApp(ContactInitialaizer contactInitialaizer,
                              ContactService contactService,
                              Scanner scanner) {
        this.contactInitialaizer = contactInitialaizer;
        this.contactService = contactService;
        this.scanner = scanner;
    }

    @SuppressWarnings("PMD.SystemPrintln")
    public void start() throws IOException {
        contactInitialaizer.initContactsFromFile().forEach(contactService::addContact);
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Просмотреть контакты");
            System.out.println("2. Добавить новый контакт");
            System.out.println("3. Удалить контакт по email");
            System.out.println("4. Сохранить контакты в файл");
            System.out.println("5. Новый функционал");
            System.out.println("6. Выйти");

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
                    contactService.saveContactsToFile();
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
        for (Contact contact : contactService.getAllContacts()) {
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


}
