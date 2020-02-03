package com.styl.demo.controller;

import java.util.Arrays;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeApiController {

	@GetMapping("/random")
	public @ResponseBody String getRandomNumber(@RequestParam(value="max", required = true) Integer max){
		Random rand = new Random(); 
		
		return String.valueOf(rand.nextInt(max-1));
	}
	
	@PostMapping("/sort")
	public @ResponseBody Integer[] sortNumber(@RequestBody Integer[] numbers){
		Arrays.sort(numbers);
		System.out.println("sorted number " + numbers.length);
		return numbers;		
	}
}
