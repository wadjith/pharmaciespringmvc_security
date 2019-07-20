package cm.objis.wtt.pharmacie.service;

import java.util.List;

import cm.objis.wtt.pharmacie.domaine.entities.Pharmacie;
import cm.objis.wtt.pharmacie.domaine.entities.Produit;

/**
 * 
 * Interface de service de sur les Produits et Pharmacies
 * 
 * @author thierry WADJI
 *
 */
public interface IService {

	/**
	 * Méthode d'enregistrement de produit
	 * 
	 * @param produit est un objet de la classe Produit
	 */
	public Produit enregistrerProduit(Produit produit);
	
	/**
	 * Méthode d'enregistrement d'une Pharmacie
	 * 
	 * @param pharmacie est un objet de la classe Pharmacie
	 */
	public Pharmacie enregistrerPharmacie(Pharmacie pharmacie);

	/**
	 * Méthode de listing des produits enregistrés en BD
	 * 
	 * @return List de Produit
	 */
	public List<Produit> listProduit();
	
	/**
	 * Méthode de listing des produits d'une pharmacie
	 * 
	 * @return List de Produit
	 */
	public List<Produit> listProduit(Pharmacie pharmacie);
	
	/**
	 * Méthode de listing des pharmacies enregistrés en BD
	 * 
	 * @return List de Pharmacie
	 */
	public List<Pharmacie> listPharmacie();

	/**
	 * Méthode de recherche de produit par sa référence
	 * 
	 * @param ref reférence du produit
	 * @return Produit objet de la classe Produit
	 */
	public Produit rechercheProduit(String ref);
	
	/**
	 * Méthode de recherche d'une pharmacie dont le nom contient un mot clé
	 * 
	 * @param motCle du nom de la pharmacie
	 * @return List<Pharmacie> Liste de Pharmacie
	 */
	public List<Pharmacie> recherchePharmacieParMotCle(String motCle);
	
	/**
	 * Méthode de recherche de produit contenant un mot clé
	 * 
	 * @param motCle du produit
	 * @return List<Produit> Liste de Produit
	 */
	public List<Produit> rechercheProduitParMotCle(String motCle);

	/**
	 * Méthode qui vérifie si un produit de reférence donnée est déjà enregistré en
	 * BD
	 * 
	 * @param ref référence du produit
	 * @return le produit s'il est dejà enregistrée en BD ou null au cas
	 *         contraire
	 */
	public Produit produitExiste(String ref);
	
	/**
	 * Méthode qui vérifie si une pharmacie de reférence donnée est déjà enregistré en
	 * BD
	 * 
	 * @param nom de la pharmacie
	 * @return la pharmacie si elle est dejà enregistrée ou null au cas
	 *         contraire
	 */
	public Pharmacie pharmacieExiste(String nom);
	
	/**
	 * Méthode de modification d'un produit
	 * 
	 * @param produit est un objet de la classe Produit
	 */
	public Produit modifierProduit(Produit produit);
	
	/**
	 * Méthode de modification d'une Pharmacie
	 * 
	 * @param pharmacie est un objet de la classe Pharmacie
	 */
	public Pharmacie modifierPharmacie(Pharmacie pharmacie);

}
