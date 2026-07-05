package microservice.exception;
import lombok.Getter;
import lombok.Setter;


@Getter
public enum ErrorCode {


    USER_ALREADY_EXISTS(
            "AUTH_001",
            "User already exists"
    ),


    INVALID_CREDENTIALS(
            "AUTH_002",
            "Invalid username or password"
    ),


    TOKEN_EXPIRED(
            "AUTH_003",
            "Token expired"
    ),


    INVALID_TOKEN(
            "AUTH_004",
            "Invalid token"
    );



    private final String code;
    private final String message;
    ErrorCode(String code, String message){
        this.code=code;
        this.message=message;
    }


}
