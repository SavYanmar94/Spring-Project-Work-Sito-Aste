package it.corso.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "items")
public class Item 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "placement_date")
	private LocalDate placementDate;
	
	@Column(name = "sale_date")
	private LocalDate saleDate;
	
	@Pattern(regexp = "[a-zA-Z\\sàèìòù']{1,100}", message = "Errore nel campo nome")
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Digits(integer = 6, fraction = 2, message = "Errore nel campo asta")
	@Column(name = "auction_base")
	private double auctionBase;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "state")
	private String state;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "seller_id", referencedColumnName = "id")
	private User seller;
	
	@OneToMany
    (
            mappedBy = "item",
            cascade = CascadeType.REFRESH,
            fetch = FetchType.EAGER, ///determina il tempo in cui i dati vengono caricati. i tempi possibili sono 2: eager e lazy(pigro) 
            orphanRemoval = true // per far eliminare i dati nel momento in cui viene rimosso l'autore e quindi non ne avrebbe più uno
    )

    @JsonIgnore // annotazione che di fatto non mi blocca il vincolo con l'interazione del db ma mi blocca l'interazione con quello specifico attributo
    private List<Offer> offers = new ArrayList<>();

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

	
	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	
	
}
