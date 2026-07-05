package microservice.entity;

import jakarta.persistence.*;
import lombok.Getter;


import java.time.LocalDateTime;


@Getter
@MappedSuperclass
public abstract class BaseEntity {



    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;




    @Column(
            name="created_at",
            updatable=false
    )
    private LocalDateTime createdAt;



    @Column(
            name="updated_at"
    )
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