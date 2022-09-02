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
import java.util.List;

public class ReimbursementDAO implements CrudDAO<Reimbursement> {
    @Override
    public void save(Reimbursement obj) throws IOException {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
//            int blobLength = (int) obj.getReceipt().length();
//            byte[] blobAsBytes = obj.getReceipt().getBytes(1, blobLength);
            //leaving if we figure out the blob conversion

            PreparedStatement ps = con.prepareStatement("INSERT INTO ers_reimbursements (reimb_id, amount, submitted, resolved, description, receipt, payment_id, author_id, resolver_id, status_id, type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getReimb_id());
            ps.setDouble(2, obj.getAmount());
            ps.setTimestamp(3, obj.getSubmitted());
            ps.setTimestamp(4, obj.getResolved());
            ps.setString(5, obj.getDescription());
            ps.setByte(6, obj.getReceipt());
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
                return new Reimbursement(rs.getString("reimb_id"), rs.getDouble("amount"), rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"), rs.getByte("receipt"), rs.getString("payment_id"), rs.getString("author_id"), rs.getString("resolver_id"), rs.getString("status_id"), rs.getString("type_id"));
            }

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
        return null;
    }

    @Override
    public List<Reimbursement> getAll() {
        return null;
    }
}
