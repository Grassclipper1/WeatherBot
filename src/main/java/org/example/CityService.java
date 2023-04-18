package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CityService {
static List<City> cityList = new ArrayList<>();





    public static void cityCreator() {
        File jsonFile = new File("cities.js");
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Object> rawCities = objectMapper.readValue(jsonFile, List.class);
            for (Object rawCity : rawCities) {
                String name = (String) ((java.util.LinkedHashMap) rawCity).get("city");
                String lat = (String) ((java.util.LinkedHashMap) rawCity).get("lat");
                String lng = (String) ((java.util.LinkedHashMap) rawCity).get("lng");
                City newCity = new City(name.toLowerCase(), lat, lng);
                cityList.add(newCity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String cityFinder(String city) {
       String link = null;
        for (City town : cityList) {
            if (town.getName().equals(city)) {
                String lat = town.getLat();
                String lng = town.getLng();
                 link = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/" + lng + "/lat/" +
                        lat + "/data.json";
            }
        }
        return link;
    }


}
