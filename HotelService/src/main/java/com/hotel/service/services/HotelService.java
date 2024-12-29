package com.hotel.service.services;

import com.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {
    // all operations

    // create
    Hotel create(Hotel hotel);
    // get single
    Hotel getSingle(String id);
    // get all
    List<Hotel> getAll();
}
