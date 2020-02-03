package com.styl.demo.controller;

import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.styl.demo.util.ApiUtil;

@Controller
public class HomeController {
	
	@Autowired
	private ApiUtil apiUti;

	@GetMapping("/random_number")
	public String getMaxNumber() {
		return "random_number";
	}
	
	@PostMapping("/random_number")
	public String getRandomNumber(@ModelAttribute("maxNumber") String maxNumber,Model model) {
		if(maxNumber != null && !maxNumber.isEmpty()) {
			HttpHeaders headers = new HttpHeaders();
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8080/random?";
			
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			String nonce = System.currentTimeMillis() + "#" + UUID.randomUUID().toString().replace("-","");
			String requestBody = "";
			String queryParam = "max="+ Integer.valueOf(maxNumber);
			String signData = requestBody + "||" + queryParam + "||" +nonce;
			signData = apiUti.signSignature(signData);
			url += queryParam;
			
			headers.set("ApiKey", apiUti.getApiKey());
			headers.set("Nonce", nonce);
			headers.set("Signature", signData);
			
			System.out.println("signData in random_number " + signData);
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
			String responseInString = response.getBody();
			
			model.addAttribute("maxNumber", maxNumber);
			model.addAttribute("randomNumber", responseInString);
		}
		
		return "random_number";
	}
	
	@GetMapping("/sort_number")
	public String getSortNumber() {
		return "sort_number";
	}
	
	@PostMapping("/sort_number")
	public String getSortNumber(@ModelAttribute("numbers") String numbers,Model model) {
		if(numbers != null && !numbers.isEmpty()) {
			
			String temp = numbers.replace("[", "");
			temp = temp.replace("]", "");
			String[] tempArray = temp.split(",");
			Integer[] requestBody = new Integer[tempArray.length];
			int i = 0; 
			for ( String textValue : tempArray ) {
			    requestBody[i] = Integer.parseInt( textValue ); 
			    i++; 
			  } 
			
			
			HttpHeaders headers = new HttpHeaders();
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8080/sort";
			
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			String nonce = System.currentTimeMillis() + "#" + UUID.randomUUID().toString().replace("-","");
			String queryParam = "";
			String signData = numbers + "||" + queryParam + "||" +nonce;
			signData = apiUti.signSignature(signData);
			
			headers.set("ApiKey", apiUti.getApiKey());
			headers.set("Nonce", nonce);
			headers.set("Signature", signData);
			
			
			HttpEntity<Integer[]> requestEntity = new HttpEntity<Integer[]>(requestBody, headers);
			ResponseEntity<Integer[]> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Integer[].class);
			Integer[] responseIntArray = response.getBody();
			System.out.println("responseIntArray " + responseIntArray.length);
			
			temp = "[";
			
			for(Integer res : responseIntArray) {
				temp += res.toString() + ",";
			}
			temp = temp.substring(0, temp.length()-1);
			temp += "]"; 
			
			model.addAttribute("numbers", numbers);
			model.addAttribute("sortedNumber", temp);
		}
		
		return "sort_number";
	}
	
}
