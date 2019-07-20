package cm.objis.wtt.pharmacie.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/admin")
@SessionAttributes("pharmacie")
public class AdminController {
	
	private final IService service;
	
	@Autowired
	public AdminController(IService service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public String home(Model model, SessionStatus status) {
		
		status.setComplete(); //Suppresion des attributs mis en session
		model.addAttribute("activeLink", "home");
		return "/admin/index";
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
		
		return "/admin/liste-pharmacie";
	}
	
	@GetMapping("/nouvelle-pharmacie.html")
	public String nouvellePharmacie(Model model) {
		
		model.addAttribute("pharmacieDto", new PharmacieDto());
		model.addAttribute("activeLink", "pharmacie");
		
		return "/admin/nouvelle-pharmacie";
	}
	
	@PostMapping("/enregistrePharmacie")
	public String enregistrePharmacie(@ModelAttribute PharmacieDto pharmacieDto, 
												Model model) {
		
		Pharmacie pharmacie = pharmacieDto.getEntity();
		service.enregistrerPharmacie(pharmacie);
		
		return "redirect:liste-pharmacie.html";
	}
	
	
	@GetMapping("/liste-produit.html")
	public String listeProduit(@RequestParam(name = "pharmacie", required = false) String nomPharmacie, Model model) {
		
		String url = "/admin/liste-produit";
		if (nomPharmacie == null) {
			/* On liste toutes les pharamcies */
			
			// je récupère la Liste de tous les produits
			final List<Produit> mesProduits = service.listProduit();
			
			// Je transforme les produits en produitDTO Data Transfer Object.
			final List<ProduitDto> lesProduitsDto = Lists.transform(mesProduits, 
					(Produit input) -> input.getDto());
			
			model.addAttribute("lesProduitsDto", lesProduitsDto);
			model.addAttribute("activeLink", "produit");
			
		} else {
			/* On liste les produits d'une pharmacie en particulier*/
			Pharmacie pharmacie = service.pharmacieExiste(nomPharmacie);
			if(pharmacie == null) {
				//Je prépare un message d'erreur
				
			} else {
				// Liste des produits de la pharmacie
				List<Produit> produits = service.listProduit(pharmacie);
				List<ProduitDto> lesProduitsDto = Lists.transform(produits, (Produit input) -> input.getDto() );
				model.addAttribute("lesProduitsDto", lesProduitsDto);
				model.addAttribute("pharmacie", pharmacie); //Il sera aussi ajouté à la session
				model.addAttribute("activeLink", "pharmacie");
				
				url = "/admin/liste-produit-pharmacie";
			}
		}
		
		return url;
	}
	
	@GetMapping("/nouveau-produit.html")
	public String nouveauProduit(Model model) {
		
		model.addAttribute("produitDto", new ProduitDto());
		model.addAttribute("activeLink", "pharmacie");
		
		return "/admin/nouveau-produit";
	}
	
	@PostMapping("/enregistreProduit")
	public String enregistreProduit(@SessionAttribute Pharmacie pharmacie, @ModelAttribute ProduitDto produitDto, Model model) {
		
		Produit produit = produitDto.getEntity();
		produit.setPharmacie(pharmacie);
		service.enregistrerProduit(produit);
		
		return "redirect:liste-produit.html?pharmacie="+pharmacie.getNom();
	}

}
