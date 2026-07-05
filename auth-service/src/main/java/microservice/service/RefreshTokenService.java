package microservice.service;


import lombok.RequiredArgsConstructor;
import microservice.entity.RefreshToken;
import microservice.entity.User;
import microservice.exception.CustomException;
import microservice.exception.ErrorCode;
import microservice.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Value("${jwt.refresh-expiration}")
    private Long refreshExpiry;

    @Value("${jwt.refresh-expiration}")
    private Long expiry;

    public String create(User user){
        String token = UUID.randomUUID()
                        .toString();
        RefreshToken rt = RefreshToken.builder()
                        .user(user)
                        .tokenHash(token)
                        .expiresAt(LocalDateTime.now().plusSeconds(refreshExpiry/1000))
                        .revoked(false)
                        .build();
        repository.save(rt);
        return token;
    }


    public RefreshToken verify(String token){
        RefreshToken refreshToken = repository.findByTokenHashAndRevokedFalse(token)
                        .orElseThrow(()->new CustomException(ErrorCode.INVALID_TOKEN));
        if(refreshToken.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);

        }
        return refreshToken;
    }


}
