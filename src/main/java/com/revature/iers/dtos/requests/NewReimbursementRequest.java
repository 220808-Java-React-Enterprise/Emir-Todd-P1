package com.revature.iers.dtos.requests;


import com.revature.iers.dtos.responses.Principal;

import java.sql.Blob;

public class NewReimbursementRequest {
    private double amount;
    private String description;
    private String receipt;
    private String payment_id;
    private String author_id;

    private String type_id;

    public NewReimbursementRequest() {
    }

    public NewReimbursementRequest(double amount, String description, String receipt, String payment_id, String author_id, String type_id) {
        this.amount = amount;
        this.description = description;
        this.receipt = receipt;
        this.payment_id = payment_id;
        this.author_id = author_id;
        this.type_id = type_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
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

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "NewReimbursementRequest{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", receipt='" + receipt + '\'' +
                ", payment_id='" + payment_id + '\'' +
                ", author_id='" + author_id + '\'' +
                ", type_id='" + type_id + '\'' +
                '}';
    }
}
