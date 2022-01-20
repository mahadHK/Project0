package com.revature.repos;

import com.revature.models.AdditionalInformation;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdditionalInformationDAOImpl implements AdditionalInformationDAO{


    @Override
    public List<AdditionalInformation> findAll() {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM login_information login join additional_information add (login.user_id = add.user_id)";

            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery(sql);

            List<AdditionalInformation> list = new ArrayList<>();

            while(result.next()){
                AdditionalInformation additional_information = new AdditionalInformation();
                additional_information.setFirst_name(result.getString("first_name"));
                additional_information.setLast_name(result.getString("last_name"));
                additional_information.setStreet(result.getString("street"));
                additional_information.setCity(result.getString("city"));
                additional_information.setState(result.getString("state"));
                additional_information.setZip(result.getString("zip"));
                additional_information.setDate_of_birth(result.getDate("date_of_birth"));
                additional_information.setUser_id(result.getInt("user_id"));
                additional_information.setUsername(result.getString("username"));
                additional_information.setPassword(result.getString("password"));
                additional_information.setEmail(result.getString("email"));
                additional_information.setActive(result.getBoolean("active"));
                additional_information.setApplication_Status(result.getString("application_status"));
                additional_information.setAccess_Level(result.getString("access_level"));
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
            String sql = "SELECT * FROM additional_information WHERE username = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, name);

            ResultSet result = statement.executeQuery();

            AdditionalInformation additional_information = new AdditionalInformation();

            if(result.next()){
                additional_information.setFirst_name(result.getString("first_name"));
                additional_information.setLast_name(result.getString("last_name"));
                additional_information.setStreet(result.getString("street"));
                additional_information.setCity(result.getString("city"));
                additional_information.setState(result.getString("state"));
                additional_information.setZip(result.getString("zip"));
                additional_information.setDate_of_birth(result.getDate("date_of_birth"));
                additional_information.setUser_id(result.getInt("user_id"));
                additional_information.setUsername(result.getString("username"));
                additional_information.setPassword(result.getString("password"));
                additional_information.setEmail(result.getString("email"));
                additional_information.setActive(result.getBoolean("active"));
                additional_information.setApplication_Status(result.getString("application_status"));
                additional_information.setAccess_Level(result.getString("access_level"));
            }

            return additional_information;


        }catch (SQLException e){
            e.printStackTrace();
        }

        return new AdditionalInformation();

    }

    @Override
    public boolean updateAdditionalInformation(AdditionalInformation additional_information) {
        try(Connection connection = ConnectionUtil.getConnection()){
            if(updateLoginInfroamtion(additional_information, connection)){
                updateInformation(additional_information, connection);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public boolean updateLoginInformation(AdditionalInformation additional_information, Connection connection) {
        try(connection){
            String sql = "UPDATE LOGIN_INFORMATION SET USER_NAME = ?, PASSWORD = ?, ACTIVE = ?, APPLICATION_STATUS = ?, EMAIL = ?, ACCESS_LEVEL = ?
            WHERE USER_ID = ?;";

            PreparedStatement statement = conn.prepareStatement(sql);

            int count = 0;
            statement.setString(++count, additional_information.getUserName());
            statement.setString(++count, additional_information.getPassword());
            statement.setBoolean(++count, additional_information.getActive());
            statement.setString(++count, additional_information.getApplication_status());
            statement.setString(++count, additional_information.getEmail());
            statement.setString(++count, additional_information.getAccess_level());
            statement.setInt(++count, additional_information.getUser_id());

            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public boolean updateInformation(AdditionalInformation additional_information, Connection connection) {
        try(connection){
            String sql = "UPDATE additional_information SET first_name = ?, last_name = ?, street = ?, city = ?, state = ?, zip = ?," +
                    "date_of_birth = ?, user_id = ? WHERE first_name = ?;";

            PreparedStatement statement = conn.prepareStatement(sql);

            int count = 0;
            statement.setString(++count, additional_information.getFirst_name());
            statement.setString(++count, additional_information.getLast_name());
            statement.setString(++count, additional_information.getStreet());
            statement.setString(++count, additional_information.getCity());
            statement.setString(++count, additional_information.getState());
            statement.setString(++count, additional_information.getZip());
            statement.setInt(++count, additional_information.getUser_id());
            statement.setString(++count, additional_information.getFirst_name());

            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public boolean addAdditionalInformation(AdditionalInformation additional_information) {
        try(Connection connection = ConnectionUtil.getConnection()){
            int userId = 0;

            userId = getUserId(connection);

            additional_information.setUser_id(userId);

            if(addLoginInformation(AdditionalInformation additional_information, connection)){
                addInformation(AdditionalInformation additional_information, connection);
            }

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int getUserId(Connection connection) {
        try(connection){
            Int userId = 0;

            String sql = "SELECT NEXTVAL("USER_ID_SEQ")";

            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery(sql);

            userId = result.getString("USER_ID");

        }catch (SQLException e){
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    public boolean addInformation(AdditionalInformation additional_information, Connection connection) {
        try(connection){

            String sql = "INSERT INTO login_information (username, pass_word, application_status, active, user_id, access_level, email) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);

            int count = 0;
            statement.setString(++count, additional_information.getUserName());
            statement.setString(++count, additional_information.getPassword());
            statement.setString(++count, additional_information.getapplication_status());
            statement.setString(++count, additional_information.getactive());
            statement.setString(++count, additional_information.getuser_id());
            statement.setString(++count, additional_information.getaccess_level());
            statement.setInt(++count, additional_information.getemail());

            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean addInformation(AdditionalInformation additional_information, Connection connection) {
        try(connection){

            String sql = "INSERT INTO additional_information (first_name, last_name, street, city, state, zip," +
                    " user_id) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);

            int count = 0;
            statement.setString(++count, additional_information.getFirst_name());
            statement.setString(++count, additional_information.getLast_name());
            statement.setString(++count, additional_information.getStreet());
            statement.setString(++count, additional_information.getCity());
            statement.setString(++count, additional_information.getState());
            statement.setString(++count, additional_information.getZip());
            statement.setInt(++count, additional_information.getUser_id());

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
            String sql = "UPDATE LOGIN_INFORMATION SET application_status = ?, active = ? WHERE USER_NAME = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

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
            String sql = "INSERT INTO transaction_details (username, transaction_id, transaction_type, amount, transaction_status) values (?,NEXTVAL("TRANS_ID"),?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            int count = 0;
            statement.setString(++count, transactionInformation.getusername());
            statement.setString(++count, transactionInformation.gettransactiontype());
            statement.setInt(++count, transactionInformation.getamount());
            statement.setString(++count, "Waiting");

            statement.execute();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateTransactionStatus(String transaction_id, String transaction_status) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "UPDATE transaction_details SET transaction_status = ? WHERE transaction_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

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
            String sql = "SELECT * FROM TRANACTION_DETAILS";

            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery(sql);

            List<TransactionInformation> list = new ArrayList<>();

            while(result.next()){
                TransactionInformation transactionInformation = new TransactionInformation();
                transactionInformation.setusername(result.getString("username"));
                transactionInformation.settransaction_id(result.getInt("transaction_id"));
                transactionInformation.settransaction_type(result.getString("transaction_type"));
                transactionInformation.setamount(result.getInt("amount"));
                transactionInformation.settransaction_status(result.getString("transaction_status"));
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

            String sql = "SELECT * FROM TRANACTION_DETAILS where username = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            List<TransactionInformation> list = new ArrayList<>();

            while(result.next()){
                TransactionInformation transactionInformation = new TransactionInformation();
                transactionInformation.setusername(result.getString("username"));
                transactionInformation.settransaction_id(result.getInt("transaction_id"));
                transactionInformation.settransaction_type(result.getString("transaction_type"));
                transactionInformation.setamount(result.getInt("amount"));
                transactionInformation.settransaction_status(result.getString("transaction_status"));
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
            String sql = "SELECT * FROM TRANACTION_DETAILS WHERE TRANSACTION_ID = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, transaction_id);

            ResultSet result = statement.executeQuery();

            TransactionInformation transactionInformation = new TransactionInformation();

            if(result.next()){
                transactionInformation.setusername(result.getString("username"));
                transactionInformation.settransaction_id(result.getInt("transaction_id"));
                transactionInformation.settransaction_type(result.getString("transaction_type"));
                transactionInformation.setamount(result.getInt("amount"));
                transactionInformation.settransaction_status(result.getString("transaction_status"));
            }

            return transactionInformation;


        }catch (SQLException e){
            e.printStackTrace();
        }

        return new TransactionInformation();

    }

    @Override
    public boolean updateBalanceAmount(TransactionInformation transactionInformation) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "UPDATE transaction_details SET transaction_status = ? WHERE transaction_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

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


}

