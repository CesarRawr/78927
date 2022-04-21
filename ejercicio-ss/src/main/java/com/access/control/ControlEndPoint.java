package com.access.control;

import java.util.Optional;
import java.util.ArrayList;
import com.access.control.entities.User;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.access.control.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.access_com.control.GrantAccessRequest;
import https.access_com.control.GrantAccessResponse;
import https.access_com.control.ValidateTokenRequest;
import https.access_com.control.ValidateTokenResponse;

import java.security.NoSuchAlgorithmException;
import com.access.control.services.TokenService;

@Endpoint
public class ControlEndPoint {
    @Autowired   
    UserRepository userRepo;

    @PayloadRoot(namespace = "https://access.com/control", localPart = "GrantAccessRequest")
    @ResponsePayload
    public GrantAccessResponse grantAccess(@RequestPayload GrantAccessRequest req) {
        GrantAccessResponse res = new GrantAccessResponse();

        // creando token
        String token = "";
        try {
            TokenService ts = new TokenService();
            token = ts.generateToken();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
            token = "error";
        }

        // si crear el token falla
        if (token == "error") {
            res.setId(-1);
            res.setToken("Hubo un error al crear tu token");
            return res;
        }

        // creando usuario si token no falla
        User user = new User();
        user.setNombre(req.getNombre());
        user.setHabitacion(req.getHabitacion());
        user.setToken(token);

        // guardando usuario
        userRepo.save(user);

        // enviando respuesta
        res.setId(user.getId());
        res.setToken(token);
        return res;
    }

    @PayloadRoot(namespace = "https://access.com/control", localPart = "ValidateTokenRequest")
    @ResponsePayload
    public ValidateTokenResponse validateToken(@RequestPayload ValidateTokenRequest req) {
        ValidateTokenResponse res = new ValidateTokenResponse();

        // Buscando si la persona está registrada
        Optional<User> isUser = userRepo.findById(req.getId());
        // si no esta registrada
        if (!isUser.isPresent()) {
            res.setIsTokenValid(false);
            return res;
        }

        // verificando token
        User user = isUser.get();
        if (!req.getToken().equals(user.getToken())) {
            res.setIsTokenValid(false);
            return res;
        }

        res.setIsTokenValid(true);
        return res;
    }
}
