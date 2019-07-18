package com.thoughtworks.parking_lot.service;


import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public void addParkingLot(ParkingLot parkingLot){
         parkingLotRepository.save(parkingLot);
    }


    public void deleteParkingLotByName(String name) {
        String uuid=parkingLotRepository.findByName(name);
        parkingLotRepository.deleteById(uuid);
    }

    public Page<ParkingLot> findAll(int page, int pageSize) {
        return parkingLotRepository.findAll(PageRequest.of(page-1,pageSize));
    }

    public ParkingLot findParkingLotByName(String parkingLotName) {
        String uuid=parkingLotRepository.findByName(parkingLotName);
       return parkingLotRepository.findById(uuid).get();
    }

    public ParkingLot updateParkingLot(ParkingLot parkingLot) {
        return parkingLotRepository.saveAndFlush(parkingLot);
    }
}
