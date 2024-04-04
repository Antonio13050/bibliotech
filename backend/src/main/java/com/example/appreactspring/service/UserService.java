package com.example.appreactspring.service;

import com.example.appreactspring.exception.UserAlreadyExistsException;
import com.example.appreactspring.model.Role;
import com.example.appreactspring.model.User;
import com.example.appreactspring.model.transport.UserResponseDTO;
import com.example.appreactspring.model.transport.operation.create.CreateUserForm;
import com.example.appreactspring.repository.RoleRepository;
import com.example.appreactspring.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO create(CreateUserForm form) throws UserAlreadyExistsException {

        Role basicRole = this.roleRepository.findByName(Role.Values.BASIC.name());

        validateUniqueUsername(form.username());
        validateUniqueEmail(form.email());

        var user = new User();
        user.setUsername(form.username());
        user.setPassword(passwordEncoder.encode(form.password()));
        user.setEmail(form.email());
        user.setRoles(Set.of(basicRole));

        this.userRepository.save(user);
        return new UserResponseDTO(user);
    }

    public List<UserResponseDTO> listUsers(){
        return this.userRepository.findAll()
                .stream().map(UserResponseDTO::new).collect(Collectors.toList());
    }

    private void validateUniqueUsername(String username) throws UserAlreadyExistsException {

        Optional<User> userFromUsername = this.userRepository.findByUsername(username);

        if (userFromUsername.isPresent()) {
            throw new UserAlreadyExistsException("User with username: " + username + ", already exists: " );
        }
    }

    private void validateUniqueEmail(String email) throws UserAlreadyExistsException {

        Optional<User> userFromEmail = this.userRepository.findByEmail(email);

        if (userFromEmail.isPresent()) {
            throw new UserAlreadyExistsException("User with email: " + email + ", already exists: " );
        }
    }
}
