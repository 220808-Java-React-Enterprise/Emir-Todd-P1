package com.revature.iers.models;


import java.sql.Timestamp;


public class Reimbursement {
    private String reimb_id;

    private double amount;

    private Timestamp submitted;

    private Timestamp resolved;

    private String description;

    private String receipt;

    private String payment_id;

    private String author_id;

    private String resolver_id;

    private String status_id;

    private String type_id;

    public Reimbursement() {
    }

    public Reimbursement(String reimb_id, double amount, Timestamp submitted, Timestamp resolved, String description, String receipt, String payment_id, String author_id, String resolver_id, String status_id, String type_id) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.payment_id = payment_id;
        this.author_id = author_id;
        this.resolver_id = resolver_id;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public Reimbursement(String reimb_id, double amount, Timestamp submitted, Timestamp resolved, String description, String receipt, String payment_id, String resolver_id, String status_id, String type_id) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.payment_id = payment_id;
        this.resolver_id = resolver_id;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public Reimbursement(String reimb_id) {
        this.reimb_id = reimb_id;
    }

    public Reimbursement(String status_id, Timestamp resolved, String resolver_id, String reimb_id) {
        this.status_id = status_id;
        this.resolved = resolved;
        this.resolver_id = resolver_id;
        this.reimb_id = reimb_id;
    }

    public String getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(String reimb_id) {
        this.reimb_id = reimb_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
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

    public String getResolver_id() {
        return resolver_id;
    }

    public void setResolver_id(String resolver_id) {
        this.resolver_id = resolver_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimb_id='" + reimb_id + '\'' +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", receipt='" + receipt + '\'' +
                ", payment_id='" + payment_id + '\'' +
                ", author_id='" + author_id + '\'' +
                ", resolver_id='" + resolver_id + '\'' +
                ", status_id='" + status_id + '\'' +
                ", type_id='" + type_id + '\'' +
                '}';
    }
}



