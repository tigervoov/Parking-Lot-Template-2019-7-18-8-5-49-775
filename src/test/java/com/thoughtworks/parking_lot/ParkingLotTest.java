package com.thoughtworks.parking_lot;


import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.entity.ParkingOrders;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;


import com.thoughtworks.parking_lot.repository.ParkingOrdersRepository;
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

    @Autowired
    private ParkingOrdersRepository parkingOrdersRepository;


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
    public void should_build_parkingOrder_when_park_a_car_to_parkingLot()throws Exception{
        //given
        ParkingLot parkingLot=new ParkingLot("南软停车场","60","珠海香洲");
        ParkingLot parkingLot1=parkingLotRepository.saveAndFlush(parkingLot);

        //when
        ParkingOrders parkingOrders=new ParkingOrders("南软停车场","粤C:IT8888","2018-10-12 18:21:12");
        JSONObject jsonObject=new JSONObject(parkingOrders);

        //then
        this.mockMvc.perform(post("/parkingOrders").content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isCreated());

    }
    @Test
    public void should_change_order_status_is_true_when_fetch_a_car_from_parkingLot()throws Exception{
        //given
        ParkingOrders parkingOrders=new ParkingOrders("南软停车场","粤C:IT8888","2018-10-12 18:21:12");
        parkingOrdersRepository.saveAndFlush(parkingOrders);
        JSONObject jsonObject=new JSONObject(parkingOrders);

        //when+then
        this.mockMvc.perform(put("/parkingOrders/").content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
    }
    @Test
    public void should_say_the_parkingLot_is_full_when_a_car_park_to_no_capacity_parkingLot()throws Exception{
        //given
        ParkingLot parkingLot=new ParkingLot("南软停车场1","0","珠海香洲");
        parkingLotRepository.saveAndFlush(parkingLot);
        ParkingOrders parkingOrders=new ParkingOrders("南软停车场1","粤C:IT8888","2018-10-12 18:21:12");
        JSONObject jsonObject=new JSONObject(parkingOrders);

        String resuleMessage=this.mockMvc.perform(post("/parkingOrders/").content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn().getResponse().getContentAsString();
        Assertions.assertEquals("车位已满",resuleMessage);
    }

}
