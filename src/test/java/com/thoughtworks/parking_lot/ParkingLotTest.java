package com.thoughtworks.parking_lot;


import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void should_return_201_when_add_new_parkingLot() throws Exception {

        //given
        ParkingLot parkingLot=new ParkingLot("1","100","珠海香洲");
        JSONObject jsonObject=new JSONObject(parkingLot);

        //when+then
        this.mockMvc.perform(post("/parkinglots").content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }
    @Test
    public void should_return_200_when_delete_a_parkingLot() throws Exception{
        //given+when+then
        this.mockMvc.perform(delete("/parkinglots/parkinglot1")).andExpect(status().isOk());
    }
    @Test
    public void should_return_second_page_content_when_page_equal_2() throws Exception{
        //given
        String mvcResult=this.mockMvc.perform(get("/parkinglots?page=2&pageSize=15")).andReturn().getResponse().getContentAsString();
        JSONArray jsonArray=new JSONArray(mvcResult);

        //when+then
        Assertions.assertEquals(3,jsonArray.length());//一共18条数据
    }
    @Test
    public void should_return_parkingLot_info_when_find_by_parkingLot_name() throws Exception {
        String mvcResult=this.mockMvc.perform(get("/parkinglots/parkinglot11")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JSONObject jsonObject=new JSONObject(mvcResult);

        Assertions.assertEquals("parkinglot11",jsonObject.getString("parking_name"));
    }
    @Test
    public void should_return_parkingLot_info_when_update_a_parkingLot()throws Exception{
        //given
        ParkingLot parkingLot=new ParkingLot("停车场1","60","珠海香洲");
        ParkingLot parkingLot1=parkingLotRepository.saveAndFlush(parkingLot);

        //when
        parkingLot1.setCapacity("80");
        JSONObject jsonObject=new JSONObject(parkingLot1);
        String resultParkingLot=this.mockMvc.perform(put("/parkinglots").content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn().getResponse().getContentAsString();
        //then
        JSONObject returnJsonObject=new JSONObject(resultParkingLot);
        Assertions.assertEquals("80",returnJsonObject.getString("capacity"));
    }
    @Test
    public void should_build_parkingOrder_when_update_a_parkingLot()throws Exception{
        //given
        ParkingLot parkingLot=new ParkingLot("停车场1","60","珠海香洲");
        ParkingLot parkingLot1=parkingLotRepository.saveAndFlush(parkingLot);

        //when
        parkingLot1.setCapacity("80");
        JSONObject jsonObject=new JSONObject(parkingLot1);
        String resultParkingLot=this.mockMvc.perform(put("/parkinglots").content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn().getResponse().getContentAsString();
        //then
        JSONObject returnJsonObject=new JSONObject(resultParkingLot);
        Assertions.assertEquals("80",returnJsonObject.getString("capacity"));
    }


}
