package microservice.controller;

import lombok.RequiredArgsConstructor;
import microservice.common.ApiResponse;
import microservice.dto.request.ChangePasswordRequest;
import microservice.dto.request.LoginRequest;
import microservice.dto.request.RefreshTokenRequest;
import microservice.dto.request.RegisterRequest;
import microservice.dto.response.AuthResponse;
import microservice.service.AuthService;
import microservice.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String header
    ){
        String token = header.substring(7);
        authService.logout(token);
      //  authService.logout(header.replace("Bearer ",""));
        return ResponseEntity.ok(ApiResponse.success("Logout success", null));

    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validate(@RequestHeader("Authorization") String auth){
        authService.validate(auth.replace("Bearer ",""));
        return ResponseEntity.ok().build();

    }


    @PostMapping("/refresh-token")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request){
        return ResponseEntity.ok(ApiResponse.success("Token refreshed", authService.refresh(request)));

    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestHeader("Authorization")
            String auth,
            @RequestBody
            ChangePasswordRequest request
    ){
        String token = auth.replace("Bearer ", "");
        String username = jwtService.extractUsername(token);
        authService.changePassword(username, request);
        return ResponseEntity.ok(ApiResponse.success("Password changed", null));
    }

}


