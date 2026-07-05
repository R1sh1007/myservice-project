package microservice.repository;


import microservice.entity.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist,Long>{


    boolean existsByJwtId(String jwtId);


}