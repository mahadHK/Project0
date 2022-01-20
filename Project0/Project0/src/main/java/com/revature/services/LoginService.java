package com.revature.services;

public class LoginService {

    public boolean login(String username, String password){

        LoginDAO loginDAO = new LoginDAO();
        if(loginDAO.login(username,password)){
            return true;
        }else{
            return false;
        }
    }

}
