package com.lhind.project.annualleave.service.impl;

import com.lhind.project.annualleave.dto.PersonDTO;
import com.lhind.project.annualleave.entity.PersonEntity;
import com.lhind.project.annualleave.mapper.PersonMapper;
import com.lhind.project.annualleave.mapper.RoleMapper;
import com.lhind.project.annualleave.repository.PersonRepository;
import com.lhind.project.annualleave.repository.RoleRepository;
import com.lhind.project.annualleave.security.model.SecurityUser;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserDetails userDetails;

    private PersonRepository personRepository;

    private RoleRepository roleRepository;

    public UserService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    private PersonMapper mapper
            = Mappers.getMapper(PersonMapper.class);

    private RoleMapper roleMapper
            = Mappers.getMapper(RoleMapper.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonEntity personEntity = personRepository.findPersonByUsername(username);
        UserDetails userDetail = new SecurityUser(personEntity);
        this.userDetails = userDetail;
        return userDetails;
    }

    public PersonDTO loadPersonByUsername(String username) {
        PersonEntity personEntity = personRepository.findPersonByUsername(username);

        return mapper.toDto(personEntity);
    }


    public UserDetails getUserDetails() {
        return userDetails;
    }

}