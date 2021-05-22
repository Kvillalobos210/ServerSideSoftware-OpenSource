package com.appdhome.controller;

import com.appdhome.entities.Employee;
import com.appdhome.services.IEmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Listar Trabajadores",notes = "Método para listar todo los trabajadores")
    @ApiResponses({
            @ApiResponse(code=201,message = "Trabajadores encontrados"),
            @ApiResponse(code=404, message = "Trabajadores no encontrados")
    })

    public ResponseEntity<List<Employee>> findAll(){
        try{
            List<Employee> employees=employeeService.getAll();
            System.out.println("Holaa" + employees);
            if(employees.size()>0)
                return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
            else
                return new ResponseEntity<List<Employee>>(employees,HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<List<Employee>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Buscar trabajador por id", notes ="Método para encontrar un trabajador con su respectivo id" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Trabajador encontrado"),
            @ApiResponse(code =404,message ="Trabajador no encontrado")
    })

    public ResponseEntity<Employee> findById(@PathVariable("id") Long id){
        try{
            Optional<Employee> employee= employeeService.getById(id);
            if(!employee.isPresent())
                return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Employee>(employee.get(),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<Employee>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value= "/searchByDni/{dni}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Buscar trabajador por DNI", notes ="Método para encontrar un trabajador con su respectivo DNI" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Trabajador encontrado"),
            @ApiResponse(code =404,message ="Trabajador no encontrado")
    })

    public ResponseEntity<Employee> findByDni(@PathVariable("dni") String dni) {
        try{
            Employee employee=employeeService.findByDni(dni);
            if(employee==null)
                return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Employee>(employee,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<Employee>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value="/searchByLastname/{lastname}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Buscar trabajador por su apellido", notes ="Método para encontrar un trabajador con su respectivo apellido" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Trabajadores  encontrados"),
            @ApiResponse(code =404,message ="Trabajadores no encontrados")
    })

    public ResponseEntity<List<Employee>> findByLastName(@PathVariable("lastname") String lastname){
        try{
            List<Employee> employees=employeeService.findByLastName(lastname);
            if(employees.size()>0)
                return new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
            else
                return new ResponseEntity<List<Employee>>(employees,HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<List<Employee>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value="/searchByLastNameandFirstName/{lastname}/{firstname}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Buscar trabajador por su apellido y nombre", notes ="Método para encontrar un trabajador con su respectivo apellido y nombre" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Trabajadores encontrados"),
            @ApiResponse(code =404,message ="Trabajadores no encontrados")
    })

    public ResponseEntity<List<Employee>> findByLastNameAndFirstName (@PathVariable("lastname") String lastname ,
                                                                      @PathVariable("firstname") String firstname)
    {
        try{
            List<Employee> employees=employeeService.findByLastNameAndFirstName(lastname,firstname);
            if(employees.size()>0)
                return new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
            else
                return new ResponseEntity<List<Employee>>(employees,HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<List<Employee>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Insertar trabajador",notes="Método para insertar trabajador")
    @ApiResponses(
            {
                    @ApiResponse(code =200, message = "Datos de trabajador insertados"),
                    @ApiResponse(code=404, message = "Datos de trabajador no insertados")
            }
    )
    public ResponseEntity<Employee> insertEmployee(@Valid @RequestBody Employee employee){
        try{
            Employee employeeNew= employeeService.save(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeNew);
        }catch (Exception e){
            return new ResponseEntity<Employee>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualización de datos trabajadores",notes="Método que actualizar los datos de los trabajadores")
    @ApiResponses(
            {
                    @ApiResponse(code =200, message = "Datos de trabajadores actualizados"),
                    @ApiResponse(code=404, message = "Datos de trabajadores no actualizados")
            }
    )
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable("id") Long id, @Valid @RequestBody Employee employee) {
        try{
            Optional<Employee> customerUp = employeeService.getById(id);
            if(!customerUp.isPresent())
                return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
            employee.setId(id);
            employeeService.save(employee);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Employee>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de los trabajadores", notes = "Método para eliminar trabajadores")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Trabajador eliminado"),
            @ApiResponse(code = 404, message = "Trabajador no encontrado")
    })

    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id")Long id){
        try{
            Optional<Employee>employeeDelete=employeeService.getById(id);
            if(!employeeDelete.isPresent())
                return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
            employeeService.delete(id);
            return new ResponseEntity<Employee>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Employee>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
