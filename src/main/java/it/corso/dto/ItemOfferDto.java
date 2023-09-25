package it.corso.dto;

import java.time.LocalDate;

public class ItemOfferDto {

	private int id;
	private LocalDate timing;
	private double amount;
	private String state;
	

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
