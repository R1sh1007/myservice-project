package com.microservice.filter;

import com.microservice.client.AuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter {

    //https://github.com/R1sh1007/myservice-project.git
    //private final AuthClient authClient;
    private final ObjectProvider<AuthClient> authClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain

    ) {
        String path = exchange
                .getRequest()
                .getURI()
                .getPath();

// public routes
        if (path.contains("/api/v1/auth")) {
            return chain.filter(exchange);
        }

        String token = exchange
                        .getRequest()
                        .getHeaders()
                        .getFirst("Authorization");

        if (token == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        try {
            authClient.getObject().validate(token);
        } catch (Exception e) {
            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange
                    .getResponse()
                    .setComplete();
        }
        return chain.filter(exchange);
    }

}