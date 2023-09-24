package com.klasha.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.klasha.dto.responseDto.ApiDataResponseDto;
import com.klasha.dto.responseDto.currency.CurrencyConversionResponse;
import com.klasha.dto.responseDto.state.StateCitiesResponse;
import com.klasha.dto.resquestDto.CurrencyConversion;
import com.klasha.service.main.MainService;
import com.klasha.service.population.PopulationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CountryController {

    private final PopulationService populationService;

    private final MainService mainService;



    @GetMapping("/country/cities")
    public ResponseEntity<ApiDataResponseDto> getCountryCities(@RequestParam(name = "limit") int numberOfCities) {
        return ResponseEntity.ok(populationService.getCountryCities(numberOfCities));
    }

    @GetMapping("/country/info")
    public ResponseEntity<ApiDataResponseDto> getCountryInfo(@RequestParam String country) throws JsonProcessingException {
        return ResponseEntity.ok(mainService.getCountryInfo(country));
    }

    @GetMapping("/country/state/cities")
    public ResponseEntity<ApiDataResponseDto> getStateAndCities(@RequestParam String country) {
        return ResponseEntity.ok(mainService.getStateAndCities(country));
    }

    @PostMapping("/country/currency/conversion")
    public ResponseEntity<ApiDataResponseDto> currencyConversion(@Valid @RequestBody CurrencyConversion currencyConversion) {
        return ResponseEntity.ok(mainService.currencyConversion(currencyConversion));
    }

}
