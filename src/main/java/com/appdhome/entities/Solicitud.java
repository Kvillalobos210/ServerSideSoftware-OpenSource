package com.appdhome.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="solicitud")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Solicitud.findById", query = "select s from Solicitud s where s.id = ?1")

public class Solicitud implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idTipo", nullable = false)
    private Long idTipo;
    @Column(name = "idEmployee", nullable = false)
    private Long idEmployee;
    @Column(name = "idCustomer", nullable = false)
    private Long idCustomer;
}
