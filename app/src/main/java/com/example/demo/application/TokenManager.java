package com.example.demo.application;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class TokenManager {
    Map<String, String> tokenMap = new ConcurrentHashMap<>();
    public TokenManager(){
    }
    public String getToken(String username){
        return tokenMap.get(username);
    }
    public String generateToken(String username){
        UUID uuid = UUID.randomUUID();
        tokenMap.put(username, uuid.toString());
        return uuid.toString();
    }

    public String validateToken( String token) {
        if(!tokenMap.containsValue(token)){
            throw new RuntimeException("토큰이 유효하지 않습니다.");
        }
        Set<String> collect = tokenMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(token))
                .map(Map.Entry::getKey).collect(Collectors.toSet());
        return collect.iterator().next();
    }
}
