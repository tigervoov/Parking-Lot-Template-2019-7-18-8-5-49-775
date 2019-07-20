package com.thoughtworks.parking_lot.service;


import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.entity.ParkingOrders;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class ParkingOrdersService {

    @Autowired
    private ParkingOrdersRepository parkingOrdersRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public String addParkingOrder(ParkingOrders parkingOrders) {
        String parkingName=parkingOrders.getParking_name();
        String  parkingLotCapacity=parkingLotRepository.findParkingLotByName(parkingName);
        Integer capacity=Integer.valueOf(parkingLotCapacity);
        if(capacity>0){
            parkingOrdersRepository.save(parkingOrders);
            String newCapacity=String.valueOf(capacity-1);
            parkingLotRepository.updateCapacity(parkingName,newCapacity);
            return "请入场";
        }else {
            return "车位已满";
        }
    }

    public void updateOrderStatus(ParkingOrders parkingOrders) {
        parkingOrders.setEnd_time(String.valueOf(new Date().getTime()));
        parkingOrders.setStatus("1");
        //parkingOrdersRepository.saveAndFlush(parkingOrders);
        parkingOrdersRepository.updateOrder(parkingOrders.getCar_number(),parkingOrders.getEnd_time(),parkingOrders.getStatus());
    }
}
