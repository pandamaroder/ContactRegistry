package com.example.demo;

import com.example.demo.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ContactInitialaizerTest {

     @Test
     @DisplayName("Verify contacts values size list of contacts from default file")
       void testInitContactsFromFile() {
        ContactInitialaizer initializer = new ContactInitialaizer();
        List<Contact> contacts = initializer.initContactsFromFile();
        assertThat(contacts)
                .isNotEmpty()
                .hasSize(2);
               // .containsExactly(new Contact("","",""));

       }

        @Test
        @DisplayName("Verify contacts values from default file")
       void testInitContactsFromTestFile()  {
                // Создание временного файла с данными
                ContactInitialaizer initializer = new ContactInitialaizer();
            List<Contact> contacts = initializer.initContactsFromFile();

            assertThat(contacts)
                    .isNotEmpty()
                    .hasSize(2)
             .containsExactly(new Contact("Иванов Иван Иваны","+79255561887","someEmail@example.example"),
                     new Contact("Иванов Иван Иваны2","+79255561882","someEmail22@example.example"));

        }

     @Test
     @DisplayName("Verify contacts list size from test file")
     void testInitContactsFromTestFile2()  {
         // Создание временного файла с данными
         ContactInitialaizer initializer = new ContactInitialaizer();
         List<Contact> contacts = initializer.initContactsFromFile("contactsTest.txt");
         assertThat(contacts).hasSize(1);

     }

     @Test
     @DisplayName("Verify contacts values size from test file")
     void testInitContactsFromTestFile3()  {
         // Создание временного файла с данными
         ContactInitialaizer initializer = new ContactInitialaizer();
         List<Contact> contacts = initializer.initContactsFromFile("contactsTest.txt");
         assertThat(contacts)
                 .hasSize(1)
                 .containsExactly(new Contact("Иванов Иван ИваныTest3","+79255561887","someEmail@example.example"));
     }


     @Test
     @DisplayName("Verify file format")
     void testIncorrectFileFormat()  {
         // Создание временного файла с данными
         ContactInitialaizer initializer = new ContactInitialaizer();
         List<Contact> contacts = initializer.initContactsFromFile("contactsTest.jpeg");

         assertThatThrownBy(() -> initializer.initContactsFromFile("incorrect_contacts.jpeg"))
                 .isInstanceOf(IOException.class);}

    @Test
    @DisplayName("Incorrect email")
    void testIncorrectEmailFormat() {
        LogWatcher logWatcher = new LogWatcher();
        ContactInitialaizer initializer = new ContactInitialaizer();
        List<Contact> contacts = initializer.initContactsFromFile("contactsTestIncorrectOrder.txt");

        // Проверка на создание warning
        List<String> logs = logWatcher.getWarnings();
        assertThat(logs)
                .isNotEmpty()
                .anyMatch(log -> log.contains("Некорректный формат email в файле"));
    }
}
