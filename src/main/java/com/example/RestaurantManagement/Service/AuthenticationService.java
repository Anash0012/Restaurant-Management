package com.example.RestaurantManagement.Service;

import com.example.RestaurantManagement.Model.Admin;
import com.example.RestaurantManagement.Model.Authenticationtoken;
import com.example.RestaurantManagement.Model.User;
import com.example.RestaurantManagement.Repository.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationRepo authenticationRepo;

    public boolean authenticate(String email, String authTokenValue)
    {
        Authenticationtoken authToken = authenticationRepo.findFirstByTokenValue(authTokenValue);

        if(authToken == null)
        {
            return false;
        }

        String tokenConnectedEmail = authToken.getUser().getUserEmail();

        return tokenConnectedEmail.equals(email);
    }

    public void saveAuthToken(Authenticationtoken authToken)
    {
        authenticationRepo.save(authToken);
    }

    public Authenticationtoken findFirstByUser(User user) {
        return authenticationRepo.findFirstByUser(user);
    }

    public void removeToken(Authenticationtoken token) {
        authenticationRepo.delete(token);
    }

    public Authenticationtoken findFirstByAdmin(Admin admin) {
        return authenticationRepo.findFirstByAdmin(admin);
    }
}
