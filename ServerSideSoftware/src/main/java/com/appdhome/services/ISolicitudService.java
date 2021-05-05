package com.appdhome.services;

import com.appdhome.services.CrudService;
import com.appdhome.entities.Solicitud;

import java.util.List;

public interface ISolicitudService extends CrudService<Solicitud>{
    public List<Solicitud> findByIdTipo(Long idTipo) throws Exception;
  /*  public List<Solicitud> findByLastName(String lastname) throws Exception;
    public List<Solicitud> findByFirstNameAndLastName(String firstname, String lastname) throws Exception;
    public Solicitud findByDni(String dni) throws Exception;*/
}
