package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class Contact {
    private String fullName;
    private String phoneNumber;
    private String email;

}
