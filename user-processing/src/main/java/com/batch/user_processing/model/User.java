package com.batch.user_processing.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;


@Data
@Entity
@Table(name = "user_info")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String mobileNumber;
    private Integer age;
    private String city;
}
