package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.entity.ParkingOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParkingOrdersRepository extends JpaRepository<ParkingOrders,String> {

    @Modifying
    @Query(value = "update parking_orders set end_time=:end_time,status=:status where car_number=:car_number",nativeQuery = true)
    void updateOrder(@Param("car_number") String car_number,@Param("end_time") String end_time,@Param("status") String status);
}
