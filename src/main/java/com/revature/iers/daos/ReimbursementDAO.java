package com.revature.iers.daos;

import com.revature.iers.models.Reimbursement;
import com.revature.iers.models.User;
import com.revature.iers.utils.custom_exceptions.InvalidSQLException;
import com.revature.iers.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO implements CrudDAO<Reimbursement> {
    @Override
    public void save(Reimbursement obj) throws IOException {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("INSERT INTO ers_reimbursements (reimb_id, amount, submitted, resolved, description, receipt, " +
                    "payment_id, author_id, resolver_id, status_id, type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getReimb_id());
            ps.setDouble(2, obj.getAmount());
            ps.setTimestamp(3, obj.getSubmitted());
            ps.setTimestamp(4, obj.getResolved());
            ps.setString(5, obj.getDescription());
            ps.setString(6, obj.getReceipt());
            ps.setString(7, obj.getPayment_id());
            ps.setString(8, obj.getAuthor_id());
            ps.setString(9, obj.getResolver_id());
            ps.setString(10, obj.getStatus_id());
            ps.setString(11, obj.getType_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Reimbursement obj) {

    }
    public void updateReimbursement(Reimbursement object){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE ers_reimbursements SET amount = ?, description = ?, receipt =?, payment_id =?, type_id =? WHERE reimb_id = ? AND status_id = ?");
            ps.setDouble(1, object.getAmount());
            ps.setString(2,object.getDescription());
            ps.setString(3, object.getReceipt());
            ps.setString(4, object.getPayment_id());
            ps.setString(5, object.getType_id());
            ps.setString(6, object.getReimb_id());
            ps.setString(7, object.getStatus_id());
            ps.executeUpdate();
        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }
    }

    public void updateReimbursementStatus(Reimbursement object){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE ers_reimbursements SET status_id = ?, resolved = ?, resolver_id =? WHERE reimb_id = ?");
            ps.setString(1, object.getStatus_id());
            ps.setTimestamp(2,object.getResolved());
            ps.setString(3, object.getResolver_id());
            ps.setString(4, object.getReimb_id());
            ps.executeUpdate();
        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Reimbursement getById(String id) {
        return null;
    }


    public Reimbursement getByRequestId(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE reimb_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Reimbursement(rs.getString("reimb_id"), rs.getDouble("amount"), rs.getTimestamp("submitted"),
                        rs.getTimestamp("resolved"), rs.getString("description"), rs.getString("receipt"),
                        rs.getString("payment_id"), rs.getString("author_id"), rs.getString("resolver_id"),
                        rs.getString("status_id"), rs.getString("type_id"));
            }

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
        return null;
    }

    @Override
    public List<Reimbursement> getAll() {
        List<Reimbursement> listReimb = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimbursement= new Reimbursement(rs.getString("reimb_id"), rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"),
                        rs.getString("receipt"), rs.getString("payment_id"), rs.getString("author_id"),
                        rs.getString("resolver_id"), rs.getString("status_id"), rs.getString("type_id"));
                listReimb.add(reimbursement);
            }

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }

        return listReimb;
    }
    public List<Reimbursement> getAllByAuthor(String reimb) {
        List<Reimbursement> listReimb = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE author_id = ?");
            ps.setString(1, reimb);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimbursement= new Reimbursement(rs.getString("reimb_id"), rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"),
                        rs.getString("receipt"), rs.getString("payment_id"), rs.getString("author_id"),
                        rs.getString("resolver_id"), rs.getString("status_id"), rs.getString("type_id"));
                listReimb.add(reimbursement);
            }

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }

        return listReimb;
    }

    public List<Reimbursement> getAllByType(String reimb) {
        List<Reimbursement> listReimb = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE type_id = ?");
            ps.setString(1, reimb);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimbursement= new Reimbursement(rs.getString("reimb_id"), rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"),
                        rs.getString("receipt"), rs.getString("payment_id"), rs.getString("author_id"),
                        rs.getString("resolver_id"), rs.getString("status_id"), rs.getString("type_id"));
                listReimb.add(reimbursement);
            }

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }

        return listReimb;

    }

    public List<Reimbursement> getAllByStatus(String reimb) {
        List<Reimbursement> listReimb = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE status_id = ?");
            ps.setString(1, reimb);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimbursement= new Reimbursement(rs.getString("reimb_id"), rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"),
                        rs.getString("receipt"), rs.getString("payment_id"), rs.getString("author_id"),
                        rs.getString("resolver_id"), rs.getString("status_id"), rs.getString("type_id"));
                listReimb.add(reimbursement);
            }

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }

        return listReimb;

    }
    public List<Reimbursement> getAllByStatusPending(String reimb) {
        List<Reimbursement> listReimb = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE status_id = ?");
            ps.setString(1, reimb);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimbursement= new Reimbursement(rs.getString("reimb_id"), rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"),
                        rs.getString("receipt"), rs.getString("payment_id"), rs.getString("author_id"),
                        rs.getString("resolver_id"), rs.getString("status_id"), rs.getString("type_id"));
                listReimb.add(reimbursement);
            }

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }

        return listReimb;
    }

    public List<Reimbursement> getAllByStatusHistory(String reimb) {
        List<Reimbursement> listReimb = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE NOT status_id = ?");
            ps.setString(1, reimb);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimbursement= new Reimbursement(rs.getString("reimb_id"), rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"),
                        rs.getString("receipt"), rs.getString("payment_id"), rs.getString("author_id"),
                        rs.getString("resolver_id"), rs.getString("status_id"), rs.getString("type_id"));
                listReimb.add(reimbursement);
            }

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }

        return listReimb;
    }

    public List<Reimbursement> getAllByStatusAuthorHistory(String reimb) {
        List<Reimbursement> listReimb = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE NOT status_id = ? AND status_id = ?");
            ps.setString(1, "123");
            ps.setString(2, reimb);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimbursement= new Reimbursement(rs.getString("reimb_id"), rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"),
                        rs.getString("receipt"), rs.getString("payment_id"), rs.getString("author_id"),
                        rs.getString("resolver_id"), rs.getString("status_id"), rs.getString("type_id"));
                listReimb.add(reimbursement);
            }

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }

        return listReimb;
    }

    public List<Reimbursement> getAllByStatusAuthorPending(String reimb) {
        List<Reimbursement> listReimb = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE author_id = ? AND status_id = ?");
            ps.setString(1, reimb);
            ps.setString(2, "123");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimbursement= new Reimbursement(rs.getString("reimb_id"), rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"),
                        rs.getString("receipt"), rs.getString("payment_id"), rs.getString("author_id"),
                        rs.getString("resolver_id"), rs.getString("status_id"), rs.getString("type_id"));
                listReimb.add(reimbursement);
            }

        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }

        return listReimb;
    }
}
