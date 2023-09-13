package it.corso.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dao.UserDao;
import it.corso.helper.ResponseManager;
import it.corso.helper.SecurityManager;
import it.corso.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
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
	public ObjectNode userUpdate(User user, String token) {
		if(userDao.findByAuthToken(token) == null)
			return responseManager.getResponse(401, "Non autorizzato");
		Optional<User> userOptional = userDao.findById(user.getId());
		if(!userOptional.isPresent())
			return responseManager.getResponse(404, "User non trovato");
		User existing = userOptional.get();
		//nonmodificareilnickname
		existing.setName(user.getName());
		existing.setLastname(user.getLastname());
		existing.setEntryDate(user.getEntryDate());
		existing.setMail(user.getMail());
		existing.setTaxcode(user.getTaxcode());
		existing.setPassword(user.getPassword());
		existing.setProfileImage(user.getProfileImage());
		userDao.save(existing);
		return responseManager.getResponse(202, "Dati User Aggiornati");
	}

}
