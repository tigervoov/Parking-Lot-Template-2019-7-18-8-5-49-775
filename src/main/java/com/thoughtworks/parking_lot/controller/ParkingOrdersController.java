package com.thoughtworks.parking_lot.controller;


import com.thoughtworks.parking_lot.entity.ParkingOrders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingOrders")
public class ParkingOrdersController {


    @PostMapping(produces = {"application/json"})
    public ResponseEntity buildParkingOrder(@RequestBody ParkingOrders parkingOrders){


        return null;
    }

}
