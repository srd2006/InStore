package org.online.kz.store.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.online.kz.store.dto.CityDTo;
import org.online.kz.store.model.City;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(target = "gorod", source = "cityName")
    CityDTo toCityDTo(City city);

    @Mapping(target = "cityName", source = "gorod")
    City toCityModel(CityDTo cityDTo);

    List<CityDTo> toCityDTo(List<City> cityList);

    List<City> toCityModel(List<CityDTo> cityDToList);
}
