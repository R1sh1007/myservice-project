package microservice.service;

import lombok.RequiredArgsConstructor;
import microservice.entity.TokenBlacklist;
import microservice.repository.TokenBlacklistRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {


    private final TokenBlacklistRepository repo;



    public void blacklist(String jti, LocalDateTime expiry
    ){
        TokenBlacklist token = TokenBlacklist.builder()
                .jwtId(jti)
                .expiresAt(expiry)
                .build();
        repo.save(token);

    }



    public boolean isBlacklisted(String jti
    ){
        return repo.existsByJwtId(jti);
    }



}