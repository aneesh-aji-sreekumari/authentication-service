package com.example.authenticationservice.services;

import com.example.authenticationservice.models.Session;
import com.example.authenticationservice.models.User;
import com.example.authenticationservice.repositories.SessionRepository;
import com.example.authenticationservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    public AuthenticationService(UserRepository userRepository, SessionRepository sessionRepository){
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }
    public ResponseEntity<String> login(User user)  {
        User user1 = userRepository.findUserByEmail(user.getEmail());
        if(user1 == null){
            user1 = userRepository.save(user);
            String token = RandomStringUtils.randomAlphanumeric(20);
            Session session = new Session();
            session.setToken(token);
            session.setUser(user1);
            session = sessionRepository.save(session);
            return new ResponseEntity<>(session.getToken(),HttpStatus.OK);
        }
        List<Session> sessions = user1.getSessions();
        return new ResponseEntity<>(sessions.get(0).getToken(), HttpStatus.OK);
    }
    public ResponseEntity<String> validate(String token){
        Session session = sessionRepository.findSessionByToken(token);
        if(session == null){
            return new ResponseEntity<>("No Session is found with given token", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Token Successfully Validated", HttpStatus.OK);
    }
}
