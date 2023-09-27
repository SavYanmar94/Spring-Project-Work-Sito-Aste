package it.corso.service;

import java.time.LocalDate;
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
		
		User loggedInUser = userDao.findByAuthToken(token);
		   if (loggedInUser == null) {
		       return responseManager.getResponse(401, "Non sei autorizzato");
		   }
		Optional<Item> itemOptional = itemDao.findById(offer.getItem().getId());
		if(!itemOptional.isPresent())
			return responseManager.getResponse(404, "Oggetto in vendita non trovato");
		Item item = itemOptional.get();
	    User seller = item.getSeller();
	    if (seller.getId() == loggedInUser.getId()) {
	        return responseManager.getResponse(400, "Non puoi fare un'offerta per l'oggetto che stai vendendo");
	    }

	    // Verifica se l'offerta è maggiore o uguale al prezzo iniziale dell'oggetto.
	    if (offer.getAmount() <= item.getAuctionBase()) {
	        return responseManager.getResponse(400, "L'offerta deve essere maggiore al prezzo iniziale dell'oggetto");
	    }
	    //iterare la lista della offerte dell'articolo, trovare quella maggiore , e confrontarla con quella 
	    //che arriva. 
	    // Verifica se l'offerta è maggiore o uguale a tutte le offerte relative a questo oggetto.
	    List<Offer> otherOffers = offerDao.findAllByItemAndState(item, "In Corso");
	    for (Offer otherOffer : otherOffers) {
	        if (offer.getAmount() <= otherOffer.getAmount()) {
	            return responseManager.getResponse(400, "L'offerta deve essere maggiore a tutte le offerte relative a questo oggetto");
	        }
	    }
	    
	    
		offer.setState("In corso");
		offerDao.save(offer);
		return responseManager.getResponse(201, "Offerta Registrata");
	}

	@Override
	public ObjectNode offerUpdate(Offer offer, String token) {
	    // Cambio dello stato dell'offerta in accettata
	    if (userDao.findByAuthToken(token) == null)
	        return responseManager.getResponse(401, "Non Autorizzato");
	    
	    // Cerca l'offerta nel database in base all'ID fornito.
	    Optional<Offer> offerOptional = offerDao.findById(offer.getId());

	    // Se l'offerta non viene trovata, restituisci una risposta di errore.
	    if (!offerOptional.isPresent())
	        return responseManager.getResponse(404, "Offerta Non Trovata");

	    // L'offerta viene accettata
	    Offer existingOffer = offerOptional.get();
	    
	    // Assicurati che lo stato dell'offerta sia "In corso" prima di accettarla.
	    /*if (!existingOffer.getState().equals("In corso"))
	        return responseManager.getResponse(400, "Offerta non valida per l'accettazione");*/
	    
	    // Assicurati che lo stato dell'offerta sia "In corso" prima di accettarla.
	    if (existingOffer.getState().equals("Accettata"))
	        return responseManager.getResponse(400, "L'offerta è già stata accettata!");
	    
	    existingOffer.setState("Accettata"); // Aggiunto per impostare lo stato come "accettata"

	    // Ottieni l'oggetto associato all'offerta.
	    Item associatedItem = existingOffer.getItem();

	    // Verifica se l'oggetto è già stato venduto.
	    if (!associatedItem.getState().equals("Disponibile")) 
	        return responseManager.getResponse(400, "Oggetto già stato venduto");
	        // Cambia lo stato dell'oggetto in "Venduto".
	        associatedItem.setState("Venduto");
	       
	     // Aggiorna la saleDate all'attuale data odierna.
	        associatedItem.setSaleDate(LocalDate.now());
	        // Aggiorna l'oggetto nel database.
	        itemDao.save(associatedItem);
	        
	        /*associatedItem.getOffers().forEach(o -> {
	         *  
				o.setState("Rifiutata"); //R = rifiutata , tutte le altre offerte vengono rifiutate 
				});*/

	    // Tutte le altre offerte associate a questo oggetto (tranne quella già accettata) vengono rifiutate.
	       List<Offer> otherOffers = offerDao.findAllByItemAndState(associatedItem, "In Corso");
	        for (Offer otherOffer : otherOffers) {
	            otherOffer.setState("Rifiutata");
	            offerDao.save(otherOffer);
	        }

	    // Aggiorna l'offerta nel database.
	    offerDao.save(existingOffer);

	    return responseManager.getResponse(200, "Offerta accettata con successo ! stato dell'oggetto aggiornato a venduto.");
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
