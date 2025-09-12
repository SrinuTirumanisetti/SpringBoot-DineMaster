package com.example.dinemaster.controller;

import com.example.dinemaster.model.Chef;
import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.service.ChefJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ChefController {

    @Autowired
    private ChefJpaService chefService;

    // API 6: GET /restaurants/chefs
    @GetMapping("/restaurants/chefs")
    public List<Chef> getAllChefs() {
        return chefService.getAllChefs();
    }

    // API 7: POST /restaurants/chefs
    @PostMapping("/restaurants/chefs")
    public Chef addChef(@RequestBody Chef chef) {
        return chefService.addChef(chef);
    }

    // API 8: GET /restaurants/chefs/{chefId}
    @GetMapping("/restaurants/chefs/{chefId}")
    public Chef getChefById(@PathVariable int chefId) {
        try {
            return chefService.getChefById(chefId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chef not found");
        }
    }

    // API 9: PUT /restaurants/chefs/{chefId}
    @PutMapping("/restaurants/chefs/{chefId}")
    public Chef updateChef(@PathVariable int chefId, @RequestBody Chef chef) {
        try {
            return chefService.updateChef(chefId, chef);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chef not found");
        }
    }

    // API 10: DELETE /restaurants/chefs/{chefId}
    @DeleteMapping("/restaurants/chefs/{chefId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChef(@PathVariable int chefId) {
        try {
            chefService.deleteChef(chefId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chef not found");
        }
    }

    // API 11: GET /chefs/{chefId}/restaurant
    @GetMapping("/chefs/{chefId}/restaurant")
    public Restaurant getRestaurantOfChef(@PathVariable int chefId) {
        try {
            Chef chef = chefService.getChefById(chefId);
            return chef.getRestaurant();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chef not found");
        }
    }
}
