package it.corso.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dao.ItemDao;
import it.corso.dao.OfferDao;
import it.corso.dao.UserDao;
import it.corso.helper.ResponseManager;
import it.corso.model.Item;
import it.corso.model.Offer;
import it.corso.model.User;


@Service
public class OfferServiceImpl implements OfferService{
	
	
	@Autowired
	private OfferDao offerDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private ResponseManager responseManager;
	
	private ModelMapper mapper = new ModelMapper();
	

	@Override
	public ObjectNode offerRegistration(Offer offer, String token) {
		if(userDao.findByAuthToken(token) == null)
			return responseManager.getResponse(401, "Non sei autorizzato");
		Optional<User> userOptional = userDao.findById(offer.getUser().getId());
		if(!userOptional.isPresent())
			return responseManager.getResponse(404, "User non trovato");
		Optional<Item> itemOptional = itemDao.findById(offer.getItem().getId());
		if(!itemOptional.isPresent())
			return responseManager.getResponse(404, "Oggetto in vendita non trovato");
		offer.setState("in corso");
		offerDao.save(offer);
		return responseManager.getResponse(201, "Offerta Registrata");
	}

	@Override
	public ObjectNode offerUpdate(Offer offer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectNode offerRemoval(int id, String token) {
		// TODO Auto-generated method stub
		return null;
	}

	// lista offerte
		@Override
		public List<Offer> getOffers() {
			List<Offer> offers = (List<Offer>)offerDao.findAll();
			return offers;		
		}

	
}
