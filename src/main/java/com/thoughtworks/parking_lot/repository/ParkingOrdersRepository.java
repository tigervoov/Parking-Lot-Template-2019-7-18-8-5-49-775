package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.entity.ParkingOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingOrdersRepository extends JpaRepository<ParkingOrders,String> {

}
