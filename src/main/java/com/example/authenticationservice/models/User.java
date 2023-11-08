package com.example.authenticationservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseModel{
    private String fullName;
    private String password;
    private String email;
    @OneToMany(fetch = jakarta.persistence.FetchType.EAGER, mappedBy = "user")
    private List<Session> sessions;
}
