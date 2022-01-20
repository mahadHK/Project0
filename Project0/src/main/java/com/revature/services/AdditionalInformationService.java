package com.revature.services;

import com.revature.models.AdditionalInformation;
import com.revature.models.TransactionInformation;
import com.revature.repos.AdditionalInformationDAO;
import com.revature.repos.AdditionalInformationDAOImpl;

import java.util.List;

public class AdditionalInformationService {

    private AdditionalInformationDAO additional_informationDAO = new AdditionalInformationDAOImpl();

    public List<AdditionalInformation> findAllAdditionalInformation(){
        return additional_informationDAO.findAll();
    }

    public AdditionalInformation findAdditionalInformation(String name){
        return additional_informationDAO.findByName(name);
    }

    public boolean updateAdditionalInformation(AdditionalInformation additional_information){
        return additional_informationDAO.updateAdditionalInformation(additional_information);
    }

    public boolean addAdditionalInformation(AdditionalInformation additional_information){
        return additional_informationDAO.addAdditionalInformation(additional_information);
    }

    public boolean updateApplicationStatus(String application_status, boolean active, String username){
        return additional_informationDAO.updateApplicationStatus(application_status, active, username);
    }


    public boolean insertTransactionRequest(TransactionInformation transactionInformation){
        return additional_informationDAO.insertTransactionRequest(transactionInformation);
    }

    public boolean updateTransactionStatus(int transaction_id, String transaction_status){
        if(additional_informationDAO.updateTransactionStatus(transaction_id, transaction_status)){
            if(transaction_status.equalsIgnoreCase("Approved")){
                TransactionInformation transactionInformation = new TransactionInformation();
                transactionInformation = additional_informationDAO.getTransactionDetails(transaction_id);
                int balanceAmount = additional_informationDAO.getBalanceAmount(transactionInformation.getUsername());
                if(transactionInformation!=null){
                    if(transactionInformation.getTransaction_type().equalsIgnoreCase("deposit")){
                        balanceAmount =  balanceAmount + transactionInformation.getAmount();
                    }else{
                        balanceAmount =  balanceAmount -  transactionInformation.getAmount();
                    }
                    return additional_informationDAO.updateBalanceAmount(balanceAmount, transactionInformation.getUsername());
                }
            }
        }
        return false;
    }

    public List<TransactionInformation> getTransactionInformation(){
        return additional_informationDAO.getTransactionInformation();
    }

    public List<TransactionInformation> getTransactionInformations(String username){
        return additional_informationDAO.getTransactionInformations(username);
    }

    public String getAccessLevel(String logged_username) {
        return additional_informationDAO.getAccessLevel(logged_username);
    }

    public boolean updateUserActiveStatus(String username, boolean active) {
        return additional_informationDAO.updateUserActiveStatus(username, active);
    }
}
