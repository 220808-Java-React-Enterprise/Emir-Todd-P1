package com.revature.iers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.iers.dtos.requests.NewReimbursementRequest;
import com.revature.iers.dtos.requests.ReimbursementRequest;
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ReimbursementRequest request = mapper.readValue(req.getInputStream(), ReimbursementRequest.class);
            String token = req.getHeader("Authorization");
            Principal principal = tokenService.extractRequesterDetails(token);


            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("status_update")) {
                if (principal.getRole_id().equals("2")) {
                    request.setResolver_id(principal.getId());
                    Reimbursement createdRequest = reimbursementService.updateReimbursementStatus(request);

                    resp.setStatus(200); // CREATED
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString(createdRequest.getReimb_id()));
                    resp.getWriter().write(mapper.writeValueAsString(createdRequest.getStatus_id()));
                }  else {
                    resp.setStatus(403); //FORBIDDEN
                }
            } else if (path[3].equals("update_reimb")) {
                if (principal.getRole_id().equals("3")) {
                    Reimbursement updateRequest = reimbursementService.updateReimbursement(request);

                    resp.setContentType("application/json");
                    if(updateRequest.getStatus_id().equals("123")){
                        resp.setStatus(200); // CREATE
                        resp.getWriter().write(mapper.writeValueAsString(updateRequest));
                    }else{
                        resp.setStatus(404);
                        resp.getWriter().write("Reimbursement is not pending and cannot be changed");
                    }

                }
            } else {
                System.out.println("NO");
            }
        } catch (InvalidRequestException e) {
            resp.setStatus(404); // BAD REQUEST
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("Authorization");
        Principal principal = tokenService.extractRequesterDetails(token);

        try {
            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("list")) {
                if (principal.getRole_id().equals("2")) { // FINANCIAL MANAGER
                    resp.setContentType("application/json");

                    List<Reimbursement> listReimb = reimbursementService.listReimb();
                    for (int i = 0; i < listReimb.size(); i++) {
                        resp.getWriter().write(mapper.writeValueAsString(listReimb.get(i)));
                    }
                } else if (principal.getRole_id().equals("3")) { // EMPLOYEE
                    resp.setContentType("application/json");

                    List<Reimbursement> listReimb = reimbursementService.listReimbByAuthor(principal.getId());
                    for (int i = 0; i < listReimb.size(); i++) {
                        resp.getWriter().write(mapper.writeValueAsString(listReimb.get(i)));
                    }
                }
            } else if (path[3].equals("list_history")) {
                if (principal.getRole_id().equals("2")) { // FINANCIAL MANAGER
                    resp.setContentType("application/json");

                    List<Reimbursement> listReimb = reimbursementService.listReimbByHistory();
                    for (int i = 0; i < listReimb.size(); i++) {
                        resp.getWriter().write(mapper.writeValueAsString(listReimb.get(i)));
                    }
                } else if (principal.getRole_id().equals("3")) { // EMPLOYEE
                    resp.setContentType("application/json");

                    List<Reimbursement> listReimb = reimbursementService.listReimbByAuthorHistory(principal.getId());
                    for (int i = 0; i < listReimb.size(); i++) {
                        resp.getWriter().write(mapper.writeValueAsString(listReimb.get(i)));
                    }
                }
            }else if (path[3].equals("list_pending")) {
                if (principal.getRole_id().equals("2")) { // FINANCIAL MANAGER
                    resp.setContentType("application/json");

                    List<Reimbursement> listReimb = reimbursementService.listReimbByPending();
                    for (int i = 0; i < listReimb.size(); i++) {
                        resp.getWriter().write(mapper.writeValueAsString(listReimb.get(i)));
                    }
                } else if (principal.getRole_id().equals("3")) { // EMPLOYEE
                    resp.setContentType("application/json");

                    List<Reimbursement> listReimb = reimbursementService.listReimbByAuthorPending(principal.getId());
                    for (int i = 0; i < listReimb.size(); i++) {
                        resp.getWriter().write(mapper.writeValueAsString(listReimb.get(i)));
                    }
                }
            }else {
                resp.setStatus(403); // FORBIDDEN
            }
        } catch (NullPointerException e) {
            resp.setStatus(401); // UNAUTHORIZED
        } catch (InvalidRequestException e) {
            resp.setStatus(404);
        }
    }
}
