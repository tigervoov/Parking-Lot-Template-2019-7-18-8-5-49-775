package com.thoughtworks.parking_lot;


import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;


import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Test
    public void should_return_201when_add_new_parkingLot() throws Exception {
        ParkingLot parkingLot=new ParkingLot("1","100","珠海香洲");
        JSONObject jsonObject=new JSONObject(parkingLot);
        this.mockMvc.perform(post("/parkinglots").content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }
    

}
