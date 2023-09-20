package it.corso.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dto.UserDto;
import it.corso.helper.PasswordValidationException;
import it.corso.model.User;
import it.corso.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auctions/user")
@CrossOrigin(origins = {"*"})
public class UserController {

	@Autowired
	private UserService userService;
	
	// endpoint #1: registrazione cliente  localhost:8080/auctions/user/reg
	@PostMapping("/reg")
	public ResponseEntity<ObjectNode> userRegistration(@Valid @RequestBody User user)
	{
		if(!Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,10}", 
				user.getPassword()))
			throw new PasswordValidationException();
		ObjectNode response = userService.userRegistration(user);
		
		return new ResponseEntity<ObjectNode>(response,
				HttpStatusCode.valueOf(response.get("code").asInt()));
		
	}
	
	// endpoint #2: login cliente  localhost:8080/auctions/user/login
	
	@PutMapping("/login")
	public ResponseEntity<ObjectNode> userLogin(@RequestBody User user)
	{
		ObjectNode response = userService.userLogin(user);
		int httpCode = response.get("message").asText().equals("Non sei autorizzato") ? 401 : 202;
		return new ResponseEntity<ObjectNode>(response, HttpStatusCode.valueOf(httpCode));
	}
	
	
	// endpoint #3: logout cliente  localhost:8080/auctions/user/logout/{user token}
	@GetMapping("/logout/{tkn}")
	public ResponseEntity<ObjectNode> customerLogout(@PathVariable("tkn") String token)
	
	{
		ObjectNode response = userService.userLogout(token);
		return new ResponseEntity<ObjectNode>(response,
				HttpStatusCode.valueOf(response.get("code").asInt()));
	}
	
	//endpoint #4: modifica dati user tranne nickname e status localhost:8080/auctions/user/update/{usertoken}
	
		@PutMapping("/update/{tkn}")
		public ResponseEntity<ObjectNode> userDataUpdate(@Valid @RequestBody User user, @PathVariable("tkn") String token)
		{
			ObjectNode response = userService.userUpdate(user, token);
			return new ResponseEntity<ObjectNode>(response, HttpStatusCode.valueOf(response.get("code").asInt()));
		}
	
		
	  //endpoint #5 : dati completi utente loggato
		
		// endpoint #5: dati clienti loggato localhost:8080/auctions/user/get/{user token}
		@GetMapping("/get/{tkn}")
		public ResponseEntity<UserDto> getUserData(@PathVariable("tkn") String token)
		{
			UserDto response = userService.getUserData(token);
			return new ResponseEntity<UserDto>(response, HttpStatus.OK);
		}
		
		
		
	// metodo per gestione eccezione validazione password
		@ExceptionHandler(PasswordValidationException.class)
		public ResponseEntity<String> handlePasswordValidationException(PasswordValidationException e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		// metodo per gestione eccezione validazione dati generali
		@ExceptionHandler(BindException.class)
		public ResponseEntity<Map<String, String>> handleValidationException(BindException e)
		{
			Map<String, String> errors = new HashMap<>();
			e.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errors);
		}
	
}
