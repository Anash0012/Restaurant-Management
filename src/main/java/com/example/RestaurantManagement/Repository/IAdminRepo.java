package com.example.RestaurantManagement.Repository;

import com.example.RestaurantManagement.Model.Admin;
import com.example.RestaurantManagement.Model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepo extends JpaRepository<Admin,Long> {
    Admin findFirstByAdminEmail(String newEmail);
}