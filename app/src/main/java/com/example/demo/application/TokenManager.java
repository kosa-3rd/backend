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

        String token;
        do{
            token = UUID.randomUUID().toString();
        } while(tokenMap.containsValue(token));
        tokenMap.put(username, token);
        tokenMap.forEach((key,value) -> System.out.println(key + ": " + value)
        );
        return token;
    }

    public String validateToken( String token) {
        tokenMap.forEach((key,value) -> System.out.println(key + ": " + value));
        System.out.println(token);
        if(!tokenMap.containsValue(token)){
            throw new RuntimeException("토큰이 유효하지 않습니다.");
        }
        return tokenMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(token))
                .map(Map.Entry::getKey).findFirst().orElseThrow(()-> new RuntimeException("token user not found"));
    }
}
