package ltd.jellyfish.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class TokenUtils<T> {

    private long expire;

    private String secret;

    private String header;

    public TokenUtils(long expire, String secret, String header) {
        this.expire = expire;
        this.secret = secret;
        this.header = header;
    }

    public String generateToken(Map<String, T> args, String headerName){
        JwtBuilder jwtBuilder = Jwts.builder();
        Date expireDate = new Date(System.currentTimeMillis() + 1000*expire);
        jwtBuilder.setExpiration(expireDate);
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secret);
        for (Map.Entry<String, T> entry : args.entrySet()){
            jwtBuilder.claim(entry.getKey(), entry.getValue());
        }
        jwtBuilder.setHeaderParam(headerName, header);
        return jwtBuilder.compact();
    }

    private Claims getClaimsByToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token){
        return getClaimsByToken(token).getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public T getTokenValue(String name, String token, Class<? extends T> objectClass){
        return getClaimsByToken(token).get(name, objectClass);
    }
}
