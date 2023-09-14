package it.corso.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dao.HomeAddressDao;
import it.corso.dao.ShippingAddressDao;
import it.corso.dao.UserDao;
import it.corso.helper.ResponseManager;
import it.corso.helper.SecurityManager;
import it.corso.model.HomeAddress;
import it.corso.model.ShippingAddress;
import it.corso.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private HomeAddressDao homeAddressDao;
	
	@Autowired 
	private ShippingAddressDao shippingAddressDao;
	
	@Autowired
	private ResponseManager responseManager;
	
	private ModelMapper mapper = new ModelMapper();
	
	
	
	@Override
	public ObjectNode userRegistration(User user) {
		
		if(userDao.findByNickname(user.getNickname()) != null)
			return responseManager.getResponse(406, "Nickname già esistente");
		user.setPassword(SecurityManager.encode(user.getPassword()));
		userDao.save(user);
		return responseManager.getResponse(201, "User registrato correttamente");
	}

	@Override
	public ObjectNode userLogin(User user) {
		User existing = userDao.findByNicknameAndPassword(user.getNickname()
				, SecurityManager.encode(user.getPassword()));
		if(existing == null)
			return responseManager.getResponse(401, "Non sei autorizzato");
		existing.setAuthToken(SecurityManager.generateToken(existing.getNickname()));
		userDao.save(existing);
		return responseManager.getResponse(existing.getId(), existing.getAuthToken());
	}

	@Override
	public ObjectNode userLogout(String token) {
		User existing = userDao.findByAuthToken(token);
		if(existing == null)
			return responseManager.getResponse(401, "Non autorizzato");
		existing.setAuthToken(null);
		userDao.save(existing);
		return responseManager.getResponse(202, "Logout accettato");
	}

	 @Override
	    public ObjectNode userUpdate(User user, String token,HomeAddress homeAddress,ShippingAddress shippingAddress) {
	        if (userDao.findByAuthToken(token) == null)
	            return responseManager.getResponse(401, "Non autorizzato");
	        Optional<User> userOptional = userDao.findById(user.getId());
	        if (!userOptional.isPresent())
	            return responseManager.getResponse(404, "User non trovato");
	        User existing = userOptional.get();

	        // Aggiornamento dei campi utente
	        existing.setName(user.getName());
	        existing.setLastname(user.getLastname());
	        existing.setEntryDate(user.getEntryDate());
	        existing.setMail(user.getMail());
	        existing.setTaxcode(user.getTaxcode());
	        existing.setPassword(user.getPassword());
	        existing.setProfileImage(user.getProfileImage());
	        
	        Optional<HomeAddress> homeAddressOptional = homeAddressDao.findById(user.getId());
	        if (!homeAddressOptional.isPresent())
	            return responseManager.getResponse(404, "Indirizzo di fatturazione non trovato");
	        
	        HomeAddress existingHA = homeAddressOptional.get();

	            existingHA.setStreet(homeAddress.getStreet());
	            existingHA.setCivic(homeAddress.getCivic());
	            existingHA.setCap(homeAddress.getCap());
	            existingHA.setTown(homeAddress.getTown());
	            existingHA.setProvince(homeAddress.getProvince());
	        
	        
	        Optional<ShippingAddress> shippingAddressOptional = shippingAddressDao.findById(user.getId());
		    if (!shippingAddressOptional.isPresent())
		        return responseManager.getResponse(404, "Indirizzo di consegna non trovato");  
	           
		    ShippingAddress existingSA = shippingAddressOptional.get();

            existingSA.setStreet(shippingAddress.getStreet());
            existingSA.setCivic(shippingAddress.getCivic());
            existingSA.setCap(shippingAddress.getCap());
            existingSA.setTown(shippingAddress.getTown());
            existingSA.setProvince(shippingAddress.getProvince());
		    
	        userDao.save(existing);
	        homeAddressDao.save(existingHA);
	        shippingAddressDao.save(existingSA);
	        return responseManager.getResponse(202, "Dati User Aggiornati");
	    }

}
