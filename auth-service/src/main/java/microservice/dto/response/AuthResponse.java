package microservice.dto.response;

import lombok.Builder;
import microservice.dto.request.RefreshTokenRequest;
import microservice.entity.RefreshToken;
import microservice.entity.User;

@Builder
public record AuthResponse(

        String accessToken,

        String refreshToken,

        String tokenType

){
}