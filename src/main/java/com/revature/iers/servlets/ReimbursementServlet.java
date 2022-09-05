package com.revature.iers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.iers.dtos.requests.NewReimbursementRequest;
import com.revature.iers.dtos.responses.Principal;
import com.revature.iers.models.Reimbursement;
import com.revature.iers.services.ReimbursementService;
import com.revature.iers.services.TokenService;
import com.revature.iers.utils.custom_exceptions.InvalidRequestException;
import com.revature.iers.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {
    private final ObjectMapper mapper;

    private final TokenService tokenService;
    private final ReimbursementService reimbursementService;


    public ReimbursementServlet(ObjectMapper mapper, TokenService tokenService, ReimbursementService reimbursementService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.reimbursementService = reimbursementService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            /* New user request from Postman */
            /* mapper obj convert JSON request and store into into a NewUserRequest.class */
            NewReimbursementRequest request = mapper.readValue(req.getInputStream(), NewReimbursementRequest.class);
            String token = req.getHeader("Authorization");
            Principal principal = tokenService.extractRequesterDetails(token);


            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("request")) {
                request.setAuthor_id(principal.getId());
//                resp.getWriter().write(mapper.writeValueAsString(request));
                Reimbursement createdRequest = reimbursementService.reimbursementRequest(request);

                resp.setStatus(200); // CREATED
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(createdRequest.getReimb_id()));
            } else {
                System.out.println("NO");
            }
        } catch (InvalidRequestException e) {
            resp.setStatus(404); // BAD REQUEST
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
        } catch (SQLException e) {
//            resp.setStatus(500); //server issue?
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("Authorization");
        Principal principal = tokenService.extractRequesterDetails(token);

        try {
            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("list")) {

                if (principal.getRole_id().equals("2")) {
//                String username = req.getParameter("username");

                    resp.setContentType("application/json");

//                    List<User> userList = userService.getAllUsers();
//                    resp.getWriter().write(mapper.writeValueAsString(userList));
                    List<Reimbursement> listReimb = reimbursementService.listReimb();
                    for (int i = 0; i < listReimb.size(); i++) {
                        resp.getWriter().write(mapper.writeValueAsString(listReimb.get(i)));
                    }
                }
            } else {
                resp.setStatus(403); // FORBIDDEN
            }
        } catch (NullPointerException e) {
            resp.setStatus(401); // UNAUTHORIZED
        } catch (InvalidRequestException e) {
            resp.setStatus(404);
        }
    }
}
