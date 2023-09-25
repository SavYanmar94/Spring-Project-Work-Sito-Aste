package it.corso.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dao.ItemDao;
import it.corso.dao.OfferDao;
import it.corso.dao.UserDao;
import it.corso.dto.ItemDto;

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
	
	private ModelMapper mapper = new ModelMapper();


	//registrazione item 
	@Override
	public ObjectNode itemRegistration(Item item, String token) {
		if(userDao.findByAuthToken(token) == null)
			return responseManager.getResponse(401, "Not Authorized");
		Optional<User> userOptional = userDao.findById(item.getSeller().getId());
		if(!userOptional.isPresent())
			return responseManager.getResponse(404, "User non trovato");
		 // Imposta lo stato dell'item come "disponibile" e la registra nel database.
		item.setState("disponibile");
		itemDao.save(item);
		return responseManager.getResponse(201, "Articolo Inserito");
	}

	@Override
	public ObjectNode itemUpdate(Item item, String token) {
		//solo lo status 
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
		if(item.getSeller().getId() != user.getId())  //da rivedere 
			return responseManager.getResponse(401, "Non autorizzato");
		if(item.getState().toLowerCase().equals("v")) // V = venduto
			return responseManager.getResponse(406, "Oggetto non ritrattabile");
		
		user.getItems().remove(item);
		item.getOffers().forEach(o -> {
			o.setItem(null);
			o.setState("R"); //R = rifiutata , tutte le altre offerte vengono rifiutate 
			});
		offerDao.saveAll(item.getOffers());
		itemDao.delete(item);
		return responseManager.getResponse(202, "Oggetto ritirato");
	}

	@Override
	public List<ItemDto> getItems() {
		List<ItemDto> itemsDto = new ArrayList<>();
		List<Item> items = (List<Item>) itemDao.findAll();
		items.forEach(o -> itemsDto.add(mapper.map(o, ItemDto.class)));
		return itemsDto;
	}
	
	
	/*// lista item
	@Override
	public List<ItemDto> getItems() {
		List<ItemDto> itemsDto = new ArrayList<>();
		List<Item> items = (List<Item>) itemDao.findAll();
		items.forEach(o -> itemsDto.add(mapper.map(o, ItemDto.class)));
		return itemsDto;
		
		
	}*/

//	@Override
//	public List<ItemDto> getItemUser( String token) 
//	{
//		List<ItemDto> itemsDto = new ArrayList<>();
//		if(userDao.findByAuthToken(token) == null)
//			return itemsDto;
//		List<Item> items = (List<Item>) itemDao.findAll();
//		items.forEach(i -> itemsDto.add(mapper.map(i, ItemDto.class)));
//		return itemsDto;
//	}

}
