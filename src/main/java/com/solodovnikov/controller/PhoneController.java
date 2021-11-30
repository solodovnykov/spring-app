package com.solodovnikov.controller;

import com.solodovnikov.domain.Phone;
import com.solodovnikov.dto.PhoneDto;
import com.solodovnikov.service.PhoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/phones")
@RestController
public class PhoneController {

    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PhoneDto>> getAll() {
        List<Phone> phones = phoneService.getAll();
        List<PhoneDto> phoneDtos = new ArrayList<>();
        for (Phone phone : phones) {
            PhoneDto phoneDto = new PhoneDto(
                    phone.getId(),
                    phone.getNumber()
            );
            phoneDtos.add(phoneDto);
        }
        return new ResponseEntity<>(phoneDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<PhoneDto> getById(@PathVariable Integer id) {
        Phone phone;
        try {
            phone = phoneService.getById(id);
            PhoneDto phoneDto = new PhoneDto(
                    phone.getId(),
                    phone.getNumber()
            );
            return new ResponseEntity<>(phoneDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody Phone newPhone) {
        phoneService.create(newPhone);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PhoneDto> update(@PathVariable Integer id, @RequestBody Phone phone) {
        Phone phoneOld;
        try {
            phoneOld = phoneService.getById(id);
            if (phoneOld != null) {
                phoneService.update(id, phone);
                PhoneDto phoneOldDto = new PhoneDto(
                        phone.getId(),
                        phone.getNumber()
                );
                return new ResponseEntity<>(phoneOldDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (phoneService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
