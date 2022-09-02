package com.revature.iers.services;

import com.revature.iers.daos.ReimbursementDAO;

import com.revature.iers.dtos.requests.NewReimbursementRequest;
import com.revature.iers.dtos.requests.ReimbursementRequest;
import com.revature.iers.models.Reimbursement;
import com.revature.iers.utils.custom_exceptions.ResourceConflictException;


import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.UUID;



public class ReimbursementService {
 private final ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }
    public Reimbursement reimbursementRequest(NewReimbursementRequest request) throws SQLException {
        Reimbursement reimbursement = null;

//        int blobLength = (int) request.getReceipt().length();
//        byte[] blobAsBytes = request.getReceipt().getBytes(1, blobLength);
//        leaving if we ever can come back to figure out the blob


        reimbursement = new Reimbursement(UUID.randomUUID().toString(), request.getAmount(), request.getDescription(), request.getReceipt(), request.getPayment_id(), request.getAuthor_id(), request.getType_id());
        try {
            reimbursementDAO.save(reimbursement);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return reimbursement;
    }
}
