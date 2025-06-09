package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.tuke.gamestudio.entity.Player;
import sk.tuke.gamestudio.service.PlayerService;

@Controller
@SessionScope
public class RegisterController {
	@Autowired
	private PlayerService playerService;

	@GetMapping("maze/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("player", new Player());
		return "register";
	}

	@PostMapping("/maze/register")
	public String registerPlayer(
			@RequestParam("name") String name,
			@RequestParam("password") String password,
			RedirectAttributes attrs
	) {
		playerService.addPlayer(new Player(name, password));
		attrs.addFlashAttribute("message", "Welcome, " + name + "!");
		return "redirect:/maze/login";
	}
}
