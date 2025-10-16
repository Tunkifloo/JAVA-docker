package com.springback.springemployees.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String position;

    @Column(length = 50)
    private String department;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(precision = 10, scale = 2)
    private Double salary;

    @Column(name = "is_active")
    private Boolean isActive = true;
}