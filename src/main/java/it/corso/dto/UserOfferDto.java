package it.corso.dto;

import java.time.LocalDate;

public class UserOfferDto 
{
	private int id;
	private LocalDate timing;
	private double amount;
	private String state;
	
    private ItemDto item;


	public ItemDto getItem() {
		return item;
	}
	public void setItem(ItemDto item) {
		this.item = item;
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
