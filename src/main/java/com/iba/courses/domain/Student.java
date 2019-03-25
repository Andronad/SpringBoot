package com.iba.courses.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="Student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="login")
    @NotNull
    private String login;

    @Column(name="password")
    @NotNull
    private String password;

    @Column(name="university")
    @NotNull
    private String university;

    @Column(name="course")
    @NotNull
    private Integer course;

    public Student(@NotNull String name, @NotNull String login, @NotNull String password, @NotNull String university, @NotNull Integer course) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.university = university;
        this.course = course;
    }
}
