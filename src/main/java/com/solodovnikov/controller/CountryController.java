package com.solodovnikov.controller;

import com.solodovnikov.domain.Country;
import com.solodovnikov.dto.CountryDto;
import com.solodovnikov.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/countries")
@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CountryDto>> getAll() {
        List<Country> countries = countryService.getAll();
        List<CountryDto> countryDtos = new ArrayList<>();
        for (Country country : countries) {
            CountryDto countryDto = new CountryDto(
                    country.getId(),
                    country.getName()
            );
            countryDtos.add(countryDto);
        }
        return new ResponseEntity<>(countryDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CountryDto> getById(@PathVariable Integer id) {
        Country country;
        try {
            country = countryService.getById(id);

            CountryDto countryDto = new CountryDto(
                    country.getId(),
                    country.getName()
            );
            return new ResponseEntity<>(countryDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody Country newCountry) {
        countryService.create(newCountry);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CountryDto> update(@PathVariable Integer id, @RequestBody Country country) {
        Country countryOld;
        try {
            countryOld = countryService.getById(id);
            if (countryOld != null) {
                countryService.update(id, country);
                CountryDto countryOldDto = new CountryDto(
                        countryOld.getId(),
                        countryOld.getName()
                );
                return new ResponseEntity<>(countryOldDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
