package com.blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper {

    public static  final long JWT_TOKEN_VALIDITY = 5*60*60;

    private String secret = "jwtTokenKey";

    //retrieve username from jwt Token
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }
    public <T> T getClaimFromToken (String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for receiving any information from the token we need the secret key
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    //check if the token has expired
    private boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token
    //1- Define claims of the token, like Issure, Expiration, Subject, and th Id
    //2- Sign the JWT  using the HSS12 algorithmand secret key .
    //3- Accoring to JWS Compact Serialization (https://tools.ietf.org/html/draft-ieft-jose)
    //  compacting of the JWT to URL safe string
    private String doGenerateToken(Map<String,Object> claims, String subject){
        return Jwts.builder().
                setClaims(claims).
                setSubject(subject).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*100)).
                signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
