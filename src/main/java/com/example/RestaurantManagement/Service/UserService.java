package com.example.RestaurantManagement.Service;

import com.example.RestaurantManagement.Model.Authenticationtoken;
import com.example.RestaurantManagement.Model.Dto.SignInInput;
import com.example.RestaurantManagement.Model.Dto.SignUpOutput;
import com.example.RestaurantManagement.Model.OrderEntity;
import com.example.RestaurantManagement.Model.User;
import com.example.RestaurantManagement.Repository.IOrderRepo;
import com.example.RestaurantManagement.Repository.IUserRepo;
import com.example.RestaurantManagement.Service.HashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.Email.MailHandler;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    IOrderRepo iorderrepo;

    @Autowired
    AuthenticationService authenticationService;

    public SignUpOutput signUpUser(User user) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }


        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }


        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());



            user.setUserPassword(encryptedPassword);
            userRepo.save(user);

            return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }


    public String signInUser(SignInInput signInInput) {


        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }


        User existingUser = userRepo.findFirstByUserEmail(signInEmail);

        if(existingUser == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }


        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                Authenticationtoken authToken  = new Authenticationtoken(existingUser);
                authenticationService.saveAuthToken(authToken);

                MailHandler.SendMail(signInEmail,"email testing",authToken.getTokenValue());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }

    }


    public String sigOutUser(String email) {

        User user = userRepo.findFirstByUserEmail(email);
        Authenticationtoken token = authenticationService.findFirstByUser(user);
        authenticationService.removeToken(token);
        return "User Signed out successfully";
    }

    public String neworder(String email, OrderEntity orderEntity) {
        User user = userRepo.findFirstByUserEmail(email);
        Authenticationtoken token = authenticationService.findFirstByUser(user);
        if(token!= null){
            iorderrepo.save(orderEntity);
            return  "order sucessfull";
        }
        else {
            return "please login";
        }
    }
}