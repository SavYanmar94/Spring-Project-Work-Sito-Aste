package it.corso.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import it.corso.dto.UserDto;
import it.corso.model.User;

public interface UserService {
	
	//registrazione user senza token
	ObjectNode userRegistration(User user);
	
	ObjectNode userLogin(User user);
	
	ObjectNode userLogout(String token);
	
	ObjectNode userUpdate(User user, String token);
	
	//List<UserDto> getUsers(String token);

	UserDto getUserData(String token);

}
