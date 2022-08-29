package com.revature.IERS.services;

import com.revature.IERS.daos.UserDAO;
import com.revature.IERS.dtos.requests.LoginRequest;
import com.revature.IERS.dtos.requests.NewUserRequest;
import com.revature.IERS.dtos.responses.Principal;
import com.revature.IERS.models.User;
import com.revature.IERS.utils.custom_exceptions.AuthenticationException;
import com.revature.IERS.utils.custom_exceptions.InvalidRequestException;
import com.revature.IERS.utils.custom_exceptions.ResourceConflictException;

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
                        user = new User(UUID.randomUUID().toString(), request.getUsername(), request.getPassword());
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

    public boolean isSamePassword(String password, String password2) {
        if (!password.equals(password2)) throw new InvalidRequestException("\nPassword do not match :(");
        return true;
    }
}
