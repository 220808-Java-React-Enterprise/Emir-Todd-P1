package com.revature.iers.dtos.requests;

import java.sql.Blob;
import java.sql.Timestamp;

public class NewReimbursementRequest {
    private Number amount;
    private String description;
    private Blob receipt;
    private String payment_id;
    private String author_id;
    //maybe not timestamp tho? just bc you could argue it's not tracked by the employee
    private Timestamp submitted;

    public NewReimbursementRequest() {
    }

    public NewReimbursementRequest(Number amount, String description, Blob receipt, String payment_id, String author_id, Timestamp submitted) {
        this.amount = amount;
        this.description = description;
        this.receipt = receipt;
        this.payment_id = payment_id;
        this.author_id = author_id;
        this.submitted = submitted;
    }

    public Number getAmount() {
        return amount;
    }

    public void setAmount(Number amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getReceipt() {
        return receipt;
    }

    public void setReceipt(Blob receipt) {
        this.receipt = receipt;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    @Override
    public String toString() {
        return "NewReimbursementRequest{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", receipt=" + receipt +
                ", payment_id='" + payment_id + '\'' +
                ", author_id='" + author_id + '\'' +
                ", submitted=" + submitted +
                '}';
    }
}
