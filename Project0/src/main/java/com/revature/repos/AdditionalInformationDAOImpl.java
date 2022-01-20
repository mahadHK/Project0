package com.revature.repos;

import com.revature.models.AdditionalInformation;
import com.revature.models.TransactionInformation;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdditionalInformationDAOImpl implements AdditionalInformationDAO{


    @Override
    public List<AdditionalInformation> findAll() {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM login_information login left join additional_information add on (login.username = add.username) left join account_info acc on (acc.username = login.username)";

            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            List<AdditionalInformation> list = new ArrayList<>();

            while(result.next()){
                AdditionalInformation additional_information = new AdditionalInformation();
                additional_information.setFirst_name(result.getString("first_name"));
                additional_information.setLast_name(result.getString("last_name"));
                additional_information.setStreet(result.getString("street"));
                additional_information.setCity(result.getString("city"));
                additional_information.setState(result.getString("state"));
                additional_information.setZip(result.getString("zip"));
                additional_information.setUsername(result.getString("username"));
                additional_information.setPassword("XXXXXXXXXXXXXX");
                additional_information.setEmail(result.getString("email"));
                additional_information.setActive(result.getBoolean("active"));
                additional_information.setApplication_status(result.getString("application_status"));
                additional_information.setAccess_levels(result.getString("access_level"));
                additional_information.setBank_balance(result.getInt("bank_balance"));
                list.add(additional_information);
            }

            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<AdditionalInformation>();
    }

    @Override
    public AdditionalInformation findByName(String username) {

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM login_information login left join additional_information add on (login.username = add.username) left join account_info acc on (acc.username = login.username) WHERE login.username = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            AdditionalInformation additional_information = new AdditionalInformation();

            if(result.next()){
                additional_information.setFirst_name(result.getString("first_name"));
                additional_information.setLast_name(result.getString("last_name"));
                additional_information.setStreet(result.getString("street"));
                additional_information.setCity(result.getString("city"));
                additional_information.setState(result.getString("state"));
                additional_information.setZip(result.getString("zip"));
                additional_information.setUsername(result.getString("username"));
                additional_information.setPassword("XXXXXXXXXXXXXX");
                additional_information.setEmail(result.getString("email"));
                additional_information.setActive(result.getBoolean("active"));
                additional_information.setApplication_status(result.getString("application_status"));
                additional_information.setAccess_levels(result.getString("access_level"));
                additional_information.setBank_balance(result.getInt("bank_balance"));
            }

            return additional_information;


        }catch (SQLException e){
            e.printStackTrace();
        }

        return new AdditionalInformation();

    }

    @Override
    public boolean updateAdditionalInformation(AdditionalInformation additional_information) {
        if(updateLoginInformation(additional_information)){
            if(updateInformation(additional_information)){
                return true;
            }
        }
     return false;
    }

    public boolean updateLoginInformation(AdditionalInformation additional_information) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "UPDATE LOGIN_INFORMATION SET PASSWORD = ?, ACTIVE = ?, APPLICATION_STATUS = ?, EMAIL = ? "+
            "WHERE USERNAME = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            int count = 0;
            statement.setString(++count, additional_information.getPassword());
            statement.setBoolean(++count, additional_information.getActive());
            statement.setString(++count, additional_information.getApplication_status());
            statement.setString(++count, additional_information.getEmail());
            statement.setString(++count, additional_information.getUsername());

            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateInformation(AdditionalInformation additional_information) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "UPDATE additional_information SET first_name = ?, last_name = ?, street = ?, city = ?, state = ?, zip = ?" +
                    "WHERE USERNAME = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            int count = 0;
            statement.setString(++count, additional_information.getFirst_name());
            statement.setString(++count, additional_information.getLast_name());
            statement.setString(++count, additional_information.getStreet());
            statement.setString(++count, additional_information.getCity());
            statement.setString(++count, additional_information.getState());
            statement.setString(++count, additional_information.getZip());
            statement.setString(++count, additional_information.getUsername());

            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    public boolean addAdditionalInformation(AdditionalInformation additional_information) {
        try(Connection connection = ConnectionUtil.getConnection()){

            if(addLoginInformation(additional_information)){
                addInformation(additional_information, connection);
            }

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean addLoginInformation(AdditionalInformation additional_information) {
        try( Connection connection = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO login_information (username, password, application_status, active,  email) VALUES (?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            int count = 0;
            statement.setString(++count, additional_information.getUsername());
            statement.setString(++count, additional_information.getPassword());
            statement.setString(++count, additional_information.getApplication_status());
            statement.setBoolean(++count, false);
            statement.setString(++count, additional_information.getEmail());

            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean addInformation(AdditionalInformation additional_information, Connection connection) {
        try(connection){

            String sql = "INSERT INTO additional_information (first_name, last_name, street, city, state, zip," +
                    " username) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            int count = 0;
            statement.setString(++count, additional_information.getFirst_name());
            statement.setString(++count, additional_information.getLast_name());
            statement.setString(++count, additional_information.getStreet());
            statement.setString(++count, additional_information.getCity());
            statement.setString(++count, additional_information.getState());
            statement.setString(++count, additional_information.getZip());
            statement.setString(++count, additional_information.getUsername());


            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean updateApplicationStatus(String application_status, boolean active, String username) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "UPDATE LOGIN_INFORMATION SET application_status = ?, active = ? WHERE USERNAME = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            int count = 0;
            statement.setString(++count, application_status);
            statement.setBoolean(++count, active);
            statement.setString(++count, username);

            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public boolean insertTransactionRequest(TransactionInformation transactionInformation){
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO transaction_details (username, transaction_id, transaction_type, amount, transaction_status) values (?,NEXTVAL('TRANS_ID_SEQ'),?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            int count = 0;
            statement.setString(++count, transactionInformation.getUsername());
            statement.setString(++count, transactionInformation.getTransaction_type());
            statement.setInt(++count, transactionInformation.getAmount());
            statement.setString(++count, "Waiting");

            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTransactionStatus(int transaction_id, String transaction_status) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "UPDATE TRANSACTION_DETAILS SET transaction_status = ? WHERE transaction_id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            int count = 0;
            statement.setString(++count, transaction_status);
            statement.setInt(++count, transaction_id);

            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<TransactionInformation> getTransactionInformation() {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM TRANSACTION_DETAILS";

            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery(sql);

            List<TransactionInformation> list = new ArrayList<>();

            while(result.next()){
                TransactionInformation transactionInformation = new TransactionInformation();
                transactionInformation.setUsername(result.getString("username"));
                transactionInformation.setTransaction_id(result.getInt("transaction_id"));
                transactionInformation.setTransaction_type(result.getString("transaction_type"));
                transactionInformation.setAmount(result.getInt("amount"));
                transactionInformation.setTransaction_status(result.getString("transaction_status"));
                list.add(transactionInformation);
            }

            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<TransactionInformation>();
    }

    @Override
    public List<TransactionInformation> getTransactionInformations(String username) {
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM TRANSACTION_DETAILS where username = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            List<TransactionInformation> list = new ArrayList<>();

            while(result.next()){
                TransactionInformation transactionInformation = new TransactionInformation();
                transactionInformation.setUsername(result.getString("username"));
                transactionInformation.setTransaction_id(result.getInt("transaction_id"));
                transactionInformation.setTransaction_type(result.getString("transaction_type"));
                transactionInformation.setAmount(result.getInt("amount"));
                transactionInformation.setTransaction_status(result.getString("transaction_status"));
                list.add(transactionInformation);
            }

            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<TransactionInformation>();
    }

    @Override
    public TransactionInformation getTransactionDetails(int transaction_id) {

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM TRANSACTION_DETAILS WHERE TRANSACTION_ID = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, transaction_id);

            ResultSet result = statement.executeQuery();

            TransactionInformation transactionInformation = new TransactionInformation();

            if(result.next()){
                transactionInformation.setUsername(result.getString("username"));
                transactionInformation.setTransaction_id(result.getInt("transaction_id"));
                transactionInformation.setTransaction_type(result.getString("transaction_type"));
                transactionInformation.setAmount(result.getInt("amount"));
                transactionInformation.setTransaction_status(result.getString("transaction_status"));
            }

            return transactionInformation;


        }catch (SQLException e){
            e.printStackTrace();
        }

        return new TransactionInformation();

    }

    public int getBalanceAmount(String username) {

        try(Connection connection = ConnectionUtil.getConnection()){
            int balance = 0;
            String sql = "SELECT bank_balance FROM Account_info WHERE username = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                balance = result.getInt("bank_balance");
            }

            return balance;


        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;

    }

    public boolean updateBalanceAmount(int amount, String username) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "UPDATE ACCOUNT_INFO SET bank_balance = ? WHERE username = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            int count = 0;
            statement.setInt(++count, amount);
            statement.setString(++count, username);

            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    public String getAccessLevel(String username){
        AdditionalInformation additionalInformation = new AdditionalInformation();
        additionalInformation = findByName(username);
        return  additionalInformation.getAccess_levels();
    }

    @Override
    public boolean updateUserActiveStatus(String username, boolean active) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "UPDATE LOGIN_INFORMATION SET ACTIVE = ? WHERE username = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            int count = 0;
            statement.setBoolean(++count, active);
            statement.setString(++count, username);

            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}

