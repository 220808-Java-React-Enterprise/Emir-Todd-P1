package com.revature.iers.services;

import com.revature.iers.daos.ReimbursementDAO;

import com.revature.iers.dtos.requests.NewReimbursementRequest;
import com.revature.iers.dtos.requests.ReimbursementRequest;
import com.revature.iers.models.Reimbursement;
import com.revature.iers.models.User;
import com.revature.iers.utils.custom_exceptions.InvalidRequestException;


import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.sql.Timestamp;


public class ReimbursementService {
    private final ReimbursementDAO reimbursementDAO;


    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;

    }

    public Reimbursement updateReimbursementStatus(ReimbursementRequest reimbursementRequest) {
        //for the finance manager
        Reimbursement reimbursement = null;
        Timestamp ts = Timestamp.from(Instant.now());

        if (isValidStatus(reimbursementRequest.getStatus_id())) {
            reimbursement = new Reimbursement(reimbursementRequest.getStatus_id(), ts, reimbursementRequest.getResolver_id(), reimbursementRequest.getReimb_id());
            reimbursementDAO.updateReimbursementStatus(reimbursement);
        }
        return reimbursement;
    }

    public Reimbursement updateReimbursement(ReimbursementRequest reimbursementRequest) {
        //for employee
        Reimbursement reimbursement = null;

        if (isNumeric(reimbursementRequest.getAmount())) {
            if (isValidType(reimbursementRequest.getType_id())) {
                if (isTooManyCharacters(reimbursementRequest.getDescription())) {
                    if (isTooManyCharacters(reimbursementRequest.getPayment_id())) {
                        reimbursement = new Reimbursement(reimbursementRequest.getAmount(), reimbursementRequest.getDescription(), reimbursementRequest.getReceipt(), reimbursementRequest.getPayment_id(), reimbursementRequest.getType_id(), reimbursementRequest.getReimb_id(), "123");
                        reimbursementDAO.updateReimbursement(reimbursement);
                    }
                }
            }
        }

        return reimbursement;
    }

    public Reimbursement reimbursementRequest(NewReimbursementRequest request) throws SQLException {
        Reimbursement reimbursement = null;
        Timestamp ts = Timestamp.from(Instant.now());

        if (isNumeric(request.getAmount())) {
            if (isValidType(request.getType_id())) {
                if (isTooManyCharacters(request.getDescription())) {
                    if (isTooManyCharacters(request.getPayment_id())) {

                        reimbursement = new Reimbursement(UUID.randomUUID().toString(), request.getAmount(), ts, null, request.getDescription(), null, request.getPayment_id(), request.getAuthor_id(), null, "123", request.getType_id());

                        try {
                            reimbursementDAO.save(reimbursement);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        return reimbursement;

    }

    public List<Reimbursement> listReimbByPending() {
        return reimbursementDAO.getAllByStatusPending("123");
    }

    public List<Reimbursement> listReimbByAuthorPending(String reimb) {
        return reimbursementDAO.getAllByStatusAuthorPending(reimb);
    }

    public List<Reimbursement> listReimbByHistory() {
        return reimbursementDAO.getAllByStatusHistory("123");
    }

    public List<Reimbursement> listReimbByAuthorHistory(String reimb) {
        return reimbursementDAO.getAllByStatusAuthorHistory(reimb);
    }

    public List<Reimbursement> listReimb() {
        return reimbursementDAO.getAll();
    }

    public List<Reimbursement> listReimbByAuthor(String reimb) {
        return reimbursementDAO.getAllByAuthor(reimb);
    }

    public List<Reimbursement> listReimbByType(String reimb) {
        return reimbursementDAO.getAllByType(reimb);
    }

    public List<Reimbursement> listReimbByStatus(String reimb) {
        return reimbursementDAO.getAllByStatus(reimb);
    }

    public Reimbursement getByReimbId(String reimb) {
        return reimbursementDAO.getByRequestId(reimb);
    }


    public boolean isValidStatus(String status) {
        if (!status.equals("456") && !status.equals("789"))
            throw new InvalidRequestException("Invalid status selection! Please choose 456 for Approved or 789 for Disapproved");
        return true;
    }

    public boolean isValidType(String type) {
        if (!type.equals("000") && !type.equals("001") && !type.equals("002") && !type.equals("003"))
            throw new InvalidRequestException("Invalid type selection! Please choose 000 for food, 001 for travel, 002 for lodging, or 003 for other!");
        return true;
    }

    public boolean isNumeric(Double amount) {
        String amounts = amount.toString();
        if (!amounts.matches("^(\\d{1,5}|\\d{0,5}\\.\\d{1,2})$"))
            throw new InvalidRequestException("Invalid Entry, please use numbers in this format '00.00' ");
        return true;
    }

    public boolean isTooManyCharacters(String amount) {
        if (!amount.matches("^.{1,50}$")) throw new InvalidRequestException("Too many characters! Please limit to 40!");
        return true;
    }

}
