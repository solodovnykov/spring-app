package com.solodovnikov.controller;

import com.solodovnikov.domain.Guest;
import com.solodovnikov.dto.GuestDto;
import com.solodovnikov.service.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/guests")
@RestController
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<GuestDto>> getAll() {
        List<Guest> guests = guestService.getAll();
        List<GuestDto> guestDtos = new ArrayList<>();
        for (Guest guest : guests) {
            GuestDto guestDto = new GuestDto(
                    guest.getId(),
                    guest.getFirstName(),
                    guest.getLastName(),
                    guest.getStateOfAccount()
            );
            guestDtos.add(guestDto);
        }
        return new ResponseEntity<>(guestDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<GuestDto> getById(@PathVariable Integer id) {
        Guest guest;
        try {
            guest = guestService.getById(id);
            GuestDto guestDto = new GuestDto(
                    guest.getId(),
                    guest.getFirstName(),
                    guest.getLastName(),
                    guest.getStateOfAccount()
            );
            return new ResponseEntity<>(guestDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody Guest newGuest) {
        guestService.create(newGuest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GuestDto> update(@PathVariable Integer id, @RequestBody Guest guest) {
        Guest guestOld;
        try {
            guestOld = guestService.getById(id);
            if (guestOld != null) {
                guestService.update(id, guest);
                GuestDto guestOldDto = new GuestDto(
                        guest.getId(),
                        guest.getFirstName(),
                        guest.getLastName(),
                        guest.getStateOfAccount()
                );
                return new ResponseEntity<>(guestOldDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (guestService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
