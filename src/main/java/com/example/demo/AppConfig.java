package com.example.demo;

import com.example.demo.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.nio.charset.StandardCharsets.UTF_8;


@Configuration
@ComponentScan("com.example.demo")
@PropertySource("application.properties")
public class AppConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in, UTF_8);
    }


    @Bean("contactsNames")
    public List<Contact> contacts() {
       List<Contact> contacts = new ArrayList<>();
       contacts.add(new Contact("Bob Bob", "454545", "dffdfdfdf"));
       return contacts;
   }

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(ContactInitialaizer.class);
    }

}