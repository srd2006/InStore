package org.online.kz.store.service;

import org.online.kz.store.model.City;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface CityService {

    List<City> getAllCities(String cityName, String cityCode);

    City addCity(City city);

    void deleteCityById(int id);

    void updateCity(City city);

    void updateCityPatch(@RequestParam int id, @RequestParam String cityCode);

    City getCityById(int id);
}
