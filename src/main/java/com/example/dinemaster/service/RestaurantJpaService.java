/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * 
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here

package com.example.dinemaster.service;

import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.repository.RestaurantRepository;
import com.example.dinemaster.repository.RestaurantJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class RestaurantJpaService implements RestaurantRepository {

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantJpaRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        return restaurantJpaRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantJpaRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(int restaurantId, Restaurant updatedRestaurant) {
        Restaurant existingRestaurant = restaurantJpaRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));

        if (updatedRestaurant.getName() != null) existingRestaurant.setName(updatedRestaurant.getName());
        if (updatedRestaurant.getAddress() != null) existingRestaurant.setAddress(updatedRestaurant.getAddress());
        if (updatedRestaurant.getCuisineType() != null) existingRestaurant.setCuisineType(updatedRestaurant.getCuisineType());
        if (updatedRestaurant.getRating() != 0) existingRestaurant.setRating(updatedRestaurant.getRating());

        return restaurantJpaRepository.save(existingRestaurant);
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        Restaurant restaurant = restaurantJpaRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
        restaurantJpaRepository.delete(restaurant);
    }
}
