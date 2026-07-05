package microservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.dto.request.ChangePasswordRequest;
import microservice.dto.request.LoginRequest;
import microservice.dto.request.RefreshTokenRequest;
import microservice.dto.request.RegisterRequest;
import microservice.dto.response.AuthResponse;
import microservice.entity.RefreshToken;
import microservice.entity.User;
import microservice.exception.CustomException;
import microservice.exception.ErrorCode;
import microservice.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final TokenBlacklistService tokenBlacklistService;



    public AuthResponse login(LoginRequest request){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        User user = userRepository.findByUsername(request.username()).orElseThrow();
        String access = jwtService.generateToken(user.getUsername(), user.getRole());
        String refresh = refreshTokenService.create(user);
        return AuthResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .tokenType("Bearer")
                .build();
    }

    public void register(RegisterRequest req){
        if(userRepository.existsByUsername(req.username())){
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        }        User user= User.builder()
                        .username(req.username())
                        .email(req.email())
                        .password(encoder.encode(req.password()))
                        .role("USER")
                        .status("ACTIVE")
                        .build();
        userRepository.save(user);
        log.info("User registered {}", req.username());
    }

    public void logout(String token){
        String jti = jwtService.extractJti(token);
        LocalDateTime expiry = jwtService.extractExpiration(token);
        tokenBlacklistService.blacklist(jti, expiry);


    }

    public void validate(String token){
        if(!jwtService.isValid(token)) throw new CustomException(ErrorCode.INVALID_TOKEN);
        String jti = jwtService.extractJti(token);
        log.info("Checking blacklist jti {}", jti);
        boolean blacklisted = tokenBlacklistService.isBlacklisted(jti);
        if(blacklisted){
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }



    public AuthResponse refresh(RefreshTokenRequest request){
        RefreshToken refreshToken = refreshTokenService.verify(request.refreshToken());
        User user = refreshToken.getUser();
        String accessToken = jwtService.generateToken(user.getUsername(), user.getRole());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(request.refreshToken())
                .tokenType("Bearer")
                .build();
    }



    public void changePassword(String username, ChangePasswordRequest request){
        User user = userRepository.findByUsername(username).orElseThrow();
        if(!encoder.matches(request.oldPassword(), user.getPassword())){throw new CustomException(ErrorCode.INVALID_CREDENTIALS);}
        user.setPassword(encoder.encode(request.newPassword()));
        userRepository.save(user);
    }
}

