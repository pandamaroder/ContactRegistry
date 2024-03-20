package com.example.demo;

import com.example.demo.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.slf4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ContactInitialaizerTest {
    Logger mockLogger = mock(Logger.class);

    @Test
    void testInitContactsFromFile_InvalidEmail() throws IOException {
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
         assertDoesNotThrow(() -> initializer.initContactsFromFile("contactsTestIncorrectOrder.txt"));

        // Проверка вызова метода error у макета объекта Logger при обнаружении некорректного email
        verify(mockLogger).error("Некорректный формат email в файле {}: {}. Последний добавленный контакт {}", "contactsTestIncorrectOrder.txt", "someEmail", null);

    }
    @Test
    @DisplayName("Verify contacts values size list of contacts from default file")
    void testInitContactsFromFile() {
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        List<Contact> contacts = initializer.initContactsFromFile();
        assertThat(contacts).isNotEmpty().hasSize(2);
        // .containsExactly(new Contact("","",""));

    }

    @Test
    @DisplayName("Verify contacts values from default file")
    void testInitContactsFromTestFile() {
        // Создание временного файла с данными
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        List<Contact> contacts = initializer.initContactsFromFile();

        assertThat(contacts).isNotEmpty().hasSize(2).containsExactly(new Contact("Иванов Иван Иваны", "+79255561887", "someEmail@example.example"), new Contact("Иванов Иван Иваны2", "+79255561882", "someEmail22@example.example"));

    }

    @Test
    @DisplayName("Verify contacts list size from test file")
    void testInitContactsFromTestFile2() {
        // Создание временного файла с данными
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        List<Contact> contacts = initializer.initContactsFromFile("contactsTest.txt");
        assertThat(contacts).hasSize(1);

    }

    @Test
    @DisplayName("Verify contacts values size from test file")
    void testInitContactsFromTestFile3() {
        // Создание временного файла с данными
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        List<Contact> contacts = initializer.initContactsFromFile("contactsTest.txt");
        assertThat(contacts).hasSize(1).containsExactly(new Contact("Иванов Иван ИваныTest3", "+79255561887", "someEmail@example.example"));
    }


    @Test
    @DisplayName("Verify file format")
    void testIncorrectFileFormat() {
        // Создание временного файла с данными
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        assertThatThrownBy(() -> initializer
                .initContactsFromFile("contactsTest2.jpeg"))
                .isInstanceOf(IllegalArgumentException.class);
    }




}
