package com.example.RestaurantManagement.Repository;

import com.example.RestaurantManagement.Model.FoodItem;
import com.example.RestaurantManagement.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends JpaRepository<OrderEntity,Long> {
}
