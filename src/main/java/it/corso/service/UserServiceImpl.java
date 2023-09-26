package it.corso.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dao.HomeAddressDao;
import it.corso.dao.OfferDao;
import it.corso.dao.ShippingAddressDao;
import it.corso.dao.UserDao;
import it.corso.dto.UserDto;
import it.corso.dto.UserItemDto;
import it.corso.dto.UserItemOfferDto;
import it.corso.helper.ResponseManager;
import it.corso.helper.SecurityManager;
import it.corso.model.HomeAddress;
import it.corso.model.Offer;
import it.corso.model.ShippingAddress;
import it.corso.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OfferDao offerDao;
	
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
		
		 //USER  Converto la prima lettera del nome iniziale in maiuscolo ed il resto minuscolo, prima verifico l'esistenza del campo
	    if (user.getName() != null && !user.getName().isEmpty()) {
	        user.setName(user.getName().substring(0, 1).toUpperCase() + user.getName().substring(1).toLowerCase());
	    }

	    // Converto la prima lettera del cognome iniziale in maiuscolo ed il resto minuscolo
	    if (user.getLastname() != null && !user.getLastname().isEmpty()) {
	        user.setLastname(user.getLastname().substring(0, 1).toUpperCase() + user.getLastname().substring(1).toLowerCase());
	    }

	    //HOMEADDRESS  Converto il campo 'town' in tutto maiuscolo
	    if (user.getHomeAddress() != null && user.getHomeAddress().getTown() != null) {
	        user.getHomeAddress().setTown(user.getHomeAddress().getTown().substring(0, 1).toUpperCase() + user.getHomeAddress().getTown().substring(1).toLowerCase());

	    }

	    // Converto il campo 'province' in tutto maiuscolo
	    if (user.getHomeAddress() != null && user.getHomeAddress().getProvince() != null) {
		   user.getHomeAddress().setProvince(user.getHomeAddress().getProvince().toUpperCase());

	    }
		
	    // SHIPPINGADDRESS Converto il campo 'town' iniziale maiuscolo
	    if (user.getShippingAddress() != null && user.getShippingAddress().getTown() != null) {
	        user.getShippingAddress().setTown(user.getShippingAddress().getTown().substring(0, 1).toUpperCase() + user.getHomeAddress().getTown().substring(1).toLowerCase());

	    }

	    // Converto il campo 'province' maiuscolo
	    if (user.getShippingAddress() != null && user.getShippingAddress().getProvince() != null) {
			user.getShippingAddress().setProvince(user.getShippingAddress().getProvince().toUpperCase());
	       
	    }
	    
		user.setPassword(SecurityManager.encode(user.getPassword()));
		user.setEntryDate(LocalDate.now());
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
	        if (userDao.findByAuthToken(token) == null)
	            return responseManager.getResponse(401, "Non autorizzato");
	        Optional<User> userOptional = userDao.findById(user.getId());
	        if (!userOptional.isPresent())
	            return responseManager.getResponse(404, "User non trovato");
	        User existing = userOptional.get();

	        // Aggiornamento dei campi utente
	        existing.setName(user.getName().substring(0, 1).toUpperCase() + user.getName().substring(1).toLowerCase());
	        existing.setLastname(user.getLastname().substring(0, 1).toUpperCase() + user.getLastname().substring(1).toLowerCase());
	        //existing.setEntryDate(user.getEntryDate());
	        existing.setMail(user.getMail());
	        existing.setTaxcode(user.getTaxcode());
	        existing.setPassword(SecurityManager.encode(user.getPassword()));
	        existing.setProfileImage(user.getProfileImage());
	        
	        Optional<HomeAddress> homeAddressOptional = homeAddressDao.findById(user.getHomeAddress().getId());
	        if (!homeAddressOptional.isPresent())
	            return responseManager.getResponse(404, "Indirizzo di fatturazione non trovato");
	        
	        HomeAddress existingHA = homeAddressOptional.get();

	            existingHA.setStreet(user.getHomeAddress().getStreet());
	            existingHA.setCivic(user.getHomeAddress().getCivic());
	            existingHA.setCap(user.getHomeAddress().getCap());
	            existingHA.setTown(user.getHomeAddress().getTown().substring(0, 1).toUpperCase() + user.getHomeAddress().getTown().substring(1).toLowerCase());
	            existingHA.setProvince(user.getHomeAddress().getProvince().toUpperCase());
	        
	        
	        Optional<ShippingAddress> shippingAddressOptional = shippingAddressDao.findById(user.getShippingAddress().getId());
		    if (!shippingAddressOptional.isPresent())
		        return responseManager.getResponse(404, "Indirizzo di consegna non trovato");  
	           
		    ShippingAddress existingSA = shippingAddressOptional.get();

            existingSA.setStreet(user.getShippingAddress().getStreet());
            existingSA.setCivic(user.getShippingAddress().getCivic());
            existingSA.setCap(user.getShippingAddress().getCap());
            existingSA.setTown(user.getShippingAddress().getTown().substring(0, 1).toUpperCase() + user.getShippingAddress().getTown().substring(1).toLowerCase());
            existingSA.setProvince(user.getShippingAddress().getProvince().toUpperCase());
		    existing.setHomeAddress(existingHA);
		    existing.setShippingAddress(existingSA);
	        userDao.save(existing);
	        //homeAddressDao.save(existingHA);
	        //shippingAddressDao.save(existingSA);
	        return responseManager.getResponse(202, "Dati User Aggiornati");
	    }

	/*@Override
	public List<UserDto> getUsers(String token) {
		List<UserDto> usersDto = new ArrayList<>();
		if(userDao.findByAuthToken(token) == null)
			return usersDto;
		List<User> users = (List<User>) userDao.findAll();
		users.forEach(u -> usersDto.add(mapper.map(u, UserDto.class)));
		return usersDto;
	}*/
	
	@Override
	public UserDto getUserData(String token) {
		User user = userDao.findByAuthToken(token);
		if(user == null)
			return new UserDto();
		UserDto userDto = mapper.map(user, UserDto.class);
		userDto.setPassword(SecurityManager.decode(userDto.getPassword()));
		
		
		
		List<UserItemDto> items = userDto.getItems();
		
		for (UserItemDto item : items) {
			
			double majorOffer = 0;
			
			for (UserItemOfferDto offer : item.getOffers()) {
				if (offer.getAmount() > majorOffer) {
					majorOffer = offer.getAmount();
				}
			}
			
			item.setMajorOffer(majorOffer);
		}
		
		/*userDto.getOffers().forEach(o -> {
			if(o.getItem() != null)
			{
				List<Offer> allOffers = offerDao.findByItemId(o.getItem().getId());
				OptionalDouble majorOffer = allOffers
						.stream()
						.mapToDouble(Offer::getAmount)
						.max();
				if(majorOffer.isPresent())
					o.getItem().setMajorOffer(majorOffer.getAsDouble());
			}
		});*/

		
		return userDto;
	}

}
