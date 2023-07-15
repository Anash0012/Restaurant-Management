package com.example.RestaurantManagement.Repository;

import com.example.RestaurantManagement.Model.Admin;
import com.example.RestaurantManagement.Model.Authenticationtoken;
import com.example.RestaurantManagement.Model.FoodItem;
import com.example.RestaurantManagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<Authenticationtoken,Long> {
    Authenticationtoken findFirstByTokenValue(String authTokenValue);

    Authenticationtoken findFirstByUser(User user);

    Authenticationtoken findFirstByAdmin(Admin admin);
}
