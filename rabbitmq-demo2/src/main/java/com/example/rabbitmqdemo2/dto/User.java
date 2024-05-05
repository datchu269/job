package com.example.rabbitmqdemo2.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;
}
