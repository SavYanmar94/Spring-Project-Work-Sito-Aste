package it.corso.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;

@Entity
@Table(name = "offers")
public class Offer 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "timing")
	private LocalDate timing;
	
	@Digits(integer = 6, fraction = 2, message = "Errore nel campo importo")
	@Column(name = "amount")
	private double amount;
	

	@Column(name = "state")
	private String state;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private Item item;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "offerer_id", referencedColumnName = "id")
	private User offerer;

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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getOfferer() {
		return offerer;
	}

	public void setOfferer(User offerer) {
		this.offerer = offerer;
	}

	


	
}

