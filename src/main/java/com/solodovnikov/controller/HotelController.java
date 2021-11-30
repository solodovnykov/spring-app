package com.solodovnikov.controller;


import com.solodovnikov.domain.Hotel;
import com.solodovnikov.dto.HotelDto;
import com.solodovnikov.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/hotels")
@RestController
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<HotelDto>> getAll() {
        List<Hotel> hotels = hotelService.getAll();
        List<HotelDto> hotelDtos = new ArrayList<>();
        for (Hotel hotel : hotels) {
            HotelDto hotelDto = new HotelDto(
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getReview(),
                    hotel.getCountry()
            );
            hotelDtos.add(hotelDto);
        }
        return new ResponseEntity<>(hotelDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<HotelDto> getById(@PathVariable Integer id) {
        Hotel hotel;
        try {
            hotel = hotelService.getById(id);
            HotelDto hotelDto = new HotelDto(
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getReview(),
                    hotel.getCountry()
            );
            return new ResponseEntity<>(hotelDto, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody Hotel newHotel) {
        hotelService.create(newHotel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HotelDto> update(@PathVariable Integer id, @RequestBody Hotel hotel) {
        Hotel hotelOld;
        try {
            hotelOld = hotelService.getById(id);
            if (hotelOld != null) {
                hotelService.update(id, hotel);
                HotelDto hotelOldDto = new HotelDto(
                        hotel.getId(),
                        hotel.getName(),
                        hotel.getReview(),
                        hotel.getCountry()
                );
                return new ResponseEntity<>(hotelOldDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (hotelService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
