package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.helper.ResponseManager;
import it.corso.model.Offer;
import it.corso.service.OfferService;

@RestController
@RequestMapping("/auctions/offer")
@CrossOrigin(origins = {"*"})
public class OfferController {

	@Autowired
	private OfferService offerService;
	
	@Autowired
	private ResponseManager responseManager;
	
	// endpoint #1: registrazione offerta localhost:8080/auctions/offer/reg/{offertoken}
	@PostMapping("/reg/{tkn}")
	public ResponseEntity<ObjectNode> offerRegistration(@RequestBody Offer offer,
			@PathVariable("tkn") String token)
	{
		ObjectNode response = offerService.offerRegistration(offer, token);
		return new ResponseEntity<ObjectNode>(response, HttpStatusCode.valueOf(response.get("code").asInt()));
	}
	//endpoint n.2 stampa lista offerte : localhost:8080/auctions/offer/get
	@GetMapping("/get")
	public ResponseEntity<List<Offer>> getOffers() 
	{
			
	     List<Offer> response = offerService.getOffers();
		 return new ResponseEntity<List<Offer>>(response, HttpStatus.OK);
	}
	
	//endpoint n 3 update offerta 
	//endpoint #4: modifica dati offerta localhost:8080/auctions/offer/update/{usertoken}
	
	@PutMapping("/update/{tkn}")
	public ResponseEntity<ObjectNode> offerUpdate(@RequestBody Offer offer, @PathVariable("tkn") String token) {
	    ObjectNode response = offerService.offerUpdate(offer, token);
	    return new ResponseEntity<ObjectNode>(response, HttpStatus.valueOf(response.get("code").asInt()));
	}
	
}
