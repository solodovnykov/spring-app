package com.solodovnikov.controller;

import com.solodovnikov.domain.Room;
import com.solodovnikov.dto.RoomDto;
import com.solodovnikov.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/rooms")
@RestController
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RoomDto>> getAll() {
        List<Room> rooms = roomService.getAll();
        List<RoomDto> roomDtos = new ArrayList<>();
        for (Room room : rooms) {
            RoomDto roomDto = new RoomDto(
                    room.getId(),
                    room.getMaxPersons(),
                    room.getPricePerNight(),
                    room.getStatus()
            );
            roomDtos.add(roomDto);
        }
        return new ResponseEntity<>(roomDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<RoomDto> getById(@PathVariable Integer id) {
        Room room;
        try {
            room = roomService.getById(id);
            RoomDto roomDto = new RoomDto(
                    room.getId(),
                    room.getMaxPersons(),
                    room.getPricePerNight(),
                    room.getStatus()
            );
            return new ResponseEntity<>(roomDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody Room newRoom) {
        roomService.create(newRoom);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<RoomDto> update(@PathVariable Integer id, @RequestBody Room room) {
        Room roomOld;
        try {
            roomOld = roomService.getById(id);
            if (roomOld != null) {
                roomService.update(id, room);
                RoomDto roomOldDto = new RoomDto(
                        room.getId(),
                        room.getMaxPersons(),
                        room.getPricePerNight(),
                        room.getStatus()
                );
                return new ResponseEntity<>(roomOldDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (roomService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
