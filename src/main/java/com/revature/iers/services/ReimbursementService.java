package com.revature.iers.services;

import com.revature.iers.daos.ReimbursementDAO;

import com.revature.iers.dtos.requests.NewReimbursementRequest;
import com.revature.iers.dtos.requests.ReimbursementRequest;
import com.revature.iers.models.Reimbursement;


import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;

import java.util.List;
import java.util.UUID;
import java.sql.Timestamp;


public class ReimbursementService {
 private final ReimbursementDAO reimbursementDAO;


    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;

    }

    public Reimbursement updateReimbursementStatus(ReimbursementRequest reimbursementRequest){
        Reimbursement reimbursement = null;
        Timestamp ts = Timestamp.from(Instant.now());
            reimbursement = new Reimbursement(reimbursementRequest.getStatus_id(), ts, reimbursementRequest.getResolver_id(), reimbursementRequest.getReimb_id());
            reimbursementDAO.updateReimbursementStatus(reimbursement);

        return reimbursement;
    }

    public Reimbursement updateReimbursement(ReimbursementRequest reimbursementRequest){
        Reimbursement reimbursement = null;

        reimbursement = new Reimbursement(reimbursementRequest.getAmount(), reimbursementRequest.getDescription(), reimbursementRequest.getReceipt(), reimbursementRequest.getPayment_id(), reimbursementRequest.getType_id(), reimbursementRequest.getReimb_id());
        reimbursementDAO.updateReimbursement(reimbursement);

        return reimbursement;
    }
    public Reimbursement reimbursementRequest(NewReimbursementRequest request) throws SQLException {
        Reimbursement reimbursement = null;

        Timestamp ts = Timestamp.from(Instant.now());
//        int blobLength = (int) request.getReceipt().length();
//        byte[] blobAsBytes = request.getReceipt().getBytes(1, blobLength);
//        leaving if we ever can come back to figure out the blob


        reimbursement = new Reimbursement(UUID.randomUUID().toString(), request.getAmount(), ts, null, request.getDescription(), null, null, request.getAuthor_id(), null, "123", request.getType_id());
        try {
            reimbursementDAO.save(reimbursement);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return reimbursement;
    }

    public List<Reimbursement> listReimb(){
       return reimbursementDAO.getAll();
    }

    public List<Reimbursement> listReimbByAuthor(String reimb){
        return reimbursementDAO.getAllByAuthor(reimb);
    }
    public List<Reimbursement> listReimbByType(String reimb){
        return reimbursementDAO.getAllByType(reimb);
    }
    public List<Reimbursement> listReimbByStatus(String reimb){
        return reimbursementDAO.getAllByStatus(reimb);
    }

    public Reimbursement getByReimbId(String reimb) {
        return reimbursementDAO.getByRequestId(reimb);
    }
}
