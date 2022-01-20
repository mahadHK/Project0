package com.revature.services;

import com.revature.repos.LoginDAO;
import com.revature.repos.LoginDAOImpl;

public class LoginService {

    private LoginDAO loginDAO = new LoginDAOImpl();

        public boolean login(String username, String password) {
            if(loginDAO.login(username,password)){
                return true;
            }else{
                return false;
            }
        }
}
