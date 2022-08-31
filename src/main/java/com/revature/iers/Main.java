package com.revature.iers;

import com.revature.iers.utils.database.ConnectionFactory;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(ConnectionFactory.getInstance().getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
