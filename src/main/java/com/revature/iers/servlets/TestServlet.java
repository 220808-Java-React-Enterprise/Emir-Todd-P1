package com.revature.iers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.iers.dtos.requests.LoginRequest;
import com.revature.iers.dtos.responses.Principal;
import com.revature.iers.services.TokenService;
import com.revature.iers.services.UserService;
import com.revature.iers.utils.custom_exceptions.AuthenticationException;
import com.revature.iers.utils.custom_exceptions.InvalidRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class TestServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final UserService userService;

    public TestServlet(ObjectMapper mapper, TokenService tokenService, UserService userService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.userService = userService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("<h1>Hello World!</h1>");
        try {

            String token = req.getHeader("Authorization");

            Principal principal = tokenService.extractRequesterDetails(token);

            resp.getWriter().write(mapper.writeValueAsString(principal));

        } catch (InvalidRequestException e){
            resp.setStatus(404);
        }
    }




}
