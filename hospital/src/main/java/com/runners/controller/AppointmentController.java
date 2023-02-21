package com.runners.controller;


import com.runners.domain.Appointment;
import com.runners.dto.AppDto;
import com.runners.dto.AppRequest;
import com.runners.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/appointment")       // http://localhost:8080/v1/
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @PreAuthorize("hasRole ('PATIENT')")
    public ResponseEntity<String> createAppointment (@Valid @RequestBody Appointment appointment){
        appointmentService.createAppointment(appointment);

        String message = "Appointment is created successfully.";

        return ResponseEntity.ok(message);

    }

    @GetMapping
    @PreAuthorize("hasRole ('ADMIN')")
    public ResponseEntity<List<AppDto>> getAllDto(){
       List<AppDto> appDtoList = appointmentService.getAllDto();

       return ResponseEntity.ok(appDtoList);

    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole ('PATIENT') or hasRole('ADMIN')")
    public ResponseEntity<AppDto> getAppDtoById(@PathVariable("id") Long id){

      AppDto appDto = appointmentService.findAppDto(id);
      return ResponseEntity.ok(appDto);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole ('PATIENT')")
    public ResponseEntity<String> updateAppointment(@PathVariable("id") Long id, @Valid @RequestBody AppRequest appRequest){

        appointmentService.updateAppointment(id,appRequest);
        String message = "Appointment is updated successfully. ";

        return ResponseEntity.ok(message);

    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole ('PATIENT')")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id") Long id){

        appointmentService.deleteAppointment(id);

        String message = "Appointment is deleted successfully !";
        return ResponseEntity.ok(message);

    }







}
