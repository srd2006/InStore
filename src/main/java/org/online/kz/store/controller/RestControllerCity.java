package org.online.kz.store.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.online.kz.store.dto.CityDTo;
import org.online.kz.store.dto.ErrorCityDto;
import org.online.kz.store.mapper.CityMapper;
import org.online.kz.store.model.City;
import org.online.kz.store.service.CityService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city-controller")
@RequiredArgsConstructor
@Tag(name = "City controller", description = "API для работы с городом для user-ов ")
public class RestControllerCity {

    private final CityService cityService;
    private final CityMapper cityMapper;

    @GetMapping("/")
    @Operation(summary = "Получение всех городов", description = "Получения  городов" +
            " с помощью cityName и cityCode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычки получены", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CityDTo.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples =
                    @ExampleObject(value = """
                            {
                                  "status": 400,
                                  "title": "PROBLEM FORMAT",
                                  "details": "INCORRECT FORMAT"
                            }
                            """),
                            schema = @Schema(implementation = ErrorCityDto.class)
                    )
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                      {
                                              "status": 500,
                                              "title": "INTERNAL ERROR",
                                              "details": "SERVER FORMAT"
                                      }
                                    """)
                    }, schema = @Schema(implementation = ErrorCityDto.class))
            })
    })
    public ResponseEntity<List<CityDTo>> getCities(@RequestParam(required = false) String cityName,
                                                   @RequestParam(required = false) String cityCode) {
        List<CityDTo> cityDTos = cityMapper.toCityDTo(cityService.getAllCities(cityName, cityCode));
        return ResponseEntity.ok(cityDTos);
    }


    @PostMapping("/add-cities")
    @Operation(summary = "Добавления города", description = "Добавления города по объекту ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Городы добавлены", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = CityDTo.class))
            }),

            @ApiResponse(responseCode = "400", description = "Неверный запрос", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                        {
                                                "status": 400,
                                                "title": "PROBLEM FORMAT",
                                                "details": "INCORRECT FORMAT"
                                        }
                                    """
                            )
                    }, schema = @Schema(implementation = ErrorCityDto.class))

            }),

            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                      {
                                              "status": 500,
                                              "title": "INTERNAL ERROR",
                                              "details": "SERVER FORMAT"
                                      }
                                    """)
                    }, schema = @Schema(implementation = ErrorCityDto.class))
            })


    })
    public City addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }


    @DeleteMapping("/delete-city/{id}")
    @Operation(summary = "Удаления города по ID", description = "Удаления города по ID для каждого города")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Город удалился", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = CityDTo.class))}),
            @ApiResponse(responseCode = "400", description = "Такой город нет", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    
                                    "status":400,
                                    "title": "Problem Format",
                                    "details": "INCORRECT FORMAT"
                                    """)
                    }, schema = @Schema(implementation = ErrorCityDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Серверный ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject("""
                                    {
                                             "status": 500,
                                             "title": "INTERNAL ERROR",
                                             "details": "SERVER FORMAT"
                                     }
                                    """)
                    })
            })

    })
    public void deleteCity(@PathVariable int id) {
        cityService.deleteCityById(id);
    }

    @PutMapping("/update-city")
    @Operation(summary = "Изменения", description = "Изменения  города по ID и код")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Город удалился", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = CityDTo.class))}),
            @ApiResponse(responseCode = "400", description = "Такой город нет", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    
                                    "status":400,
                                    "title": "Problem Format",
                                    "details": "INCORRECT FORMAT"
                                    """)
                    }, schema = @Schema(implementation = ErrorCityDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Серверный ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject("""
                                    {
                                             "status": 500,
                                             "title": "INTERNAL ERROR",
                                             "details": "SERVER FORMAT"
                                     }
                                    """)
                    })
            })

    })
    public void updateCity(@RequestBody City city) {
        cityService.updateCity(city);
    }

    @PatchMapping("/update-patch-city")
    @Operation(summary = "Изменения", description = "Изменения города по ID и код")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Город удалился", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = CityDTo.class))}),
            @ApiResponse(responseCode = "400", description = "Такой город нет", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    
                                    "status":400,
                                    "title": "Problem Format",
                                    "details": "INCORRECT FORMAT"
                                    """)
                    }, schema = @Schema(implementation = ErrorCityDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Серверный ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject("""
                                    {
                                             "status": 500,
                                             "title": "INTERNAL ERROR",
                                             "details": "SERVER FORMAT"
                                     }
                                    """)
                    })
            })

    })
    public void updateCityPatch(@RequestParam int id, @RequestParam String cityCode) {
        cityService.updateCityPatch(id, cityCode);
    }


}
