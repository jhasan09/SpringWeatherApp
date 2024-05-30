package com.os.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.os.weather.entity.User;
import com.os.weather.entity.Weather;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestWebApp extends WeatherApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @org.junit.Test
    public void testEmployee() throws Exception {

        User user = User.builder()
                .id(3)
                .userName("user3")
                .email("a.b@c.com")
                .active(true)
                .password("1234")
                .firstName("mr")
                .lastName("user")
                .profileImageUrl("abc")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType("application/json")
                .content(requestJson))
                .andExpect(status().isOk());
    }

    @org.junit.Test
    public void testWeather() throws Exception {

        Weather weather = new Weather();

        weather.setTemp(33);
        weather.setFeels_like(38);
        weather.setTemp_min(15);
        weather.setTemp_max(55);
        weather.setPressure(38);
        weather.setHumidity(13);
        weather.setLocation("dhaka");
        weather.setLongitude(1234);
        weather.setLatitude(5524);
        weather.setWeather("good");
        weather.setWind(10);
        weather.setError("err");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(weather);

        mockMvc.perform(MockMvcRequestBuilders.post("/savecity")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isOk());
    }
}
