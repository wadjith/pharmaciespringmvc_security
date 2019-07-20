package cm.objis.wtt.pharmacie.domaine.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import cm.objis.wtt.pharmacie.domaine.dto.PharmacieDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author thierry WADJI
 *
 */
@Data
@NoArgsConstructor
@Entity
public class Pharmacie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 50)
	private String nom;
	
	@Column(nullable = false, length = 150)
	private String adresse;
	
	@Column(nullable = false, length = 20)
	private String telephone;
	
	@OneToMany(mappedBy = "pharmacie", cascade = CascadeType.REMOVE)
	private List<Produit> produits;
	
	public Pharmacie(String nom, String adresse, String telephone) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.produits = new ArrayList<Produit>();
	}
	
	public PharmacieDto getDto() {
		
		int nbProduit = this.getProduits().size();
		return new PharmacieDto(this.nom, this.adresse, this.telephone, nbProduit);
	}

}
