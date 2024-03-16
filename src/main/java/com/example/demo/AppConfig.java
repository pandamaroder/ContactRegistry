package com.example.demo;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Configuration
@ComponentScan("com.example.demo")
@PropertySource("application.properties")
public class AppConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }


    @Bean("contactsNames")
    public List<Contact> contacts() {


       List<Contact> contacts = new ArrayList<>();
       contacts.add(new Contact("Bob Bob", "454545", "dffdfdfdf"));

       return contacts;
   }

}