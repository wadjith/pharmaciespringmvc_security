package cm.objis.wtt.pharmacie.domaine.dto;

import cm.objis.wtt.pharmacie.domaine.entities.Produit;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ProduitDto {
	
	private String reference;
	private String libelle;
	private double prix;
	private int quantite;
	private PharmacieDto pharmacie;
	
	//Renvoi l'entité correspondant au Dto
	public Produit getEntity() {
		
		return new Produit(this.reference, this.libelle, this.prix, this.quantite);
	}
	
}
