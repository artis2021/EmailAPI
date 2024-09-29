package com.example.EmailAPI.controller;

//import org.springframework.stereotype.Controller;
import com.example.EmailAPI.model.EmailRequest;
import com.example.EmailAPI.services.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailServices emailServices;

    @GetMapping("/")
    public String welcome(){
        return "Welcome to Email API";
    }

//    @GetMapping("/send")
    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){
        System.out.println(emailRequest);
        boolean f = emailServices.sendEmail(emailRequest.getSubject(), emailRequest.getMessage(), emailRequest.getTo());
//        return ResponseEntity.ok("Done");
        if(f){
            return ResponseEntity.ok("Done");
        } else {
            return ResponseEntity.ok("Failed");
        }

    }


}
