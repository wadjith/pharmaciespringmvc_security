package cm.objis.wtt.pharmacie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.objis.wtt.pharmacie.domaine.entities.Pharmacie;
import cm.objis.wtt.pharmacie.domaine.entities.Produit;
import cm.objis.wtt.pharmacie.repository.PharmacieRepository;
import cm.objis.wtt.pharmacie.repository.ProduitRepository;

@Service
public class ServiceImpl implements IService {
	
	private final ProduitRepository produitRepository;
	private final PharmacieRepository pharmacieRepository;
	
	@Autowired
	public ServiceImpl(ProduitRepository pRepository, PharmacieRepository phRepository) {
		
		this.produitRepository = pRepository;
		this.pharmacieRepository = phRepository;
	}

	@Override
	public Produit enregistrerProduit(Produit produit) {
		
		return produitRepository.save(produit);
	}

	@Override
	public Pharmacie enregistrerPharmacie(Pharmacie pharmacie) {
		
		return pharmacieRepository.save(pharmacie);
	}

	@Override
	public List<Produit> listProduit() {
		// TODO Auto-generated method stub
		return produitRepository.findAll();
	}

	@Override
	public List<Produit> listProduit(Pharmacie pharmacie) {
		// TODO Auto-generated method stub
		return pharmacie.getProduits();
	}

	@Override
	public List<Pharmacie> listPharmacie() {
		// TODO Auto-generated method stub
		return pharmacieRepository.findAll();
	}

	@Override
	public Produit rechercheProduit(String ref) {
		// TODO Auto-generated method stub
		return produitRepository.findByReference(ref);
	}

	@Override
	public List<Pharmacie> recherchePharmacieParMotCle(String motCle) {
		// TODO Auto-generated method stub
		return pharmacieRepository.recherchePharmacieParMotCle(motCle);
	}

	@Override
	public List<Produit> rechercheProduitParMotCle(String motCle) {
		// TODO Auto-generated method stub
		return produitRepository.rechercheProduitParMotCle(motCle);
	}

	@Override
	public Produit produitExiste(String ref) {
		// TODO Auto-generated method stub
		return produitRepository.findByReference(ref);
		
	}

	@Override
	public Pharmacie pharmacieExiste(String nom) {
		// TODO Auto-generated method stub
		return pharmacieRepository.findByNom(nom);
	}

	@Override
	public Produit modifierProduit(Produit produit) {
		// TODO Auto-generated method stub
		return produitRepository.save(produit);
	}

	@Override
	public Pharmacie modifierPharmacie(Pharmacie pharmacie) {
		// TODO Auto-generated method stub
		return pharmacieRepository.save(pharmacie);
	}
	


}
