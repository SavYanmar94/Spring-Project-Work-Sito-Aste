package it.corso.dto;

import java.time.LocalDate;

import it.corso.model.HomeAddress;
import it.corso.model.ShippingAddress;

public class SellerDto {

private int id;
	
	private LocalDate entryDate;	
	private String name;
	private String lastname;
	private String mail;
	private String taxcode;
	private String nickname;
	private String profileImage;
	
	private HomeAddress homeAddress;
	private ShippingAddress shippingAddress;
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
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
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
	
	
}
