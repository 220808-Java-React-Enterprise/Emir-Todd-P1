package com.revature.iers.services;

import com.revature.iers.daos.UserDAO;
import com.revature.iers.dtos.requests.*;
import com.revature.iers.dtos.responses.Principal;
import com.revature.iers.models.User;
import com.revature.iers.utils.custom_exceptions.AuthenticationException;
import com.revature.iers.utils.custom_exceptions.InvalidRequestException;
import com.revature.iers.utils.custom_exceptions.ResourceConflictException;

import java.util.UUID;

public class UserService {

    private final UserDAO userDAO;
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User register(NewUserRequest request) {
        User user = null;

        if (isValidUsername(request.getUsername())) {
            if (!isDuplicateUsername(request.getUsername())) {
                if (isValidPassword(request.getPassword())) {
                    if (isSamePassword(request.getPassword(), request.getPasswordConfirm())) {
                        if(isValidEmail(request.getEmail())){
                            if(isValidName(request.getSurName())){
                                if(isValidName(request.getGiven_name())){
                                    user = new User(UUID.randomUUID().toString(), request.getUsername(), request.getEmail(), EncryptionService.encryption(request.getPassword()), request.getGiven_name(), request.getSurName(), false, "3");
                                    userDAO.save(user);
                                }
                            }
                        }
                    }
                }
            }
        }

        return user;
    }

    public Principal login(LoginRequest request) {
        User user = userDAO.getUserByUsernameAndPassword(request.getUsername(), EncryptionService.encryption(request.getPassword()));
        if (!isActiveUser(user.getIs_active())) throw new AuthenticationException("\nUser is not active!");
        if (user == null) throw new AuthenticationException("\nIncorrect username or password :(");
        return new Principal(user.getId(), user.getUsername(), user.getRole_id(), user.getIs_active());

    }

    public User updateUserActive(UpdateUserRequest updateUserRequest){
        User user = null;

        if (isValidUsername(updateUserRequest.getUsername())) {
            user = new User(updateUserRequest.getUsername(), updateUserRequest.isIs_active());
            userDAO.updateUserActive(user);
                    }

        return user;
    }

    public User updateUserPassword(UpdateUserRequest updateUserRequest){
        User user = null;

        if (isValidUsername(updateUserRequest.getUsername())) {
            user = new User(updateUserRequest.getUsername(), EncryptionService.encryption(updateUserRequest.getPassword()));
            userDAO.updateUserPassword(user);
        }

        return user;
    }

    public User updateUserRole(UpdateUserRequest updateUserRequest){
        User user = null;

        if (isValidUsername(updateUserRequest.getUsername())) {
            user = new User(updateUserRequest.getUsername(), EncryptionService.encryption(updateUserRequest.getPassword()), updateUserRequest.getRole_id());
            userDAO.updateUserRole(user);
        }

        return user;
    }

    public User getUserById(String id) {
        return userDAO.getById(id);
    }

    public void deleteUserByUsername(UpdateUserRequest request){
        userDAO.delete(request.getUsername());
    }

//    public User getUserByUsername(String username){
//        return userDAO.getUsername(username);
//    }

    public boolean isValidUsername(String username) {
        if (!username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) throw new InvalidRequestException("\nInvalid username! username is 8-20 characters long. no _ or . at the beginning. no __ or _. or ._ or .. inside");
        return true;
    }

    public boolean isValidPassword(String password) {
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) throw new InvalidRequestException("\nInvalid password! Minimum eight characters, at least one letter and one number");
        return true;
    }

    public boolean isDuplicateUsername(String username) {
        if (userDAO.getUsername(username) != null) throw new ResourceConflictException("\nSorry, " + username + " already been taken :(");
        return false;
    }

    public boolean isSamePassword(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) throw new InvalidRequestException("\nPassword do not match :(");
        return true;
    }

    public boolean isValidEmail(String email) {
        if (!email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) throw new InvalidRequestException("\nInvalid email entry!");
        return true;
    }

    public boolean isValidName(String name) {
        if (!name.matches("^[a-zA-Z ]*$")) throw new InvalidRequestException("\nInvalid name entry!");
        return true;
    }
    public boolean isActiveUser(Boolean is_active) {
        if (!is_active) throw new InvalidRequestException("\nInvalid name entry!");
        return true;
    }


}
