package com.revature.controllers;

import com.revature.models.AdditionalInformation;
import com.revature.services.AdditionalInformationService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AdditionalInformationController implements Controller{

    private AdditionalInformationService additionalInformationService = new AdditionalInformationService();

    Handler getAdditionalInformation = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            ctx.json(additionalInformationService.findAllAdditionalInformation());
            ctx.status(200);
        }else{
            ctx.status(401);
        }
    };

    Handler getAdditionalInformations = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String user_name = ctx.pathParam("user_name");
            AdditionalInformation additionalInformation = additionalInformationService.findAdditionalInformation(user_name);
            ctx.json(additionalInformation);
            ctx.status(200);
        }else{
            ctx.status(401);
        }
    };

    Handler updateAdditionalInformation = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            AdditionalInformation additional_information = ctx.bodyAsClass(AdditionalInformation.class);
            if(additionalInformationService.updateAdditionalInformation(additional_information)){
                ctx.status(202);
            }else{
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    Handler newAdditionalInformation = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            AdditionalInformation additional_information = ctx.bodyAsClass(AdditionalInformation.class);
            if(additionalInformationService.addAdditionalInformation(additional_information)){
                ctx.status(201);
            }else{
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    Handler updateApplicationStatus = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String application_status = ctx.pathParam("application_status");
            boolean active = ctx.pathParam("active");
            String username = ctx.pathParam("username");
            if(additionalInformationService.updateApplicationStatus(application_status, active, username)){
                ctx.status(202);
            }else{
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    Handler insertTransactionRequest = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            TransactionInformation transactionInformation = ctx.bodyAsClass(TransactionInformation.class);
            if(additionalInformationService.insertTransactionRequest(transactionInformation)){
                ctx.status(202); 
            }else{
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    Handler updateTransactionStatus = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            int transaction_id = ctx.pathParam("transaction_id");
            String transaction_status = ctx.pathParam("transaction_status");
            if(additionalInformationService.updateTransactionStatus(transaction_id, transaction_status)){
                ctx.status(202);
            }else{
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    Handler getTransactionInformation = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            ctx.json(additionalInformationService.getTransactionInformation());
            ctx.status(200);
        }else{
            ctx.status(401);
        }
    };

    Handler getTransactionInformations = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String user_name = ctx.pathParam("user_name");
            ctx.json(additionalInformationService.getTransactionInformations(user_name));
            ctx.status(200);
        }else{
            ctx.status(401);
        }
    };

    @Override
    public void addRoutes(Javalin app) {

        app.get("/additional_information", getAdditionalInformation);
        app.get("/additional_information/{first_name}", getAdditionalInformations);
        app.put("/additional_information", updateAdditionalInformation);
        app.post("/additional_information", newAdditionalInformation);
        app.put("/updateApplicationStatus",updateApplicationStatus);
        app.put("/insertTransactionRequest",insertTransactionRequest);
        app.put("/updateTransactionStatus",updateTransactionStatus);

    }
}
