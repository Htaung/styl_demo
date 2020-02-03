package com.styl.demo.util;

import java.util.regex.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@ConfigurationProperties(prefix = "api") 
public class ApiUtil {
	
	private String secretKey;
	
	private String apiKey;

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String signSignature(String data) {
		Claims claims = Jwts.claims();
        claims.put("Signature", data);
//        Date expireDate = dateTimeUtil.addDayToDate(new Date(), 1);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.getSecretKey())
//                .setExpiration(expireDate)
                .compact();	
	}
	
	public String[] getSignSingature(String data) {
		Claims body = Jwts.parser()
			              .setSigningKey(this.getSecretKey())
			              .parseClaimsJws(data)
			              .getBody();

        data = (String) body.get("Signature");
        System.out.println("signature => " + data);
        
        return data.split(Pattern.quote("||"),3);
	}
	
	

}
