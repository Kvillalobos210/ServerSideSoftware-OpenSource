package com.appdhome.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Customer.findByFirstName", query = "select c from Customer c where c.firstName = ?1")

public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstName;
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastName;
    @Column(name = "dni", nullable = false, length = 8)
    private String dni;
    @Column(name = "email", nullable = false, length = 60)
    private String email;
    @Column(name = "cellphone", nullable = false, length = 9)
    private String cellphone;
    @Column(name="idAccount", nullable = false)
    private Long idAccount;
    @Column(name="idCity", nullable = false)
    private Long idCity;
    @Column(name="idDistrict", nullable = false)
    private Long idDistrict;
}