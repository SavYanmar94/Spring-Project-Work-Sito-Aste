package it.corso.dto;

import java.time.LocalDate;

public class OfferDto 
{
	 	private int id;
	    private LocalDate timing;
	    private double amount;
	    private String state;
	    private OfferUserDto user;
	    private ItemDto items;
	    
	    
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
		public OfferUserDto getUser() {
			return user;
		}
		public void setUser(OfferUserDto user) {
			this.user = user;
		}
		public ItemDto getItems() {
			return items;
		}
		public void setItems(ItemDto items) {
			this.items = items;
		}
	    
		

}
