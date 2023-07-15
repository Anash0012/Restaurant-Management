package com.example.RestaurantManagement.Repository;

import com.example.RestaurantManagement.Model.FoodItem;
import com.example.RestaurantManagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IFoodRepo extends JpaRepository<FoodItem,Long> {

}