package com.revature.iers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.iers.dtos.requests.ReimbursementRequest;
import com.revature.iers.dtos.responses.Principal;
import com.revature.iers.models.Reimbursement;
import com.revature.iers.services.ReimbursementService;
import com.revature.iers.services.TokenService;
import com.revature.iers.utils.custom_exceptions.InvalidRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewReimbursementFilterServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final ReimbursementService reimbursementService;


    public ViewReimbursementFilterServlet(ObjectMapper mapper, TokenService tokenService, ReimbursementService reimbursementService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.reimbursementService = reimbursementService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReimbursementRequest request = mapper.readValue(req.getInputStream(), ReimbursementRequest.class);
        String token = req.getHeader("Authorization");
        Principal principal = tokenService.extractRequesterDetails(token);

        try{
            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("list_type")){
                if (principal.getRole_id().equals("2")) { // FINANCIAL MANAGER
                    resp.setContentType("application/json");

                    List<Reimbursement> listReimb = reimbursementService.listReimbByType(request.getType_id());
                    for (int i = 0; i < listReimb.size(); i++) {
                        resp.getWriter().write(mapper.writeValueAsString(listReimb.get(i)));
                    }
                }
            } else if (path[3].equals("list_status")){
                if (principal.getRole_id().equals("2")) { // FINANCIAL MANAGER
                    resp.setContentType("application/json");

                    List<Reimbursement> listReimb = reimbursementService.listReimbByStatus(request.getStatus_id());
                    for (int i = 0; i < listReimb.size(); i++) {
                        resp.getWriter().write(mapper.writeValueAsString(listReimb.get(i)));
                    }
                }
            } else if (path[3].equals("view_reimbursement")){
                if (principal.getRole_id().equals("2")) {
                    resp.setContentType("application/json");

                    Reimbursement reimbursement = reimbursementService.getByReimbId(request.getReimb_id());
                    resp.getWriter().write(mapper.writeValueAsString(reimbursement));
                }else if (principal.getId().equals(request.getAuthor_id())){
                    resp.setContentType("application/json");

                    Reimbursement reimbursement = reimbursementService.getByReimbId(request.getReimb_id());
                    resp.getWriter().write(mapper.writeValueAsString(reimbursement));
                }else{
                    resp.setStatus(403); // FORBIDDEN
                }
            }

        }catch (NullPointerException e) {
            resp.setStatus(401); // UNAUTHORIZED
        } catch (InvalidRequestException e) {
            resp.setStatus(404);
        }



    }
}