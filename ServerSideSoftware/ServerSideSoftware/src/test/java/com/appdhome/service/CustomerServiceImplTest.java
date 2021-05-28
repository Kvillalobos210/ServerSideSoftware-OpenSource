package com.appdhome.service;

import com.appdhome.entities.Customer;
import com.appdhome.repository.ICustomerRepository;
import com.appdhome.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private ICustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void saveTest() {
        Customer customer = new Customer(1L, "Julio", "Caldas", "54625548", "julio.caldas@upc.edu.pe", "987654321",
                1L,1L,1L);

        given(customerRepository.save(customer)).willReturn(customer);

        Customer savedCustomer = null;
        try {
            savedCustomer = customerRepository.save(customer);
        } catch (Exception e) {
        }

        assertThat(savedCustomer).isNotNull();
        verify(customerRepository).save(any(Customer.class));
    }
    @Test
    void findByIdTest() throws Exception{
        Long id = 1L;
        Customer customer = new Customer(1L, "Julio", "Caldas", "54625548", "julio.caldas@upc.edu.pe", "987654321",
                1L,1L,1L);

        given(customerRepository.findById(id)).willReturn(Optional.of(customer));

        Optional<Customer> expected = null;
        expected = customerService.getById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception {
        List<Customer> list = new ArrayList<>();
        list.add(new Customer(1L, "Julio", "Caldas", "54625548", "julio.caldas@upc.edu.pe", "987654321",
                1L,1L,1L));
        list.add(new Customer(1L, "Julio", "Caldas", "54625548", "julio.caldas@upc.edu.pe", "987654321",
                1L,1L,1L));
        list.add(new Customer(1L, "Julio", "Caldas", "54625548", "julio.caldas@upc.edu.pe", "987654321",
                1L,1L,1L));
        list.add(new Customer(1L, "Julio", "Caldas", "54625548", "julio.caldas@upc.edu.pe", "987654321",
                1L,1L,1L));
        given(customerRepository.findAll()).willReturn(list);
        List<Customer> expected = customerService.getAll();
        assertEquals(expected, list);
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;
        customerService.delete(id);
        verify(customerRepository, times(1)).deleteById(id);
    }
}
