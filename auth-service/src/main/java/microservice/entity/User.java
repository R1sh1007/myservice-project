package microservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name="auth_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique=true)
    private String username;

    @Column(nullable=false,unique=true)
    private String email;

    @Column(name="password_hash")
    private String password;

    private String role;

    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @PrePersist
    public void onCreate(){


        createdAt =
                LocalDateTime.now();


        updatedAt =
                LocalDateTime.now();

    }



    @PreUpdate
    public void onUpdate(){


        updatedAt =
                LocalDateTime.now();

    }

}
