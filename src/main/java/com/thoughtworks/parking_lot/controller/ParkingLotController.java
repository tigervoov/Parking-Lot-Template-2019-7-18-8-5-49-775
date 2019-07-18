package com.thoughtworks.parking_lot.controller;


import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;


    @PostMapping(produces = {"application/json"})
    public ResponseEntity addAparkingLot(@RequestBody ParkingLot parkingLot){

        parkingLotService.addParkingLot(parkingLot);

        return ResponseEntity.ok().build();
    }


}
