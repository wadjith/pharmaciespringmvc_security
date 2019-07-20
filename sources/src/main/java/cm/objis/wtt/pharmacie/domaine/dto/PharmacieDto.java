package cm.objis.wtt.pharmacie.domaine.dto;

import cm.objis.wtt.pharmacie.domaine.entities.Pharmacie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author thierry WADJI
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacieDto {
	
	private String nom;
	private String adresse;
	private String telephone;
	private int nbProduit;
	
	// Renvoi l'entity correspondant au DTO
	public Pharmacie getEntity() {
		
		return new Pharmacie(this.nom, this.adresse, this.telephone);
	}
	


}
