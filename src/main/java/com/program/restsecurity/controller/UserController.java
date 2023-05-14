package com.program.restsecurity.controller;

import com.program.restsecurity.entity.User;
import com.program.restsecurity.rest.response.Metadata;
import com.program.restsecurity.rest.response.Response;
import com.program.restsecurity.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    private Response response = new Response();

    @PostMapping("/users/register")
    public ResponseEntity<Response> register(@RequestBody User user){

        User registerUser = userService.registerUser(user);

        response("User created", registerUser, HttpStatus.CREATED, HttpStatus.CREATED.value());

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    private void response(String message, Object payload, HttpStatus httpStatus, int statusCode){
        response.setMessage(message);
        response.setPayload(payload);
        response.setHttpStatus(httpStatus);
        response.setStatusCode(statusCode);
    }

    private void response(String message, Object payload, HttpStatus httpStatus, int statusCode, Metadata metadata){
        response.setMessage(message);
        response.setPayload(payload);
        response.setHttpStatus(httpStatus);
        response.setStatusCode(statusCode);
        response.setMetadata(metadata);
    }

}
