package cm.objis.wtt.pharmacie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cm.objis.wtt.pharmacie.domaine.entities.Pharmacie;
import java.lang.String;

public interface PharmacieRepository extends JpaRepository<Pharmacie, Long> {
	
	Pharmacie findByNom(String nom);
	
	//Recherche les pharmacies dont le nom contient un mot cle
	@Query("SELECT p FROM Pharmacie p WHERE p.nom LIKE %:motCle%")
	List<Pharmacie> recherchePharmacieParMotCle(@Param("motCle") String motCle);

}
