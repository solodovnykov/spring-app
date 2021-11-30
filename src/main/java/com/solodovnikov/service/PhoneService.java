package com.solodovnikov.service;

import com.solodovnikov.domain.Phone;
import com.solodovnikov.repository.PhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService implements AbstractService<Phone, Integer> {

    public final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public List<Phone> getAll() {
        return phoneRepository.findAll();
    }

    @Override
    public Phone getById(Integer id) {
        return phoneRepository.getOne(id);
    }

    @Override
    public Phone create(Phone newPhone) {
        return phoneRepository.save(newPhone);
    }

    @Override
    public Phone update(Integer id, Phone phone) {
        if (phoneRepository.findById(id).isPresent()) {
            return phoneRepository.save(phone);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteById(Integer id) {
        if (phoneRepository.findById(id).isPresent()) {
            phoneRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
