package com.thoughtworks.parking_lot.controller;


import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;


    @PostMapping(produces = {"application/json"})
    public ResponseEntity addAparkingLot(@RequestBody ParkingLot parkingLot){

        parkingLotService.addParkingLot(parkingLot);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/{parkingLotName}")
    public ResponseEntity delete(@PathVariable String parkingLotName){
        parkingLotService.deleteParkingLotByName(parkingLotName);
        return ResponseEntity.ok().build();
    }
    @GetMapping(params = {"page","pageSize"})
    public  ResponseEntity getAll(@RequestParam int page,int pageSize){
       Page<ParkingLot> parkingLot= parkingLotService.findAll(page,pageSize);
       List<ParkingLot> parkingLotList=parkingLot.getContent();
       return ResponseEntity.ok().body(parkingLotList);
    }
    @GetMapping("/{parkingLotName}")
    public ResponseEntity findParkingLotByName(@PathVariable String parkingLotName){
        ParkingLot parkingLot=parkingLotService.findParkingLotByName(parkingLotName);
        return ResponseEntity.ok().body(parkingLot);
    }





}
