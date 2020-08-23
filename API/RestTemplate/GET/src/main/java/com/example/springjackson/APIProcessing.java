package com.example.springjackson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class APIProcessing {
	
	@Autowired
	RestTemplate restTemplate;
	
	//mapper is required to read JSON response
	ObjectMapper mapper = new ObjectMapper();
	
	String URI = "https://reqres.in/api/users";
	
	@GetMapping("/users")
	public void GetUsers() {
		
		ResponseEntity<String> response = restTemplate.getForEntity(URI, String.class);
		System.out.println(response.getStatusCodeValue()); //print 200
		
		String response1 = restTemplate.getForObject(URI, String.class);
		System.out.println(response1); // returns repose as String in this case, no method to capture response code
		
		try {
			
			//Navigating through Response using JsonNode Tree option
			JsonNode root = mapper.readTree(response.getBody());
			JsonNode data = root.path("data");
			String id = data.get(0).get("id").asText();
			String email = data.get(0).get("email").asText();
			String first_name = data.get(0).get("first_name").asText();
			String last_name = data.get(0).get("last_name").asText();
			
			System.out.println("******************** Printing User information *******************");
			System.out.println("id :" + id );
			System.out.println("email :" + email);
			System.out.println("first_name :" + first_name);
			System.out.println("last_name :" + last_name);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}


