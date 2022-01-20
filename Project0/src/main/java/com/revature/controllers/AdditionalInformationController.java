package com.revature.controllers;

import com.revature.models.AdditionalInformation;
import com.revature.models.TransactionInformation;
import com.revature.services.AdditionalInformationService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AdditionalInformationController implements Controller{

    private AdditionalInformationService additionalInformationService = new AdditionalInformationService();

    Handler getAdditionalInformation = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            String logged_username  = (String) ctx.req.getSession().getAttribute("logged_username");
            String access_level = additionalInformationService.getAccessLevel(logged_username);
            if(access_level != null && !access_level.isEmpty() && !access_level.equalsIgnoreCase("customer")) {
                ctx.json(additionalInformationService.findAllAdditionalInformation());
                ctx.status(200);
            } else {
                ctx.status(400);
            }
        } else {
            ctx.status(401);
        }
    };

    Handler getAdditionalInformations = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String logged_username  = (String) ctx.req.getSession().getAttribute("logged_username");
            String access_level = additionalInformationService.getAccessLevel(logged_username);
            if(access_level != null && !access_level.isEmpty()) {
                String user_name = ctx.pathParam("username");
                if((access_level.equalsIgnoreCase("customer") && logged_username.equalsIgnoreCase(user_name))|| !access_level.equalsIgnoreCase("customer")) {
                    AdditionalInformation additionalInformation = additionalInformationService.findAdditionalInformation(user_name);
                    ctx.json(additionalInformation);
                    ctx.status(200);
                }else{
                   ctx.status(400);
               }
           }
        }else{
            ctx.status(401);
        }
    };

    Handler updateAdditionalInformation = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String logged_username  = (String) ctx.req.getSession().getAttribute("logged_username");
            String access_level = additionalInformationService.getAccessLevel(logged_username);
            if(access_level != null && !access_level.isEmpty() && access_level.equalsIgnoreCase("branch_manager")) {
                AdditionalInformation additional_information = ctx.bodyAsClass(AdditionalInformation.class);
                if(additionalInformationService.updateAdditionalInformation(additional_information)){
                    ctx.status(202);
                }else{
                    ctx.status(400);
                }
            } else {
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    Handler newAdditionalInformation = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String logged_username  = (String) ctx.req.getSession().getAttribute("logged_username");
            String access_level = additionalInformationService.getAccessLevel(logged_username);
            if(access_level != null && !access_level.isEmpty() && access_level.equalsIgnoreCase("branch_manager")) {
                AdditionalInformation additional_information = ctx.bodyAsClass(AdditionalInformation.class);
                if(additionalInformationService.addAdditionalInformation(additional_information)){
                    ctx.status(201);
                }else{
                    ctx.status(400);
                }
            }  else {
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    Handler updateApplicationStatus = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String logged_username  = (String) ctx.req.getSession().getAttribute("logged_username");
            String access_level = additionalInformationService.getAccessLevel(logged_username);
            if(access_level != null && !access_level.isEmpty() && !access_level.equalsIgnoreCase("customer")) {
                AdditionalInformation additional_information = ctx.bodyAsClass(AdditionalInformation.class);
                if(additionalInformationService.updateApplicationStatus(additional_information.getApplication_status(), additional_information.getActive(), additional_information.getUsername())){
                    ctx.status(202);
                }else{
                    ctx.status(400);
                }
            } else {
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    Handler insertTransactionRequest = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String logged_username  = (String) ctx.req.getSession().getAttribute("logged_username");
            String access_level = additionalInformationService.getAccessLevel(logged_username);
            if(access_level != null && !access_level.isEmpty() && !access_level.equalsIgnoreCase("bank_teller")) {
                TransactionInformation transactionInformation = ctx.bodyAsClass(TransactionInformation.class);
                if(additionalInformationService.insertTransactionRequest(transactionInformation)){
                    ctx.status(202);
                }else{
                    ctx.status(400);
                }
            } else {
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    Handler updateTransactionStatus = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String logged_username  = (String) ctx.req.getSession().getAttribute("logged_username");
            String access_level = additionalInformationService.getAccessLevel(logged_username);
            if(access_level != null && !access_level.isEmpty() && access_level.equalsIgnoreCase("branch_manager")) {
                TransactionInformation transactionInformation = ctx.bodyAsClass(TransactionInformation.class);
                if(additionalInformationService.updateTransactionStatus(transactionInformation.getTransaction_id(), transactionInformation.getTransaction_status())){
                    ctx.status(202);
                }else{
                    ctx.status(400);
                }
            } else {
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    Handler getTransactionInformation = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String logged_username  = (String) ctx.req.getSession().getAttribute("logged_username");
            String access_level = additionalInformationService.getAccessLevel(logged_username);
            if(access_level != null && !access_level.isEmpty() && !access_level.equalsIgnoreCase("customer")) {
                ctx.json(additionalInformationService.getTransactionInformation());
                ctx.status(200);
            } else {
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    Handler getTransactionInformations = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String logged_username  = (String) ctx.req.getSession().getAttribute("logged_username");
            String access_level = additionalInformationService.getAccessLevel(logged_username);
            if(access_level != null && !access_level.isEmpty()) {
                String username = ctx.pathParam("username");
                if((access_level.equalsIgnoreCase("customer") && logged_username.equalsIgnoreCase(username))|| !access_level.equalsIgnoreCase("customer")) {
                    ctx.json(additionalInformationService.getTransactionInformations(username));
                    ctx.status(200);
                }
                else {
                    ctx.status(400);
                }
            }else{
                ctx.status(401);
            }
        }
    };

    Handler updateUserActiveStatus = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String logged_username  = (String) ctx.req.getSession().getAttribute("logged_username");
            String access_level = additionalInformationService.getAccessLevel(logged_username);
            if(access_level != null && !access_level.isEmpty() && access_level.equalsIgnoreCase("branch_manager")) {
                AdditionalInformation additionalInformation = ctx.bodyAsClass(AdditionalInformation.class);
                if(additionalInformationService.updateUserActiveStatus(additionalInformation.getUsername(),additionalInformation.getActive())){
                    ctx.status(202);
                }else{
                    ctx.status(400);
                }
            } else {
                ctx.status(400);
            }
        }else{
            ctx.status(401);
        }
    };

    @Override
    public void addRoutes(Javalin app) {

        app.get("/additional_information", getAdditionalInformation);
        app.get("/additional_information/{username}", getAdditionalInformations);
        app.post("/update_additional_information", updateAdditionalInformation);
        app.put("/additional_information", newAdditionalInformation);
        app.post("/updateApplicationStatus",updateApplicationStatus);
        app.put("/insertTransactionRequest",insertTransactionRequest);
        app.post("/updateTransactionStatus",updateTransactionStatus);
        app.get("/getTransactionInformation", getTransactionInformation);
        app.get("/getTransactionInformations/{username}", getTransactionInformations);
        app.get("/updateUserActiveStatus",updateUserActiveStatus);

    }
}
