package it.corso.dto;

import java.time.LocalDate;
import java.util.List;

public class UserOfferDto 
{
	private int id;
	private LocalDate timing;
	private double amount;
	private String state;
	
	private UserDto user;
//	private Item item;
	private List<ItemDto> items;
	private List<OfferDto> offers;
	
	
	
	
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public List<OfferDto> getOffers() {
		return offers;
	}
	public void setOffers(List<OfferDto> offers) {
		this.offers = offers;
	}
	public List<ItemDto> getItems() {
		return items;
	}
	public void setItems(List<ItemDto> items) {
		this.items = items;
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
