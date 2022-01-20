package com.revature.repos;

import com.revature.models.AdditionalInformation;

import java.util.List;

public interface AdditionalInformationDAO {
    public List<AdditionalInformation> findAll();
    public AdditionalInformation findByName(String name);
    public boolean updateAdditionalInformation (AdditionalInformation additional_information);
    public boolean addAdditionalInformation(AdditionalInformation additional_information);
    public boolean updateApplicationStatus(String application_status, boolean active, String username);
    public boolean insertTransactionRequest(TransactionInformation transactionInformation);
    public boolean updateTransactionStatus(int transaction_id, String transaction_status);
    public List<TransactionInformation> getTransactionInformation();
    public List<TransactionInformation> getTransactionInformations(String username);
    public TransactionInformation getTransactionDetails(int transaction_id);
    public boolean updateBalanceAmount(TransactionInformation transactionInformation);
}
