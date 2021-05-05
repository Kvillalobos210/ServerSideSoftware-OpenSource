package com.appdhome.controller;


import com.appdhome.entities.Customer;
import com.appdhome.entities.Solicitud;
import com.appdhome.services.ISolicitudService;
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
@RequestMapping("/api/solicitud")
@Api(tags="Solicitud", value = "RESTFul de Solicitudes")
public class SolicitudController {
    @Autowired
    private ISolicitudService solicitudService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar todas las solicitudes", notes = "Método para listar a todas las solicitudes")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Solicitudes encontradas"),
            @ApiResponse(code = 404, message = "Solicitudes no encontradas")
    })
    public ResponseEntity<List<Solicitud>> findAll()
    {
        try {
            List<Solicitud> solicitudes = solicitudService.getAll();
            if (solicitudes.size() > 0)
                return new ResponseEntity<List<Solicitud>>(solicitudes, HttpStatus.OK);
            else
                return new ResponseEntity<List<Solicitud>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Solicitud>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ingreso de solicitudes", notes = "Método para ingresar solicitudes")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer creado"),
            @ApiResponse(code = 404, message = "Customer no creado")
    })
    public ResponseEntity<Solicitud> insertSolicitud(@RequestBody Solicitud solicitud){
        try{
            Solicitud solicitudnew = solicitudService.save(solicitud);
            return ResponseEntity.status(HttpStatus.CREATED).body(solicitudnew);
        } catch(Exception e){
            return new ResponseEntity<Solicitud>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de las solicitudes", notes = "Método para eliminar solicitudes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Customer eliminado"),
            @ApiResponse(code = 404, message = "Customer no encontrado")
    })
    public ResponseEntity<Solicitud> deleteSolicitud(@PathVariable("id") Long id){
        try{
            Optional<Solicitud> solicitudDelete = solicitudService.getById(id);
            if(!solicitudDelete.isPresent())
                return new ResponseEntity<Solicitud>(HttpStatus.NOT_FOUND);
            solicitudService.delete(id);
            return new ResponseEntity<Solicitud>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<Solicitud>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
