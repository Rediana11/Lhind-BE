package com.lhind.project.annualleave.service;

import com.lhind.project.annualleave.dto.PersonDTO;
import org.springframework.data.domain.Page;

public interface PersonService {

    Page<PersonDTO> getAllUsers(Integer pageNo, Integer pageSize);

    PersonDTO getUserById(Long id);

    PersonDTO updatePerson(PersonDTO user);

    PersonDTO createUser(PersonDTO user);

    void changeUserPassword(String oldPassword, String newPassword, String confirmPassword);

    void deleteUserById(Long id);

}
