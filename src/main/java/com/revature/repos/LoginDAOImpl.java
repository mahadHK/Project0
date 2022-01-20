package com.revature.repos;

public class LoginDAOImpl implements LoginDAO{

    public boolean login(String username, String password){
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM LOGIN_INFORMATION WHERE USERNAME = ?;

            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery(sql);

            if(result.next()){
                if(result.getString("pass_word").equals(password)){
                    return true;
                }
                else {
                    return false;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
