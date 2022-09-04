package com.revature.iers.services;

import com.revature.iers.daos.ReimbursementDAO;

import com.revature.iers.dtos.requests.NewReimbursementRequest;
import com.revature.iers.dtos.requests.ReimbursementRequest;
import com.revature.iers.dtos.responses.Principal;
import com.revature.iers.models.Reimbursement;
import com.revature.iers.utils.custom_exceptions.ResourceConflictException;


import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.sql.Timestamp;


public class ReimbursementService {
 private final ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }
    public Reimbursement reimbursementRequest(NewReimbursementRequest request) throws SQLException {
        Reimbursement reimbursement = null;
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
//        int blobLength = (int) request.getReceipt().length();
//        byte[] blobAsBytes = request.getReceipt().getBytes(1, blobLength);
//        leaving if we ever can come back to figure out the blob


        reimbursement = new Reimbursement(UUID.randomUUID().toString(), request.getAmount(), ts, null, request.getDescription(), null, null, "d11290bc-d455-4a04-96a2-15978780b17e", null, "123", request.getType_id());
        try {
            reimbursementDAO.save(reimbursement);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return reimbursement;
    }
}
