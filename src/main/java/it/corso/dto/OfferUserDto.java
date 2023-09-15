package it.corso.dto;

import java.util.ArrayList;
import java.util.List;

import it.corso.model.Offer;

public class OfferUserDto 
{
	   private OfferDto offer;
	    private UserDto user;
	    private List<Offer> offers = new ArrayList<>();
	    
	    
		public List<Offer> getOffers() {
			return offers;
		}
		public void setOffers(List<Offer> offers) {
			this.offers = offers;
		}
		public OfferDto getOffer() {
			return offer;
		}
		public void setOffer(OfferDto offer) {
			this.offer = offer;
		}
		public UserDto getUser() {
			return user;
		}
		public void setUser(UserDto user) {
			this.user = user;
		}
	    
}
