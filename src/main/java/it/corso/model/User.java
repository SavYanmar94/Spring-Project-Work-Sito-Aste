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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "entry_date")
	private LocalDate entryDate;
	
	@Pattern(regexp = "[a-zA-Z\\sàèìòù']{1,100}", message = "Errore nel campo nome")
	@Column(name = "name")
	private String name;
	
	@Pattern(regexp = "[a-zA-Z\\sàèìòù']{1,100}", message = "Errore nel campo cognome")
	@Column(name = "lastname")
	private String lastname;
	
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)${1,100}", message = "Errore nel campo email")
	@Column(name = "mail")
	private String mail;
	
	@Pattern(regexp = "[a-zA-Z]{6}\\d{2}[ABCDEHLMPRSTabcdehlmprst]\\d{2}[a-zA-Z]\\d{3}[a-zA-Z]", message = "Errore nel campo codice fiscale")
	@Column(name = "tax_code")
	private String taxcode;
	
	@Pattern(regexp = "[a-zA-Z0-9.]{1,20}", message = "Errore nel campo username")
	@Column(name = "nickname")
	private String nickname;

	@Column(name = "password")
	private String password;
	
	@Column(name = "profile_image")
	private String profileImage;
	
	@Column(name = "profile_type")
	private String profileType;
	
	@Column(name = "auth_token")
	private String authToken;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "home_address", referencedColumnName = "id")
	private HomeAddress homeAddress;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shipping_address", referencedColumnName = "id")
	private ShippingAddress shippingAddress;
	
	@OneToMany
    (
            mappedBy = "offerer",
            cascade = CascadeType.REFRESH,
            fetch = FetchType.EAGER, ///determina il tempo in cui i dati vengono caricati. i tempi possibili sono 2: eager e lazy(pigro) 
            orphanRemoval = true // per far eliminare i dati nel momento in cui viene rimossa l'offerta e quindi non ne avrebbe più uno
    )

    // annotazione che di fatto non mi blocca il vincolo con l'interazione del db ma mi blocca l'interazione con quello specifico attributo
    private List<Offer> offers = new ArrayList<>();
	
	@OneToMany
    (
            mappedBy = "seller",
            cascade = CascadeType.REFRESH,
            fetch = FetchType.EAGER, ///determina il tempo in cui i dati vengono caricati. i tempi possibili sono 2: eager e lazy(pigro) 
            orphanRemoval = true // per far eliminare i dati nel momento in cui viene rimosso l'oggetto e quindi non ne avrebbe più uno
    )

    @JsonIgnore // annotazione che di fatto non mi blocca il vincolo con l'interazione del db ma mi blocca l'interazione con quello specifico attributo
    private List<Item> items = new ArrayList<>();

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

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
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

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	

	
}
