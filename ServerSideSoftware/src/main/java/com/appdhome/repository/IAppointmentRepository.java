package com.appdhome.repository;

import com.appdhome.entities.Appointment;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment,Long> {
    public List<Appointment> findAppointmentByIdEmployee(Long id);
    public List<Appointment> findAppointmentByIdCustomer(Long IdCustomer);
    public List<Appointment> findAppointmentByStatus(String status);
}
