package it.corso.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.model.HomeAddress;
import it.corso.model.ShippingAddress;
import it.corso.model.User;

public interface UserService {
	
	//registrazione user senza token
	ObjectNode userRegistration(User user);
	
	ObjectNode userLogin(User user);
	
	ObjectNode userLogout(String token);
	
	ObjectNode userUpdate(User user, String token , HomeAddress homeAddress);
	//chiedere a Davide se va bene, accettazione di due argomenti in piu 
	

}
