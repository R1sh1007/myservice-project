package microservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenCleanupScheduler {



    @Scheduled(
            cron="0 0 0 * * *"
    )
    public void clean(){
        System.out.println("Cleaning expired token");

    }

}
