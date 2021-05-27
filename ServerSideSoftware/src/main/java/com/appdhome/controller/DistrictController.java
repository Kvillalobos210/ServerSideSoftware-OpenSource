package com.appdhome.controller;

import com.appdhome.entities.District;
import com.appdhome.services.IDistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ditricts")
@Api(tags = "District", value = "RESTFul de Districts")
public class DistrictController {
    @Autowired
    private IDistrictService districtService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lista de todos los distritos", notes = "Método para listar a todos los distritos")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Districts encontrados"),
            @ApiResponse(code = 404, message = "Districts no encontrados")
    })
    public ResponseEntity<List<District>>findAll(){
        try {
            List<District> districts = districtService.getAll();
            if (districts.size()>0){
                return new ResponseEntity<List<District>>(districts, HttpStatus.OK);
            }else{
                return new ResponseEntity<List<District>>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<List<District>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar distrito por Id", notes = "Método para encontrar un distrito por id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "District encontrado"),
            @ApiResponse(code = 404, message = "District no encontrado")
    })
    public ResponseEntity<District> findById(@PathVariable("id") Long id){
        try{
            Optional<District> district=districtService.getById(id);
            if (!district.isPresent()){
                return new ResponseEntity<District>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<District>(district.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<District>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar distrito por su nombre", notes = "Método para encontrar distritos por su nombre")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Districts encontrados"),
            @ApiResponse(code = 404, message = "Districts no encontrados")
    })
    public ResponseEntity<District> finByName(@PathVariable("name") String name){
        try {
            District district=districtService.findByName(name);
            if (district == null){
                return new ResponseEntity<District>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<District>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<District>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ingreso de Distritos", notes = "Método para ingresar distritos")
    @ApiResponses({
            @ApiResponse(code = 201, message = "District creado"),
            @ApiResponse(code = 404, message = "District no creado")
    })
    public ResponseEntity<District> insertDistrict(@RequestBody District district){
        try {
            District districtNew = districtService.save(district);
            return ResponseEntity.status(HttpStatus.CREATED).body(districtNew);
        }catch (Exception e){
            return new ResponseEntity<District>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Districts", notes = "Método para actualizar los datos del district")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de district actualizados"),
            @ApiResponse(code = 404, message = "Datos de district no actualizados")
    })
    public ResponseEntity<District> updateDistrict(@PathVariable("id") Long id, @RequestBody District district){
        try {
            Optional<District> districtUp=districtService.getById(id);
            if (!districtUp.isPresent()){
                return new ResponseEntity<District>(HttpStatus.NOT_FOUND);
            }
            district.setId(id);
            districtService.save(district);
            return new ResponseEntity<District>(district,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<District>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de los districts", notes = "Método para eliminar Districts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "District eliminado"),
            @ApiResponse(code = 404, message = "District no encontrado")
    })
    public ResponseEntity<District> deleteDistrict (@PathVariable("id") Long id){
        try {
            Optional<District> districtDelete=districtService.getById(id);
            if(!districtDelete.isPresent()){
                return new ResponseEntity<District>(HttpStatus.NOT_FOUND);
            }
            districtService.delete(id);
            return new ResponseEntity<District>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<District>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
