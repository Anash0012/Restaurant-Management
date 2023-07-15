package com.example.RestaurantManagement.Service;

import com.example.RestaurantManagement.Model.FoodItem;
import com.example.RestaurantManagement.Repository.IFoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    @Autowired
    IFoodRepo ifoodrepo;
    public List<FoodItem> getall() {
        return ifoodrepo.findAll();

    }
}
