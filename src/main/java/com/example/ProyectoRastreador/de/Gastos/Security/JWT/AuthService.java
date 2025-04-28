package com.example.ProyectoRastreador.de.Gastos.Security.JWT;

import com.example.ProyectoRastreador.de.Gastos.DTO.LoginDTO;
import com.example.ProyectoRastreador.de.Gastos.DTO.userRequestDTO;
import com.example.ProyectoRastreador.de.Gastos.Entity.CredencialesUser;
import com.example.ProyectoRastreador.de.Gastos.Entity.User;
import com.example.ProyectoRastreador.de.Gastos.Enums.EstadoUser;
import com.example.ProyectoRastreador.de.Gastos.Enums.RoleUser;
import com.example.ProyectoRastreador.de.Gastos.Repository.CredentialsRepository;
import com.example.ProyectoRastreador.de.Gastos.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CredentialsRepository credentialsRepository;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, CredentialsRepository credentialsRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.credentialsRepository = credentialsRepository;
        this.jwtService = jwtService;
    }

    public String registerUser(userRequestDTO userRequest){
        User user = User.builder()
                .nombre(userRequest.getUserDTO().getNombre())
                .apellido(userRequest.getUserDTO().getApellido())
                .estadoUser(EstadoUser.ACTIVO)
                .direccion(userRequest.getUserDTO().getDireccion())
                .telefono(userRequest.getUserDTO().getTelefono())
                .fechaNacimiento(userRequest.getUserDTO().getFechaNacimiento())
                .fechaRegistro(new Date())
                .build();
        this.userRepository.save(user);

        CredencialesUser credencialesUser = CredencialesUser.builder()
                .email(userRequest.getCredencialesDTO().getEmail())
                .password(passwordEncoder.encode(userRequest.getCredencialesDTO().getPassword()))
                .roleUser(RoleUser.USER)
                .userCredenciales(user)
                .username(userRequest.getCredencialesDTO().getUsername())
                .fechaCreacion(new Date())
                .build();
        this.credentialsRepository.save(credencialesUser);

        return this.jwtService.generateToken(userRequest.getCredencialesDTO().getEmail());


    }

    public String loginUser(LoginDTO loginDTO){
        CredencialesUser credencialesUser = this.credentialsRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow();

        if (!passwordEncoder.matches(loginDTO.getClave(), credencialesUser.getPassword())){
            throw new RuntimeException("Las credenciales no son correctas");
        }

        return this.jwtService.generateToken(credencialesUser.getEmail());

    }





}
