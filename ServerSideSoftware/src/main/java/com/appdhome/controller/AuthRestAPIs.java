package com.appdhome.controller;

import com.appdhome.entities.Role;
import com.appdhome.entities.RoleName;
import com.appdhome.entities.User;
import com.appdhome.message.request.LoginForm;
import com.appdhome.message.request.SignUpForm;
import com.appdhome.message.response.JwtResponse;
import com.appdhome.message.response.ResponseMessage;
import com.appdhome.repository.IRoleRepository;
import com.appdhome.repository.IUserRepository;
import com.appdhome.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserRepository userRepository;


    @Autowired
    IRoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginForm loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt =jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignUpForm signUpRequest){
        if(userRepository.existsByUsername(signUpRequest.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("Falló: El Username ya existe!! "),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("Falló: El Email ya existe!! "),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpRequest.getName(),signUpRequest.getUsername(), signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        String role = signUpRequest.getRole();
        Set roles = new HashSet<>();

        switch(role){
            case "admin":
                Role roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN)
                        .orElseThrow(()-> new RuntimeException("Falló -> Causa: El rol no fue encontrado en BD"));
                roles.add(roleAdmin); break;
            case "employee":
                Role roleEmployee = roleRepository.findByName(RoleName.ROLE_EMPLOYEE)
                        .orElseThrow(()-> new RuntimeException("Falló -> Causa: El rol no fue encontrado en BD"));
                roles.add(roleEmployee); break;
            default:
                Role roleCustomer = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
                        .orElseThrow(()-> new RuntimeException("Falló -> Causa: El rol no fue encontrado en BD"));
                roles.add(roleCustomer); break;
        }

        user.setRoles(roles);

        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("Usuario registrado correctamente !!"), HttpStatus.OK);
    }
}
