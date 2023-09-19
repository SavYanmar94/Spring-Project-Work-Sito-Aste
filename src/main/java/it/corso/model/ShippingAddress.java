package it.corso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table( name = "shipping_addresses")
public class ShippingAddress 
{
	
	//pojo
		//attributi
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private int id;
			
			@Pattern(regexp = "[a-zA-Z0-9\\sàèìòù'.]{1,50}", message = "Errore nel campo indirizzo")
			@Column(name = "street")
			private String street;
			
			@Pattern(regexp = "[a-zA-Z0-9/_-]{1,20}", message = "Errore nel campo num. civico")
			@Column(name = "civic")
			private String civic;
			
			@Pattern(regexp = "[0-9]{5}", message = "CAP non valido")
			@Column(name = "cap")
			private String cap;
			
			@Pattern(regexp = "[a-zA-Z0-9\\sàèìòù'.]{1,50}", message = "Errore nel campo città")
			@Column(name = "town")
			private String town;
			
			@Pattern(regexp = "[a-zA-Z]{2}", message = "Errore nel campo provincia")
			@Column(name = "province")
			private String province;
			

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getStreet() {
				return street;
			}

			public void setStreet(String street) {
				this.street = street;
			}

			public String getCivic() {
				return civic;
			}

			public void setCivic(String civic) {
				this.civic = civic;
			}

			public String getCap() {
				return cap;
			}

			public void setCap(String cap) {
				this.cap = cap;
			}

			public String getTown() {
				return town;
			}

			public void setTown(String town) {
				this.town = town;
			}

			public String getProvince() {
				return province;
			}

			public void setProvince(String province) {
				this.province = province;
			}
			
}
