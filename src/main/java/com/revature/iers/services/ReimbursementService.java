package com.revature.iers.services;

import com.revature.iers.daos.ReimbursementDAO;

public class ReimbursementService {
 private final ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }
}
