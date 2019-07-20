package cm.objis.wtt.pharmacie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cm.objis.wtt.pharmacie.domaine.entities.Produit;
import cm.objis.wtt.pharmacie.domaine.entities.Pharmacie;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
	
	public Produit findByReference(String ref);
	
	//List<Produit> findByPharmacie(Pharmacie pharmacie);
	
	//Recherche les produits dont le nom contient un mot cle
	@Query("SELECT p FROM Produit p WHERE p.libelle LIKE %:motCle%")
	List<Produit> rechercheProduitParMotCle(@Param("motCle") String motCle);

}
