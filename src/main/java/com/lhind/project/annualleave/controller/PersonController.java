package com.lhind.project.annualleave.controller;

import com.lhind.project.annualleave.dto.AuthDTO;
import com.lhind.project.annualleave.dto.PersonDTO;
import com.lhind.project.annualleave.service.PersonService;
import com.lhind.project.annualleave.service.impl.AuthService;
import com.lhind.project.annualleave.service.impl.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class PersonController {

    private PersonService personService;

    private AuthService authService;

    private UserService userService;

    public PersonController(PersonService personService, AuthService authService, UserService userService) {
        this.personService = personService;
        this.authService = authService;
        this.userService = userService;

    }

    @PostMapping("/login")
    public ResponseEntity<UserDetails> createAuthenticationToken(@RequestBody AuthDTO authDTO) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,"Bearer " + authService.authenticate(authDTO));
        return new ResponseEntity<>(userService.loadUserByUsername(authDTO.getUsername()),headers, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<PersonDTO>> getAllUsers(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        Page<PersonDTO> list = personService.getAllUsers(pageNo, pageSize);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(personService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createUser(@Valid @RequestBody PersonDTO personDTO) {
        PersonDTO personCreated = personService.createUser(personDTO);
        return ResponseEntity.ok().body(personCreated);
    }

    @PutMapping
    public ResponseEntity<PersonDTO> updateUser(@RequestBody PersonDTO personDTO) {
        PersonDTO personUpdated = personService.updatePerson(personDTO);
        return ResponseEntity.ok().body(personUpdated);
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestParam String oldPassword,@RequestParam String newPassword,@RequestParam String confirmPassword) {
        personService.changeUserPassword(oldPassword,newPassword,confirmPassword);
        return ResponseEntity.ok().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        personService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

}
