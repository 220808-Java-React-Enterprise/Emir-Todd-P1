package com.revature.iers.services;

import com.revature.iers.daos.UserDAO;
import com.revature.iers.dtos.requests.LoginRequest;
import com.revature.iers.dtos.requests.NewUserRequest;
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
                        user = new User(UUID.randomUUID().toString(), request.getUsername(), request.getEmail(), request.getPassword(), request.getGiven_name(), request.getSurName(), false, "8756d6d6-289e-11ed-a261-0242ac120002");
                        userDAO.save(user);
                    }
                }
            }
        }

        return user;
    }

    public Principal login(LoginRequest request) {
        User user = userDAO.getUserByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user == null) throw new AuthenticationException("\nIncorrect username or password :(");
        return new Principal(user.getId(), user.getUsername(), user.getRole());
    }

    public User getUserById(String id) {
        return userDAO.getById(id);
    }

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
}