package it.corso.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.model.Offer;

public interface OfferService {
	
	//registrazione offerta 
	ObjectNode offerRegistration(Offer offer, String token);
	
	//modifica offerta , solo lo stato 
	//il programma in automatico la aggiorna 
	ObjectNode offerUpdate(Offer offer,String token);
	
	//eliminazione offerta 
	ObjectNode offerRemoval(int id, String token);
	
	//lista offerte per un item 
	List<Offer> getOffers();
	

}
