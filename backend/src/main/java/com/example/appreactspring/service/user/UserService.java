package com.example.appreactspring.service.user;

import com.example.appreactspring.exception.UserAlreadyExistsException;
import com.example.appreactspring.model.Role;
import com.example.appreactspring.model.User;
import com.example.appreactspring.dto.UserResponseDTO;
import com.example.appreactspring.service.notification.NotificationService;
import com.example.appreactspring.service.validation.EmailValidator;
import com.example.appreactspring.service.validation.UsernameValidator;
import com.example.appreactspring.transport.operation.create.CreateUserForm;
import com.example.appreactspring.repository.RoleRepository;
import com.example.appreactspring.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;
    private final UsernameValidator usernameValidator;
    private final NotificationService notificationService;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, EmailValidator emailValidator, UsernameValidator usernameValidator, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailValidator = emailValidator;
        this.usernameValidator = usernameValidator;
        this.notificationService = notificationService;
    }

    @Transactional
    public UserResponseDTO create(CreateUserForm form) throws UserAlreadyExistsException {

        Role basicRole = this.roleRepository.findByName(Role.Values.BASIC.name());

        usernameValidator.validateUniqueUsername(form.username());
        emailValidator.validateUniqueEmail(form.email());

        var user = new User();
        user.setUsername(form.username());
        user.setPassword(passwordEncoder.encode(form.password()));
        user.setEmail(form.email());
        user.setRoles(Set.of(basicRole));

        notificationService.notifyUserCreation(user);

        this.userRepository.save(user);
        return new UserResponseDTO(user);
    }

    public List<UserResponseDTO> listUsers(){
        return this.userRepository.findAll()
                .stream().map(UserResponseDTO::new).collect(Collectors.toList());
    }
}
