package com.solodovnikov.service;

import com.solodovnikov.domain.Guest;
import com.solodovnikov.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService implements AbstractService<Guest, Integer>{

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public List<Guest> getAll() {
        return guestRepository.findAll();
    }

    @Override
    public Guest getById(Integer id) {
        return guestRepository.getOne(id);
    }

    @Override
    public Guest create(Guest newGuest) {
        return guestRepository.save(newGuest);
    }

    @Override
    public Guest update(Integer id, Guest guest) {
        if (guestRepository.findById(id).isPresent()) {
            return guestRepository.save(guest);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteById(Integer id) {
        if (guestRepository.findById(id).isPresent()) {
            guestRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
