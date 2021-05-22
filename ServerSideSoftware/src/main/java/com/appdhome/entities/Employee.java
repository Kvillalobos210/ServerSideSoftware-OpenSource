package com.appdhome.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Employee.findByFirstName",query="Select c from Customer c where c.firstName=?1")

public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstName;
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastName;
    @Column(name = "dni", nullable = false, length = 8)
    private String dni;
    @Column(name = "address", nullable = true, length = 150)
    private String address;
    @Column(name = "cellphone", nullable = false, length = 9)
    private int cellphone;
    @Column(name = "email", nullable = true, length = 50)
    private String email;
    @Column(name = "username", nullable = true, length = 50)
    private String username;
    @Column(name="password",nullable = false, length = 15)
    private String password;
    @Column(name="birthday",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

}
