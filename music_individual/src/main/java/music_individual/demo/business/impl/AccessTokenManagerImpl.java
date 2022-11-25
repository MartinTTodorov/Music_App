package music_individual.demo.business.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import music_individual.demo.business.IAccessTokenManager;
import music_individual.demo.business.exception.InvalidAccessTokenException;
import music_individual.demo.domain.AccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.io.Decoders;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@CrossOrigin("http://localhost:3000/")
public class AccessTokenManagerImpl implements IAccessTokenManager {

    private final Key key;

    public AccessTokenManagerImpl(@Value("${jwt.secret}") String secretKey){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String encode(AccessToken accessToken) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("role", accessToken.getRole());
        claimsMap.put("userID", accessToken.getUserID());

        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(accessToken.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();


    }

    @Override
    public AccessToken decode(String accessToken) {
        try{
            Jwt jwt = Jwts.parserBuilder().setSigningKey(key).build().parse(accessToken);
            Claims claims = (Claims) jwt.getBody();

            String role = claims.get("role", String.class);
            Integer ID = claims.get("userID", Integer.class);
            return AccessToken.builder()
                    .subject(claims.getSubject())
                    .role(role)
                    .userID(ID)
                    .build();
        }
        catch(JwtException e){
            throw new InvalidAccessTokenException(e.getMessage());
        }
    }
}
