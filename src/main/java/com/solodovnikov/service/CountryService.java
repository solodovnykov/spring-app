package com.solodovnikov.service;

import com.solodovnikov.domain.Country;
import com.solodovnikov.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements AbstractService<Country, Integer> {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country getById(Integer id) {
        return countryRepository.getOne(id);
    }

    @Override
    public Country create(Country newCountry) {
        return countryRepository.save(newCountry);
    }

    @Override
    public Country update(Integer id, Country country) {
        if (countryRepository.findById(id).isPresent()) {
            return countryRepository.save(country);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteById(Integer id) {
        if (countryRepository.findById(id).isPresent()) {
            countryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
