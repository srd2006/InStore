package org.online.kz.store.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "It's CityDto")
public class CityDTo {
    private Integer id;
    private String gorod;
    private String cityCode;
}
