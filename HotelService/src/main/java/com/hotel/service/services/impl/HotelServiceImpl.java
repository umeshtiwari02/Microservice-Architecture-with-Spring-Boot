package com.hotel.service.services.impl;

import com.hotel.service.entities.Hotel;
import com.hotel.service.exceptions.ResourceNotFoundException;
import com.hotel.service.repositories.HotelRepository;
import com.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString().substring(19);
        hotel.setId(randomHotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getSingle(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Give hotel id " + id + " is not found!!"));
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }
}
