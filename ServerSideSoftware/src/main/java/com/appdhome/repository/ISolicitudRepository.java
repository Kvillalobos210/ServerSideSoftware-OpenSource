package com.appdhome.repository;


import com.appdhome.entities.Customer;
import com.appdhome.entities.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ISolicitudRepository extends JpaRepository<Solicitud,Long> {
    public List<Solicitud> findByIdTipo(Long idTipo);

}
