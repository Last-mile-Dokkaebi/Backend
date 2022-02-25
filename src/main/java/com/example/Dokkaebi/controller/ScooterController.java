package com.example.Dokkaebi.controller;

import com.example.Dokkaebi.controller.dtos.ScooterResponseDto;
import com.example.Dokkaebi.domain.Scooter;
import com.example.Dokkaebi.service.ScooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScooterController {
    private final ScooterService scooterService;

    @GetMapping("/scooter")
    public ResponseEntity<Object> findScooters() {
        List<Scooter> Scooters = scooterService.findAllScooter();
        List<ScooterResponseDto> responseDtos = new ArrayList<>();
        for(Scooter scooter : Scooters){
            responseDtos.add(ScooterResponseDto.builder()
                            .bike(scooter.getBike())
                            .lat(scooter.getLat())
                            .lon(scooter.getLon())
                            .build());
        }
        return new ResponseEntity(responseDtos, HttpStatus.OK);
    }
}