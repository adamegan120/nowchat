package com.chat.app.nowchat.service.data;

import com.chat.app.nowchat.auth.AuthenticationResponse;
import com.chat.app.nowchat.auth.RegisterRequest;
import com.chat.app.nowchat.jwt.JwtService;
import com.chat.app.nowchat.model.User;
import com.chat.app.nowchat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailService implements UserDetailsService {

    private final UserRepository repository;
    private final JwtService jwtService;

    public AuthenticationResponse registerUser(RegisterRequest registerRequest){
        String jwttoken = null;
        try{
            User registeredUser = User
                    .builder()
                    .email(registerRequest.getEmail())
                    .username(registerRequest.getUsername())
                    .password(registerRequest.getPassword())
                    .build();

            if(!repository.existsByUsername(registeredUser.getUsername())) {
                repository.save(registeredUser);
            }
            log.info("User {} registered", registeredUser.getUsername());
            jwttoken = jwtService.generateToken(registeredUser);
        } catch(DataAccessException e){
            log.error(e.getLocalizedMessage());
        }
        return AuthenticationResponse.builder()
                .accessToken(jwttoken)
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(RuntimeException::new);
    }
}
