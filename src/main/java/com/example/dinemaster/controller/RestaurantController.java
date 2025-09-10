/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.ArrayList;
 * 
 */

// Write your code here

package com.example.dinemaster.controller;

import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // API 1: GET /restaurants
    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }

    // API 2: POST /restaurants
    @PostMapping("/restaurants")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.addRestaurant(restaurant);
    }

    // API 3: GET /restaurants/{restaurantId}
    @GetMapping("/restaurants/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable int restaurantId) {
        return restaurantRepository.getRestaurantById(restaurantId);
    }

    // API 4: PUT /restaurants/{restaurantId}
    @PutMapping("/restaurants/{restaurantId}")
    public Restaurant updateRestaurant(@PathVariable int restaurantId, @RequestBody Restaurant restaurant) {
        return restaurantRepository.updateRestaurant(restaurantId, restaurant);
    }

    // API 5: DELETE /restaurants/{restaurantId}
    @DeleteMapping("/restaurants/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable int restaurantId) {
        restaurantRepository.deleteRestaurant(restaurantId);
    }
}
