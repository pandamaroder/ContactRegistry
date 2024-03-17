package com.example.demo;

import com.example.demo.model.Contact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

 class ContactInitialaizerTest {

     @Test
     @DisplayName("Verify contacts values")
       void testInitContactsFromFile() {
        ContactInitialaizer initializer = new ContactInitialaizer();
        List<Contact> contacts = initializer.initContactsFromFile();
        assertThat(contacts)
                .isNotEmpty()
                .hasSize(2);
               // .containsExactly(new Contact("","",""));

       }

        @Test
        @DisplayName("Verify contacts values2")
       void testInitContactsFromTestFile()  {
                // Создание временного файла с данными
                ContactInitialaizer initializer = new ContactInitialaizer();
                List<Contact> contacts = initializer.initContactsFromFile("contactsTest.txt");
                assertThat(contacts).hasSize(1);
                //++ порядок
                //assertThat(contacts).containsExactly(new Contact("","",""));
        }
}
