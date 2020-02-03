package com.styl.demo.authentication;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.styl.demo.util.ApiUtil;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
	
	@Autowired
	private ApiUtil apiUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			   throws Exception {
		System.out.println("API preHandle is worked!");

		String apiKey = request.getHeader("ApiKey");
				
		if(!apiUtil.getApiKey().equals(apiKey)) {
			return false;
		}
		
		return validateSignature(request);
	}
	
	private boolean validateSignature(HttpServletRequest request) throws IOException {
		String nonce = request.getHeader("Nonce");
		String signature = request.getHeader("Signature");
		String queryParams = request.getQueryString() != null ?request.getQueryString() : "";
		boolean result = true;
		String[] signData = apiUtil.getSignSingature(signature);
		
		if(signData[1].equals(queryParams) && signData[2].equals(nonce)) {
			String nonceData[] = nonce.split("#",2);
			
			if(!(nonceData[1].length() >= 16 && nonceData[1].length() <= 64)) {
				result = false;
			}
			
			Timestamp requestTime = new Timestamp(Long.valueOf(nonceData[0]));
			Duration duration = Duration.between(requestTime.toLocalDateTime(), LocalDateTime.now().plusSeconds(120));
			System.out.println("duration " + duration.getSeconds());
			if(duration.getSeconds() > 120l) {
				result = false;
			}
		}else {
			result = false;
		}
		
		return result;
	}
}
