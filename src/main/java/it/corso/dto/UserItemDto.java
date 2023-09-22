package it.corso.dto;

import java.time.LocalDate;
import java.util.List;

public class UserItemDto 
{
	private int id;
	private LocalDate placementDate;
	private LocalDate saleDate;
	private String name;
	private String description;
	private double auctionBase;
	private String image;
	private String state; 
	private double majorOffer = 0;
	
	
	private List<UserItemOfferDto> offers;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getPlacementDate() {
		return placementDate;
	}
	public void setPlacementDate(LocalDate placementDate) {
		this.placementDate = placementDate;
	}
	public LocalDate getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAuctionBase() {
		return auctionBase;
	}
	public void setAuctionBase(double auctionBase) {
		this.auctionBase = auctionBase;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<UserItemOfferDto> getOffers() {
		return offers;
	}
	public void setOffers(List<UserItemOfferDto> offers) {
		this.offers = offers;
	}
	public double getMajorOffer() {
		return majorOffer;
	}
	public void setMajorOffer(double majorOffer) {
		this.majorOffer = majorOffer;
	}
	
	

	
}
