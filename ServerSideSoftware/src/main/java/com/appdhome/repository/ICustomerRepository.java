package com.appdhome.repository;

import com.appdhome.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    public List<Customer> findByFirstName(String firstname);
    public List<Customer> findByLastName(String lastname);
    public List<Customer> findByFirstNameAndLastName(String firstname, String lastname);
    public Customer findByDni(String dni);
}