package com.example.demo;

import com.example.demo.service.ContactService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class AppConfig {

    @Bean
    public ContactService contactService() {
        return new ContactService();
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public ConsoleContactsApp consoleContactsApp(ContactService contactService, Scanner scanner) {
        return new ConsoleContactsApp(contactService, scanner);
    }


}