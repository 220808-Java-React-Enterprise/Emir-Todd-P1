package com.revature.iers.services;

import com.revature.iers.daos.ReimbursementDAO;

import com.revature.iers.dtos.requests.NewReimbursementRequest;
import com.revature.iers.dtos.requests.ReimbursementRequest;
import com.revature.iers.models.Reimbursement;
import com.revature.iers.utils.custom_exceptions.ResourceConflictException;

import java.io.IOException;
import java.util.UUID;

public class ReimbursementService {
 private final ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }
    public Reimbursement reimbursementRequest(NewReimbursementRequest request) {
        Reimbursement reimbursement = null;

        reimbursement = new Reimbursement(UUID.randomUUID().toString(), request.getAmount(), request.getDescription(), request.getReceipt(), request.getPayment_id(), request.getAuthor_id(), request.getType_id());
        try {
            reimbursementDAO.save(reimbursement);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return reimbursement;
    }
}
