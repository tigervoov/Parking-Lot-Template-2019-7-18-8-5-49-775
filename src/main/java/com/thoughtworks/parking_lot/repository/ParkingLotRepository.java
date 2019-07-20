package com.thoughtworks.parking_lot.repository;


import com.thoughtworks.parking_lot.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParkingLotRepository extends JpaRepository<ParkingLot,String> {
    @Query(value = "select id from parking_lot where parking_name=:name",nativeQuery = true)
    String findByName(@Param("name") String name);

    @Query(value="select capacity from parking_lot where parking_name=:name",nativeQuery = true)
    String findParkingLotByName(@Param("name") String name);


    @Modifying
    @Query(value="update parking_lot set capacity=:newCapacity where parking_name=:name",nativeQuery = true)
    void updateCapacity(@Param("name") String name,@Param("newCapacity") String newCapacity);
}
