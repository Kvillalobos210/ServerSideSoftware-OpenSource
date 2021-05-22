package com.appdhome.controller;

import com.appdhome.entities.Employee;
import com.appdhome.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebMvcTest(controllers=EmployeeController.class)
@ActiveProfiles("test")
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeServiceImpl employeeService;

    private List<Employee> employeeList;
/*
    // carga de data
    @BeforeEach
    void setUp(){
        employeeList=new ArrayList<>();
        employeeList.add(new Employee(1L,"Gianella","Coronel","72857985",973973100,"giane@gmail.com",1L,1L,1L,1L));
        employeeList.add(new Employee(1L,"Gianella","Coronel","72857985",973973100,"giane@gmail.com",1L,1L,1L,1L));
        employeeList.add(new Employee(1L,"Gianella","Coronel","72857985",973973100,"giane@gmail.com",1L,1L,1L,1L));
        employeeList.add(new Employee(1L,"Gianella","Coronel","72857985",973973100,"giane@gmail.com",1L,1L,1L,1L));
    }
    @Test
    void findAllEmployees() throws Exception {
        given(employeeService.getAll()).willReturn(employeeList);
        mockMvc.perform(get("/api/employees")).andExpect(status().isOk());
    }

    @Test
    void findEmployeeById() throws Exception{
        Long EmployeeId=1L;
        Employee employee= new Employee(1L,"Gianella","Coronel","72857985",973973100,"giane@gmail.com",1L,1L,1L,1L);

        given(employeeService.getById(EmployeeId)).willReturn(Optional.of(employee));
        mockMvc.perform(get("/api/employees/{id}",employee.getId())).andExpect(status().isOk());
    }

*/
}
