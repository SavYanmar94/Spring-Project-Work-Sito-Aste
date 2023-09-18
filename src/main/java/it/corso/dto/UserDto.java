package it.corso.dto;

import java.time.LocalDate;
import java.util.List;

import it.corso.model.HomeAddress;
import it.corso.model.ShippingAddress;

public class UserDto 
{
	private int id;
	
	private LocalDate entryDate;	
	private String name;
	private String lastname;
	private String mail;
	private String taxcode;
	private String nickname;
	private String password;
	private String profileImage;
	private String profileType;
	
	private HomeAddress homeAddress;
	private ShippingAddress shippingAddress;
	
	private List<UserOfferDto> offers;
	private List<UserItemDto> items;
	
	    
	    
	    
		public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTaxcode() {
		return taxcode;
	}
	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getProfileType() {
		return profileType;
	}
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}
	public HomeAddress getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(HomeAddress homeAddress) {
		this.homeAddress = homeAddress;
	}
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
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
		

}
