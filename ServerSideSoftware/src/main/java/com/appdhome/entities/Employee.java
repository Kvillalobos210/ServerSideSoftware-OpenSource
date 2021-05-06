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
@NamedQuery(name="Employee.findByFirstName",query="Select e from Employee e where e.firstName=?1")

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
    @Column(name = "cellphone", nullable = false, length = 9)
    private int cellphone;
    @Column(name = "email", nullable = true, length = 50)
    private String email;
    @Column(name="idSpecialty", nullable = false)
    private Long idSpecialty;
    @Column(name="idAccount", nullable = false)
    private Long idAccount;
    @Column(name="idCity", nullable = false)
    private Long idCity;
    @Column(name="idDistrict", nullable = false)
    private Long idDistrict;





}
