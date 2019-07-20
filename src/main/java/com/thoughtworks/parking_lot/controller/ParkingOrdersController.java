package com.thoughtworks.parking_lot.controller;


import com.thoughtworks.parking_lot.entity.ParkingOrders;
import com.thoughtworks.parking_lot.service.ParkingOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/parkingOrders")
public class ParkingOrdersController {

    @Autowired
    private ParkingOrdersService parkingOrdersService;

    @PostMapping(produces = {"application/json"})
    public ResponseEntity buildParkingOrder(@RequestBody ParkingOrders parkingOrders){
        String resultMessage=parkingOrdersService.addParkingOrder(parkingOrders);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultMessage);
    }
    @PutMapping(produces = {"application/json"})
    public ResponseEntity fetchaCar(@RequestBody ParkingOrders parkingOrders){
        parkingOrdersService.updateOrderStatus(parkingOrders);
        return ResponseEntity.ok().build();
    }

}
