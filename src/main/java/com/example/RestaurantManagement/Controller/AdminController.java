package com.example.RestaurantManagement.Controller;

import com.example.RestaurantManagement.Model.Dto.SignInInput;
import com.example.RestaurantManagement.Model.Dto.SignUpOutput;
import com.example.RestaurantManagement.Model.Admin;
import com.example.RestaurantManagement.Model.FoodItem;
import com.example.RestaurantManagement.Service.AuthenticationService;
import com.example.RestaurantManagement.Service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@Validated
@RestController
public class AdminController {


    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("admin/signup")
    public SignUpOutput signUpInstaAdmin(@RequestBody Admin admin) throws NoSuchAlgorithmException {

        return adminService.signUpAdmin(admin);
    }

    @PostMapping("admin/signIn")
    public String sigInInstaAdmin(@RequestBody @Valid SignInInput signInInput)
    {
        return adminService.signInAdmin(signInInput);
    }

    @DeleteMapping("admin/signOut")
    public String sigOutInstaAdmin(String email, String token) {
        if (authenticationService.authenticate(email, token)) {
            return adminService.sigOutAdmin(email);
        } else {
            return "Sign out not allowed for non authenticated admin.";
        }
    }
    @PostMapping("admin/fooditem")
    public String addfooditem(FoodItem foodlist,String email){
        return adminService.addfooditem(foodlist, email);
    }



}
