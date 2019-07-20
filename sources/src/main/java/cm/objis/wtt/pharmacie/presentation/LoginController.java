package cm.objis.wtt.pharmacie.presentation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

import cm.objis.wtt.pharmacie.domaine.dto.UserDto;
import cm.objis.wtt.pharmacie.domaine.entities.Produit;
import cm.objis.wtt.pharmacie.domaine.entities.User;
import cm.objis.wtt.pharmacie.service.UserService;



@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/login");
		return modelAndView;
	}


	@RequestMapping(value="/admin/registration.html", method = RequestMethod.GET)
	public ModelAndView registration(){
		final ModelAndView modelAndView = new ModelAndView();
		final User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.addObject("activeLink", "utilisateur");
		
		modelAndView.setViewName("/admin/registration");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		final ModelAndView modelAndView = new ModelAndView();
		final User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
			.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/admin/registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("/admin/registration");

		}
		return modelAndView;
	}

	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request){
		final ModelAndView modelAndView = new ModelAndView();
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final User user = userService.findUserByEmail(auth.getName());
		request.getSession().setAttribute("username", user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.addObject("activeLink", "home");
		
		modelAndView.setViewName("/admin/index");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/liste-utilisateur.html")
	public ModelAndView listeUtilisateurs() {
		final ModelAndView modelAndView = new ModelAndView();
		final List<User> users = userService.listUsers();
		final List<UserDto> usersDto = Lists.transform(users, (User input) -> input.getDto() );
		modelAndView.addObject("usersDto", usersDto);
		modelAndView.addObject("activeLink", "utilisateur");
		
		modelAndView.setViewName("/admin/liste-utilisateur.html");
		
		return modelAndView;
	}


}
