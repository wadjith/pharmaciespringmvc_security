package cm.objis.wtt.pharmacie.presentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.google.common.collect.Lists;

import cm.objis.wtt.pharmacie.domaine.dto.PharmacieDto;
import cm.objis.wtt.pharmacie.domaine.dto.ProduitDto;
import cm.objis.wtt.pharmacie.domaine.entities.Pharmacie;
import cm.objis.wtt.pharmacie.domaine.entities.Produit;
import cm.objis.wtt.pharmacie.service.IService;

@Controller
@SessionAttributes("pharmacie")
public class WebController {
	
	private final IService service;
	
	@Autowired
	public WebController(IService service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public String home(Model model, SessionStatus status) {
		
		status.setComplete(); //Suppresion des attributs mis en session
		model.addAttribute("activeLink", "home");
		
		return "home";
	}
	
	@GetMapping("/liste-pharmacie.html")
	public String listePharmacie(Model model) {
		
		// je récupère la Liste de tous les produits
		final List<Pharmacie> mesPharmacies = service.listPharmacie();
		
		// Je transforme les pharmacies en pharmacieDTO Data Transfer Object.
		final List<PharmacieDto> lesPharmaciesDtos = Lists.transform(mesPharmacies, 
				(Pharmacie input) -> input.getDto());
		
		model.addAttribute("lesPharmaciesDtos", lesPharmaciesDtos);
		model.addAttribute("activeLink", "pharmacie");
		
		return "liste-pharmacie";
	}
	
	@GetMapping("/affiche-pharmacie.html/{nom}")
	public String affichePharmacie(@PathVariable("nom") String nomPharmacie, Model model) {
		
		List<Produit> produits = new ArrayList<Produit>();
		List<ProduitDto> lesProduitsDto = new ArrayList<ProduitDto>();
		
		Pharmacie pharmacie = service.pharmacieExiste(nomPharmacie);
		if(pharmacie == null) {
			//Je prépare un message d'erreur
			
		} else {
			// Liste des produits de la pharmacie
			produits = service.listProduit(pharmacie);
			lesProduitsDto = Lists.transform(produits, (Produit input) -> input.getDto() );
			model.addAttribute("pharmacie", pharmacie); //Il sera aussi ajouté à la session
		}
		
		model.addAttribute("lesProduitsDto", lesProduitsDto);
		model.addAttribute("activeLink", "pharmacie");
		
		return "affiche-pharmacie";
	}
	
	@GetMapping("/liste-produit.html")
	public String listeProduit(Model model) {
		
		// je récupère la Liste de tous les produits
		final List<Produit> mesProduits = service.listProduit();
		
		// Je transforme les produits en produitDTO Data Transfer Object.
		final List<ProduitDto> lesProduitsDto = Lists.transform(mesProduits, 
				(Produit input) -> input.getDto());
		
		model.addAttribute("lesProduitsDto", lesProduitsDto);
		model.addAttribute("activeLink", "produit");
		
		return "liste-produit";
	}

}
