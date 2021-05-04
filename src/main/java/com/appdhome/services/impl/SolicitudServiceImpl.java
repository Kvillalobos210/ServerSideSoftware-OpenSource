package com.appdhome.services.impl;

import com.appdhome.entities.Customer;
import com.appdhome.entities.Solicitud;
import com.appdhome.repository.ICustomerRepository;
import com.appdhome.repository.ISolicitudRepository;
import com.appdhome.services.ICustomerService;
import com.appdhome.services.ISolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SolicitudServiceImpl implements ISolicitudService {
    @Autowired
    private ISolicitudRepository solicitudRepository;

    @Override
    @Transactional
    public Solicitud save(Solicitud solicitud) throws Exception{
        return solicitudRepository.save(solicitud);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception{
        solicitudRepository.deleteById(id);
    }

    @Override
    public List<Solicitud> getAll() throws Exception{
        return solicitudRepository.findAll();
    }

    @Override
    public Optional<Solicitud> getById(Long id) throws Exception {
        return solicitudRepository.findById(id);
    }

    @Override
    public List<Solicitud> findByIdTipo(Long idTipo) throws Exception {
        return solicitudRepository.findByIdTipo(idTipo);
    }
}
