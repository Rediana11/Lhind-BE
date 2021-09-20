package com.lhind.project.annualleave.person;

import com.lhind.project.annualleave.dto.PersonDTO;
import com.lhind.project.annualleave.entity.PersonEntity;
import com.lhind.project.annualleave.mapper.PersonMapper;
import com.lhind.project.annualleave.repository.PersonRepository;
import com.lhind.project.annualleave.repository.RoleRepository;
import com.lhind.project.annualleave.service.PersonService;
import com.lhind.project.annualleave.service.impl.PersonServiceImpl;
import com.lhind.project.annualleave.service.impl.UserService;
import com.lhind.project.annualleave.util.PersonHelperMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceUnitTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private RoleRepository  roleRepository;

    private PersonService personService;

    private UserService userService;

    private PersonMapper personMapper;

    private PersonHelperMapper personHelperMapper;

    @BeforeEach
    void setup() {
        personMapper = Mappers.getMapper(PersonMapper.class);
        personHelperMapper = new PersonHelperMapper();
        personService = new PersonServiceImpl(personRepository,roleRepository, personHelperMapper,userService);
    }

    @Test
    void shouldReturnUsers_whenGetAllUsers() {
        Pageable paging = PageRequest.of(2, 5);
        when(personRepository.findByIsDeleted(paging, false)).thenReturn(
                new PageImpl<>(Arrays.asList(new PersonEntity(), new PersonEntity()))
        );

        Page<PersonDTO> dtoPage = personService.getAllUsers(2, 5);
        boolean checkContentSize = dtoPage.getContent().size() == 2;
        assertTrue(checkContentSize);
    }

    @Test
    void shouldReturnUser_whenGivenId() {
        Long id = 1L;
        PersonEntity entity = new PersonEntity();
        entity.setId(id);
        when(personRepository.findById(id)).thenReturn(Optional.of(entity));

        PersonDTO dto = personService.getUserById(1L);

        boolean isDtoRespective = dto.getId().equals(entity.getId());
        assertTrue(isDtoRespective);
    }

    @Test
    void shouldUpdatePerson_whenGivenDto() {
        Long id = 1L;
        String firstName = "Redi";

        PersonDTO updateDto = new PersonDTO();
        updateDto.setId(id);
        updateDto.setFirstName(firstName);

        PersonEntity updatedEntity = new PersonEntity();
        updatedEntity.setId(id);
        updatedEntity.setFirstName(firstName);

        when(personRepository.existsById(updateDto.getId())).thenReturn(true);
        when(personRepository.save(any(PersonEntity.class))).thenReturn(updatedEntity);

        PersonDTO updatedPerson = personService.updatePerson(updateDto);
        boolean updated = updatedPerson.getFirstName().equals(updateDto.getFirstName());
        assertTrue(updated);
    }

}
