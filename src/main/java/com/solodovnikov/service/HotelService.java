package com.solodovnikov.service;

import com.solodovnikov.domain.Hotel;
import com.solodovnikov.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements AbstractService<Hotel, Integer>{

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getById(Integer id) {
        return hotelRepository.getOne(id);
    }

    @Override
    public Hotel create(Hotel newHotel) {
        return hotelRepository.save(newHotel);
    }

    @Override
    public Hotel update(Integer id, Hotel hotel) {
        if (hotelRepository.findById(id).isPresent()) {
            return hotelRepository.save(hotel);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteById(Integer id) {
        if (hotelRepository.findById(id).isPresent()) {
            hotelRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
