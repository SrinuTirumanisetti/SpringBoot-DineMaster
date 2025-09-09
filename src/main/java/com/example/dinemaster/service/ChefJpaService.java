/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here

package com.example.dinemaster.service;

import com.example.dinemaster.model.Chef;
import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.repository.ChefRepository;
import com.example.dinemaster.repository.ChefJpaRepository;
import com.example.dinemaster.repository.RestaurantJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ChefJpaService implements ChefRepository {

    @Autowired
    private ChefJpaRepository chefJpaRepository;

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public List<Chef> getAllChefs() {
        return chefJpaRepository.findAll();
    }

    @Override
    public Chef getChefById(int chefId) {
        return chefJpaRepository.findById(chefId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chef not found"));
    }

    @Override
    public Chef addChef(Chef chef) {
        if (chef.getRestaurant() != null && chef.getRestaurant().getId() != 0) {
            Restaurant restaurant = restaurantJpaRepository.findById(chef.getRestaurant().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
            chef.setRestaurant(restaurant);
        }
        return chefJpaRepository.save(chef);
    }

    @Override
    public Chef updateChef(int chefId, Chef updatedChef) {
        Chef existingChef = chefJpaRepository.findById(chefId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chef not found"));

        if (updatedChef.getFirstName() != null) existingChef.setFirstName(updatedChef.getFirstName());
        if (updatedChef.getLastName() != null) existingChef.setLastName(updatedChef.getLastName());
        if (updatedChef.getExpertise() != null) existingChef.setExpertise(updatedChef.getExpertise());
        if (updatedChef.getExperienceYears() != 0) existingChef.setExperienceYears(updatedChef.getExperienceYears());

        if (updatedChef.getRestaurant() != null && updatedChef.getRestaurant().getId() != 0) {
            Restaurant restaurant = restaurantJpaRepository.findById(updatedChef.getRestaurant().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
            existingChef.setRestaurant(restaurant);
        }

        return chefJpaRepository.save(existingChef);
    }

    @Override
    public void deleteChef(int chefId) {
        Chef chef = chefJpaRepository.findById(chefId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chef not found"));
        chefJpaRepository.delete(chef);
    }
}
