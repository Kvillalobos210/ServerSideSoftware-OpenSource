package com.appdhome.services;

import com.appdhome.entities.Appointment;

import java.util.List;

public interface IAppointmentService extends CrudService<Appointment>{
    public List<Appointment> findByIdCustomer(Long idCustomer) throws Exception;
    public List<Appointment> findByStatus(String status) throws Exception;
    public List<Appointment> findByIdEmployee(Long idEmployee) throws Exception;
}
