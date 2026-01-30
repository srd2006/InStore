package org.online.kz.store.service.impl;

import lombok.RequiredArgsConstructor;
import org.online.kz.store.Repository.CityRepository;
import org.online.kz.store.model.City;
import org.online.kz.store.service.CityService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service("mainCityService")
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<City> getAllCities(String cityName, String cityCode) {
        return List.of();
    }

//    @Override
//    public List<City> getAllCities() {
//        return cityRepository.findAll();
//    }

    @Override
    public City addCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void deleteCityById(int id) {
        cityRepository.deleteById(id);
    }

    @Override
    public void updateCity(City city) {
        cityRepository.save(city);
    }

    @Override
    public void updateCityPatch(int id, @RequestParam String cityCode) {
        City city = cityRepository.findById(id).get();
        city.setCityCode(cityCode);
        cityRepository.save(city);
    }

    @Override
    public City getCityById(int id) {
        return cityRepository.findById(id).get();
    }


}
