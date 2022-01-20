package com.revature.repos;

import com.revature.utils.ConnectionUtil;

import java.sql.*;

public class LoginDAOImpl implements LoginDAO {

    public boolean login(String username, String password) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM LOGIN_INFORMATION WHERE USERNAME = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                if (result.getString("PASSWORD").equals(password)) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
