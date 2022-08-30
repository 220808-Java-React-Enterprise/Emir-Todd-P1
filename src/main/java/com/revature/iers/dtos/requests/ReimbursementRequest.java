package com.revature.iers.dtos.requests;

import java.sql.Blob;
import java.sql.Timestamp;

public class ReimbursementRequest {
    private String reimb_id;
    private Number amount;
    private Timestamp submitted;
    private Timestamp resolved;
    //descriptions vary from these types: Lodging, Travel, Food, Other
    private String description;
    private Blob receipt;
    private String payment_id;
    //author is the employee?
    private String author_id;
    //resolver would be finance manager
    private String resolver_id;
    private String status_id;
    //Lodging, Travel, Food, Other=types
    private String type_id;

    public ReimbursementRequest() {
    }

    //this constructor for finance manager use? bc they would want to see more information on a request
    public ReimbursementRequest(String reimb_id, Number amount, Timestamp submitted, Timestamp resolved, String description, Blob receipt, String payment_id, String author_id, String resolver_id, String status_id, String type_id) {
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
    //perhaps just for an employee to see their submissions.
    public ReimbursementRequest(Number amount, Timestamp submitted, Timestamp resolved, String description, String status_id) {
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.status_id = status_id;
    }

    public String getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(String reimb_id) {
        this.reimb_id = reimb_id;
    }

    public Number getAmount() {
        return amount;
    }

    public void setAmount(Number amount) {
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
        return "ReimbursementRequest{" +
                "reimb_id='" + reimb_id + '\'' +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", receipt=" + receipt +
                ", payment_id='" + payment_id + '\'' +
                ", author_id='" + author_id + '\'' +
                ", resolver_id='" + resolver_id + '\'' +
                ", status_id='" + status_id + '\'' +
                ", type_id='" + type_id + '\'' +
                '}';
    }
}
