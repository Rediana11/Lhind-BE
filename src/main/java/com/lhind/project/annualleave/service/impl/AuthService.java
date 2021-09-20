package com.lhind.project.annualleave.service.impl;

import com.lhind.project.annualleave.dto.AuthDTO;
import com.lhind.project.annualleave.entity.PersonEntity;
import com.lhind.project.annualleave.exception.EntityNotFoundException;
import com.lhind.project.annualleave.repository.PersonRepository;
import com.lhind.project.annualleave.security.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private PersonRepository personRepository;

    private AuthenticationManagerBuilder authenticationManagerBuilder;

    private TokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    Logger log = LoggerFactory.getLogger(TokenProvider.class);


    public AuthService(PersonRepository personRepository, AuthenticationManagerBuilder authenticationManagerBuilder, TokenProvider tokenProvider) {
        this.personRepository = personRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
    }

    public String authenticate (AuthDTO authDTO){
        PersonEntity user = personRepository.findPersonByUsername(authDTO.getUsername());

        boolean matches = passwordEncoder.matches(authDTO.getPassword(), user.getPassword());

        if (user==null || !matches){
            throw new EntityNotFoundException("Wrong credencials ! User not found!");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.createToken(authentication);
    }
}