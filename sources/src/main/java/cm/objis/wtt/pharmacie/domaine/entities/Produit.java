package cm.objis.wtt.pharmacie.domaine.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import cm.objis.wtt.pharmacie.domaine.dto.ProduitDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe représente les produits qui sont manipulés dans l'application
 * 
 * @author thierry WADJI
 * @version 1.0
 *
 */

@Data
@NoArgsConstructor
@Entity
public class Produit {
	
	/**
	 * Les propriétes d'un produit.
	 * reference: est le numéro de référence du produit
	 * libelle
	 * prix
	 * quantite
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=10, unique=true)
	private String reference;
	
	@Column(length=50)
	private String libelle;
	
	private double prix;
	private int quantite;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Pharmacie pharmacie;
	

	public Produit(String reference, String libelle, double prix, int quantite) {
		super();
		this.reference = reference;
		this.libelle = libelle;
		this.prix = prix;
		this.quantite = quantite;
	}
	
	public ProduitDto getDto() {
		
		return new ProduitDto(this.reference, this.libelle,
				this.prix, this.quantite, this.pharmacie.getDto());
	}

	
}
