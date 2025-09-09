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

import com.example.dinemaster.model.Chef;
import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class ChefController {

    @Autowired
    private ChefRepository chefRepository;

    // API 6: GET /restaurants/chefs
    @GetMapping("/restaurants/chefs")
    public List<Chef> getAllChefs() {
        return chefRepository.getAllChefs();
    }

    // API 7: POST /restaurants/chefs
    @PostMapping("/restaurants/chefs")
    public Chef addChef(@RequestBody Chef chef) {
        return chefRepository.addChef(chef);
    }

    // API 8: GET /restaurants/chefs/{chefId}
    @GetMapping("/restaurants/chefs/{chefId}")
    public Chef getChefById(@PathVariable int chefId) {
        return chefRepository.getChefById(chefId);
    }

    // API 9: PUT /restaurants/chefs/{chefId}
    @PutMapping("/restaurants/chefs/{chefId}")
    public Chef updateChef(@PathVariable int chefId, @RequestBody Chef chef) {
        return chefRepository.updateChef(chefId, chef);
    }

    // API 10: DELETE /restaurants/chefs/{chefId}
    @DeleteMapping("/restaurants/chefs/{chefId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChef(@PathVariable int chefId) {
        chefRepository.deleteChef(chefId);
    }

    // API 11: GET /chefs/{chefId}/restaurant
    @GetMapping("/chefs/{chefId}/restaurant")
    public Restaurant getRestaurantOfChef(@PathVariable int chefId) {
        Chef chef = chefRepository.getChefById(chefId);
        if (chef.getRestaurant() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found for chef");
        }
        return chef.getRestaurant();
    }
}
