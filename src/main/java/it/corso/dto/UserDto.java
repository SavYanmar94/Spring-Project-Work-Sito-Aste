package it.corso.dto;

import java.time.LocalDate;
import java.util.List;

public class UserDto 
{
	  	private int id;
	    private LocalDate timing;
	    private double amount;
	    private String state;
	    private List<UserOfferDto> offers;
	    private List<UserItemDto> items;
	    
	    
	    
		public List<UserItemDto> getItems() {
			return items;
		}
		public void setItems(List<UserItemDto> items) {
			this.items = items;
		}
		public List<UserOfferDto> getOffers() {
			return offers;
		}
		public void setOffers(List<UserOfferDto> offers) {
			this.offers = offers;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public LocalDate getTiming() {
			return timing;
		}
		public void setTiming(LocalDate timing) {
			this.timing = timing;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
	    

}
