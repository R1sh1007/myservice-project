package microservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;



@Entity
@Table(name="token_blacklist")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenBlacklist  {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name="jwt_id", unique=true)
    private String jwtId;

    @Column(name="expires_at", nullable=false)
    private LocalDateTime expiresAt;

    @Column(name="created_at", updatable=false)
    private LocalDateTime createdAt;



    @Column(name="updated_at")
    private LocalDateTime updatedAt;



    @PrePersist
    public void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }



    @PreUpdate
    public void onUpdate(){


        updatedAt =
                LocalDateTime.now();

    }



}