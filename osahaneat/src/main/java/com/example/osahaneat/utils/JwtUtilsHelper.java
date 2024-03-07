package com.example.osahaneat.utils;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtilsHelper {

	@Value("${jwt.privateKey}")
	private String privateKey;

	public String generateToken(String data) {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));

		String jws = Jwts.builder().subject(data).signWith(key).compact();

		System.out.println(data +"---------");
		return jws;
	}

	public boolean verifyToken(String token) {

		try {
			SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
			Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
			System.out.println("hehe :" + token);
			return true;
		} catch (JwtException ex) {
			return false;
		}
	}

}
