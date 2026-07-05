package microservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name="refresh_tokens")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken  {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="token_hash")
    private String tokenHash;
    private LocalDateTime expiresAt;
    private boolean revoked;

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
        updatedAt = LocalDateTime.now();
    }



}
