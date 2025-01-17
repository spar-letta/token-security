/**
 * 
 */
package com.simiyu.util;

import java.security.KeyStore.SecretKeyEntry;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Generated;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author enock
 *
 */
@Component
public class JwtUtil {

	@Value("${app.secret}")
	private String secret;
	
	//6. validate username in token and database. expDate
	public boolean validateToken(String token, String username) {
		String tokenUserName = getUsername(token);
		return (username.equals(tokenUserName) && !isTokenExp(token));
	}
	//5. Validate Exp Date
	public boolean isTokenExp(String token){
		Date expDate = getExpDate(token);
		return expDate.before(new Date(System.currentTimeMillis()));
	}
	//4. read subject/username
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	//3. read exp Date
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();
	}
	
	//2. read claims
	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
	//1. Generated token
	public String generateToken(String subject) {
		
			return Jwts.builder()
					.setSubject(subject)
					.setIssuer("enocksimiyu")
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
					.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512,secret.getBytes())
					.compact();
			
	}
}
