package com.example.demo;

import com.example.demo.model.Contact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ContactInitialaizerTest {
    Logger mockLogger = mock(Logger.class);


    @Test
    @DisplayName("Verify contacts values size list of contacts from default file")
    void testInitContactsFromFile() {
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        List<Contact> contacts = initializer.initContactsFromFile();
        assertThat(contacts)
                .isNotEmpty()
                .hasSize(2);


    }

    @Test
    @DisplayName("Verify contacts values from default file")
    void testInitContactsFromTestFile() {
        // Создание временного файла с данными
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        List<Contact> contacts = initializer.initContactsFromFile();

        assertThat(contacts)
                .isNotEmpty()
                .hasSize(2)
                .containsExactly(new Contact("Иванов Иван Иваны", "+79255561887",
                        "someEmail@example.example"), new Contact("Иванов Иван Иваны2",
                        "+79255561882", "someEmail22@example.example"));

    }


    @Test
    @DisplayName("Verify contacts values size from test file")
    void testInitContactsFromTestFile3() {
        // Создание временного файла с данными
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        List<Contact> contacts = initializer.initContactsFromFile("contactsTest.txt");
        assertThat(contacts)
                .hasSize(1)
                .containsExactly(new Contact("Иванов Иван ИваныTest3",
                "+79255561887", "someEmail@example.example"));
    }


    @Test
    @DisplayName("Verify file format")
    void testIncorrectFileFormat() {
        // Создание временного файла с данными
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        assertThatThrownBy(() -> initializer
                .initContactsFromFile("contactsTest2.jpeg"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Неподдерживаемый формат файла: ");
    }


    @Test
    @DisplayName("Verify email format")
    void testInitContactsFromFile_InvalidEmail() throws IOException {
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        assertDoesNotThrow(() -> initializer.initContactsFromFile("contactsTestIncorrectEmail.txt"));

        verify(mockLogger).error("Некорректный формат email в файле {}: {}. Последний добавленный контакт {}",
                "contactsTestIncorrectEmail.txt", "someEmail", null);
    }


    @Test
    @DisplayName("Verify Name format")
    void testInitContactsFromFile_InvalidName() throws IOException {
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        assertDoesNotThrow(() -> initializer.initContactsFromFile("contactsTestIncorrectName.txt"));

        verify(mockLogger).error("Некорректный формат имени в файле {}: {}. Последний добавленный контакт {}",
                        "contactsTestIncorrectName.txt", "+79255561887", null);
    }

    @Test
    @DisplayName("Verify Number format")
    void testInitContactsFromFile_InvalidNumber() throws IOException {
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        assertDoesNotThrow(() -> initializer.initContactsFromFile("contactsTestIncorrectNumber.txt"));

        verify(mockLogger).error("Некорректный формат номера телефона в файле {}: {}. Последний добавленный контакт {}",
                "contactsTestIncorrectNumber.txt", "someEmail@pisem.net", null);
    }


    @Test
    @DisplayName("Verify correct Contact format")
    void testInitContactsFromFile_notFullString() throws IOException {
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        assertDoesNotThrow(() -> initializer.initContactsFromFile("contactsTestUnfullStringr.txt"));

        verify(mockLogger).error("Некорректный формат строки в файле либо некорректный формат файла: {}",
                "Иванов Иван ИваныTest3;+792222222");
    }

    @Test
    @DisplayName("Verify correctly added last contact")
    void testInitContactsFromFile_lastAddedContact() throws IOException {
        ContactInitialaizer initializer = new ContactInitialaizer(mockLogger);
        assertDoesNotThrow(() -> initializer.initContactsFromFile("contactsTestlLastAddedContact.txt"));

        assertThat(initializer.lastContact.getFullName())
                .isEqualTo("Иванов Иван Иваны2");

        assertThat(initializer.lastContact.getEmail())
                .isEqualTo("someEmail22@example.example");

        assertThat(initializer.lastContact.getPhoneNumber())
                .isEqualTo("+79255561882");
    }

}
