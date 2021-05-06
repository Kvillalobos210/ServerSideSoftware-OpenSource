package com.appdhome.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Appointment.findByStatus", query = "select a from Appointment a where a.status = ?1")
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="idCustomer", nullable = false)
    private Long idCustomer;
    @Column(name="idEmployee", nullable = false)
    private Long idEmployee;
    @Column(name="status", nullable = false, length = 50)
    private String status;
    @Column(name="description", nullable = false, length = 250)
    private String description;
    @Column(name="AppointmentDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;
    @Column(name="address", nullable = false, length = 200)
    private String address;
    @Column(name="valorization", nullable = false, length = 200)
    private int valorization;
    @Column(name="paymentMethod", nullable = false, length = 200)
    private Long paymentMethod;
    @Column(name="TypeRequest", nullable = false, length = 200)
    private Long typeRequest;



}
