package com.revature.iers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.iers.dtos.requests.UpdateUserRequest;
import com.revature.iers.dtos.responses.Principal;
import com.revature.iers.models.User;
import com.revature.iers.services.TokenService;
import com.revature.iers.services.UserService;
import com.revature.iers.utils.custom_exceptions.InvalidRequestException;
import com.revature.iers.utils.custom_exceptions.ResourceConflictException;
import jdk.nashorn.internal.parser.Token;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final UserService userService;

    public AdminServlet(ObjectMapper mapper, TokenService tokenService, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            /* New user request from Postman */
            /* mapper obj convert JSON request and store into a UpdateUserRequest.class */
            String token = req.getHeader("Authorization");
            Principal principal = tokenService.extractRequesterDetails(token);
            UpdateUserRequest updateUserRequest = mapper.readValue(req.getInputStream(), UpdateUserRequest.class);

            String[] path = req.getRequestURI().split("/");

            if (principal.getRole().equals("1")) { //admin
                if (path[3].equals("is_active")) {
                    User updatedUser = userService.updateUserActive(updateUserRequest);

                    resp.setStatus(200); // CREATED
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString(updatedUser.getUsername()));
                    resp.getWriter().write(mapper.writeValueAsString(updatedUser.getIs_active()));
                } else if (path[3].equals("password")) {
                    User updatedUser = userService.updateUserPassword(updateUserRequest);

                    resp.setStatus(200); // CREATED
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString(updatedUser.getUsername()));
                    resp.getWriter().write(mapper.writeValueAsString(updatedUser.getPassword()));
                } else {
                    System.out.println("NO");
                }
            } else {
                resp.setStatus(403); //FORBIDDEN
            }
        } catch (InvalidRequestException e) {
            resp.setStatus(404); // BAD REQUEST
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
        }
    }
}

