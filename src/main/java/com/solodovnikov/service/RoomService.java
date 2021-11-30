package com.solodovnikov.service;

import com.solodovnikov.domain.Room;
import com.solodovnikov.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements AbstractService<Room, Integer> {

    public final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room getById(Integer id) {
        return roomRepository.getOne(id);
    }

    @Override
    public Room create(Room newRoom) {
        return roomRepository.save(newRoom);
    }

    @Override
    public Room update(Integer id, Room room) {
        if (roomRepository.findById(id).isPresent()) {
            return roomRepository.save(room);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteById(Integer id) {
        if (roomRepository.findById(id).isPresent()) {
            roomRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
