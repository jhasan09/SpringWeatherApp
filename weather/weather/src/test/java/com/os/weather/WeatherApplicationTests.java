package com.os.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.os.weather.controller.WeatherRestController;
import com.os.weather.entity.LikedCity;
import com.os.weather.entity.Weather;
import com.os.weather.repository.WeatherRepository;
import com.os.weather.services.service.ListCitiesActionService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.HashMap;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Spy // mock it partially
    @InjectMocks
    private WeatherRestController weatherRestController;
    @Mock
    WeatherRepository weatherRepository;
    @Mock
    private ListCitiesActionService listCitiesActionService;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @org.junit.Test
    public void searchWeather() throws Exception {
        Weather weather = new Weather();
        weather.setLocation("Dhaka");
        ResponseEntity<Weather> weatherResponseEntity = new ResponseEntity<>(weather, HttpStatus.CREATED);
        when(weatherRestController.serachWeather("Dhaka")).thenReturn(weatherResponseEntity);
    }

    @org.junit.Test
    public void testLikedCities() throws Exception {
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

    @org.junit.Test
    public void testSaveLikedCity() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Weather weather = new Weather();
        weather.setLocation("Dhaka");
        HashMap<String, String> map = new HashMap<>();
        map.put("responseText", weather.location + " added to liked list");
        when(weatherRestController.saveCity(weather, request)).thenReturn(map);
    }

    @org.junit.Test
    public void testDeleteLikedCity() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Weather weather = new Weather();
        weather.setLocation("Dhaka");
        when(weatherRestController.deleteCity(weather, request)).thenReturn(LikedCity.builder().build());
    }
}
