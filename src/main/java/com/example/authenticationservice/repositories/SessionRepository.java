package com.example.authenticationservice.repositories;

import com.example.authenticationservice.models.Session;
import com.example.authenticationservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    public Session save(Session session);
    public Session findSessionByToken(String token);
}
