package com.lhind.project.annualleave.service.impl;

import com.lhind.project.annualleave.dto.AddUpdatePersonDTO;
import com.lhind.project.annualleave.dto.PersonDTO;
import com.lhind.project.annualleave.entity.PersonEntity;
import com.lhind.project.annualleave.exception.EntityNotFoundException;
import com.lhind.project.annualleave.mapper.AddUpdatePersonMapper;
import com.lhind.project.annualleave.mapper.PersonMapper;
import com.lhind.project.annualleave.mapper.RoleMapper;
import com.lhind.project.annualleave.repository.PersonRepository;
import com.lhind.project.annualleave.repository.RoleRepository;
import com.lhind.project.annualleave.service.PersonService;
import com.lhind.project.annualleave.util.PersonHelperMapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    private RoleRepository roleRepository;

    private PersonHelperMapper helperMapper;

    private UserService userService;

    private AddUpdatePersonMapper addUpdateMapper
            = Mappers.getMapper(AddUpdatePersonMapper.class);

    private PersonMapper mapper
            = Mappers.getMapper(PersonMapper.class);
    public static Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private RoleMapper roleMapper
            = Mappers.getMapper(RoleMapper.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PersonServiceImpl(PersonRepository personRepository, RoleRepository roleRepository, PersonHelperMapper helperMapper, UserService userService) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.helperMapper = helperMapper;
        this.userService = userService;
    }

    public Page<PersonDTO> getAllUsers(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<PersonEntity> users = personRepository.findByIsDeleted(paging, false);
        return helperMapper.toDto(users);
    }

    public PersonDTO getUserById(Long id) {
        Optional<PersonEntity> user = personRepository.findById(id);
        return mapper.toDto(user.orElseThrow(() -> new EntityNotFoundException("Not valid Id: " + id)));
    }

    public PersonDTO updatePerson(AddUpdatePersonDTO user) {
        if (personRepository.existsById(user.getId())) {
            Optional<PersonEntity> model = personRepository.findById(user.getId());
            PersonEntity personEntity = model.get();
            personEntity.setUpdatedOn(LocalDate.now());
            personEntity = personRepository.save(addUpdateMapper.toEntity(user));

            return mapper.toDto(personEntity);
        }

        throw new EntityNotFoundException("Not valid Id: " + user.getId());
    }


    public PersonDTO createUser(AddUpdatePersonDTO person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        PersonEntity personEntity = personRepository.save(addUpdateMapper.toEntity(person));
        return mapper.toDto(personEntity);
    }

    public boolean changeUserPassword(String oldPassword, String newPassword, String confirmPassword) {
        logger.info("inside changeUserPassword");
        PersonEntity personEntity = personRepository.findPersonByUsername(userService.getUserDetails().getUsername());
        logger.info(personEntity.getUsername());
        boolean isPasswordMatch = passwordEncoder.matches(oldPassword, personEntity.getPassword());
        if (isPasswordMatch && newPassword.equals(confirmPassword)) {
            personEntity.setPassword(passwordEncoder.encode(newPassword));
            personEntity.setUpdatedOn(LocalDate.now());
            personRepository.save(personEntity);
            return true;
        }
        return false;
    }

    public void deleteUserById(Long id) {
        if (personRepository.existsById(id)) {
            Optional<PersonEntity> user = personRepository.findById(id);
            user.get().setDeleted(true);
            personRepository.save(user.get());
            logger.info("User deleted");
        } else {
            throw new EntityNotFoundException("Not valid Id: " + id);
        }
    }

}
