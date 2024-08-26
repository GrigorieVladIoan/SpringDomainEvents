package com.vlad.java.domain.events.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    public void send(String emailAddress, String message){
        log.info("Sent email to {} with message {}", emailAddress, message);
    }

}
