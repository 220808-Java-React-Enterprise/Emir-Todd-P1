package com.revature.iers.daos;

import com.revature.iers.models.User;
import com.revature.iers.utils.custom_exceptions.InvalidSQLException;
import com.revature.iers.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements CrudDAO<User> {
    @Override
    public void save(User obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO ers_users (user_id, username, email, password, given_name, surname, is_active, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getPassword());
            ps.setString(5, obj.getGiven_name());
            ps.setString(6, obj.getSurName());
            ps.setBoolean(7, obj.getIs_active());
            ps.setString(8, obj.getRole_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(User obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO ers_users (user_id, username, email, password, given_name, surname, is_active, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getPassword());
            ps.setString(5, obj.getGiven_name());
            ps.setString(6, obj.getSurName());
            ps.setBoolean(7, obj.getIs_active());
            ps.setString(8, obj.getRole_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public void updateUserActive(User object){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE ers_users SET is_active = ? WHERE username = ?");
            ps.setBoolean(1, object.getIs_active());
            ps.setString(2, object.getUsername());
            ps.executeUpdate();
        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }
    }
    public void updateUserPassword(User object){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE ers_users SET password = ? WHERE username = ?");
            ps.setString(1, object.getPassword());
            ps.setString(2, object.getUsername());
            ps.executeUpdate();
        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }
    }

    public void updateUserRole(User object){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE ers_users SET password = ?, role_id = ? WHERE username = ?");
            ps.setString(1, object.getPassword());
            ps.setString(2, object.getRole_id());
            ps.setString(3, object.getUsername());
            ps.executeUpdate();
        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }
    }
    @Override
    public void delete(String username) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM ers_users WHERE username = ?");
            ps.setString(1, username);
            ps.executeUpdate();
        }catch(SQLException e){
            throw new InvalidSQLException("Error occurred connecting to database");
        }
    }

    @Override
    public User getById(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_users WHERE user_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("user_id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("given_name"), rs.getString("surname"), rs.getBoolean("is_active"), rs.getString("role_id"));
            }

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public String getUsername(String username) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT (username) FROM ers_users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getString("username");

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_users WHERE username = ? and \"password\" = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return new User(rs.getString("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("role_id"), rs.getBoolean("is_active"));
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }

    public User getUserByUsername(String username) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return new User(rs.getString("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("role_id"));
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }
}
