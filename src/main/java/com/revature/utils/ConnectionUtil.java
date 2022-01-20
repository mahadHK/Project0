package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {

        //For many framewords using JDBC it is necessary to "register" the driver
        //package you are using. This is to make the framework aware of it.
        try {
            Class.forName(("org.postgresql.Driver"));
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://javafs220103.c2351hhbxi6c.us-east-1.rds.amazonaws.com:5432/demos";
        String username = "postgres"; //It is possible and preferable to hide this information in environment variables
        //System.out.println(System.getenv("SQLPassword"));
        String password = "Password"; //Those are accessed by System.getenv("var-name");

        return DriverManager.getConnection(url, username, password);
    }

//    public static void main(String[] args){
//        try{
//            getConnection();
//            System.out.println("Connection successful");
//        }catch (SQLException e){
//            System.out.println("Connection failed");
//            e.printStackTrace();
//        }
//    }

}
