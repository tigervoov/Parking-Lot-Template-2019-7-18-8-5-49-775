package com.thoughtworks.parking_lot.repository;


import com.thoughtworks.parking_lot.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParkingLotRepository extends JpaRepository<ParkingLot,String> {
    @Query(value = "select id from parking_lot where parking_name=:name",nativeQuery = true)
    String findByName(@Param("name") String name);
}
