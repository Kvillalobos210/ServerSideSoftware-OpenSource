package com.appdhome.repository;

import com.appdhome.entities.Appointment;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment,Long> {
    public List<Appointment> findAppointmentByIdEmployee(Long id);
    public List<Appointment> findAppointmentByIdCustomer(Long IdCustomer);
    public List<Appointment> findAppointmentByStatus(String status);
}
