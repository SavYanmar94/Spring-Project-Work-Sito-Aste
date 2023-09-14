package it.corso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dao.ItemDao;
import it.corso.dao.OfferDao;
import it.corso.dao.UserDao;
import it.corso.helper.ResponseManager;
import it.corso.model.Item;
import it.corso.model.User;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private ResponseManager responseManager;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OfferDao offerDao;

	//registrazione item 
	@Override
	public ObjectNode itemRegistration(Item item, String token) {
		if(userDao.findByAuthToken(token) == null)
			return responseManager.getResponse(401, "Not Authorized");
		Optional<User> userOptional = userDao.findById(item.getUser().getId());
		if(!userOptional.isPresent())
			return responseManager.getResponse(404, "User non trovato");
		 // Imposta lo stato dell'item come "disponibile" e la registra nel database.
		item.setState("disponibile");
		itemDao.save(item);
		return responseManager.getResponse(201, "Articolo Inserito");
	}

	@Override
	public ObjectNode itemUpdate(Item item, String token) {
		// TODO Auto-generated method stub
		return null;
	}

	// rimozione item


	@Override
	public ObjectNode itemRemoval(int id, String token)
	{
		User user = userDao.findByAuthToken(token);
		if(user == null)
			return responseManager.getResponse(401, "Non autorizzato");

		Optional<Item> itemOptional = itemDao.findById(id);
		if(!itemOptional.isPresent())
			return responseManager.getResponse(404, "Prodotto non trovato");

		Item item = itemOptional.get();
		if(item.getUser().getId() != user.getId())  //da rivedere 
			return responseManager.getResponse(401, "Non autorizzato");
		if(item.getState().toLowerCase().equals("v")) // V = venduto
			return responseManager.getResponse(406, "Oggetto non ritrattabile");
		
		user.getItems().remove(item);
		item.getOffers().forEach(o -> {
			o.setItem(null);
			o.setState("R"); //R = ritirata 
			});
		offerDao.saveAll(item.getOffers());
		itemDao.delete(item);
		return responseManager.getResponse(202, "Oggetto ritirato");
	}
	
	// lista item
	@Override
	public List<Item> getItems() {
		return (List<Item>) itemDao.findAll();
	}

}
