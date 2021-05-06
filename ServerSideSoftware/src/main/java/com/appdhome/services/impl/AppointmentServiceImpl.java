package com.appdhome.services.impl;

import com.appdhome.entities.Appointment;
import com.appdhome.repository.IAppointmentRepository;
import com.appdhome.services.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private IAppointmentRepository appointmentRepository;
    @Override
    @Transactional
    public Appointment save(Appointment appointment) throws Exception {
        return appointmentRepository.save(appointment);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        appointmentRepository.deleteById(id);
    }


    @Override
    public List<Appointment> getAll() throws Exception {
        return null;
    }

    @Override
    public Optional<Appointment> getById(Long id) throws Exception {
        return Optional.empty();
    }

    @Override
    public List<Appointment> findByStatus(String status) throws Exception {
        return null;
    }

    @Override
    public List<Appointment> findByIdCustomer(Long id) throws Exception {
        return appointmentRepository.findAppointmentByIdCustomer(id);
    }
}
