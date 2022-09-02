package com.revature.iers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.iers.dtos.requests.NewReimbursementRequest;
import com.revature.iers.models.Reimbursement;
import com.revature.iers.services.ReimbursementService;
import com.revature.iers.utils.custom_exceptions.InvalidRequestException;
import com.revature.iers.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ReimbursementServlet extends HttpServlet {
    private final ObjectMapper mapper;

    private final ReimbursementService reimbursementService;

    public ReimbursementServlet(ObjectMapper mapper, ReimbursementService reimbursementService) {
        this.mapper = mapper;
        this.reimbursementService = reimbursementService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            /* New user request from Postman */
            /* mapper obj convert JSON request and store into into a NewUserRequest.class */
            NewReimbursementRequest request = mapper.readValue(req.getInputStream(), NewReimbursementRequest.class);

            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("request")) {
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
}
