package com.revature.IERS.services;

import com.revature.IERS.daos.ReimbursementDAO;

public class ReimbursementService {
 private final ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }
}
