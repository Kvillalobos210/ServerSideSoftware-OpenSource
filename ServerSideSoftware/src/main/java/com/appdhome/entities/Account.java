package com.appdhome.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Account")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Account implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "passwordK", nullable = false, length = 20)
    private String password;
    @Column(name = "usernameT", nullable = false, length = 60)
    private String username;
    @Column(name = "Verify", nullable = false, length = 8)
    private String dni;

}
