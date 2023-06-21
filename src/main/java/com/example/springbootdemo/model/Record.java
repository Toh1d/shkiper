package com.example.springbootdemo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "person_count")
    private int personCount;

    @Column(name = "preferences")
    private String preferences;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "email")
    private String email;

    @Column(name = "budget")
    private String budget;

}
