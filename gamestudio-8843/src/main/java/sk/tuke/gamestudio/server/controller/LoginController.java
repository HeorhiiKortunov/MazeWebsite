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
import sk.tuke.gamestudio.game.GamePlayer;
import sk.tuke.gamestudio.service.PlayerService;

@Controller
@SessionScope
public class LoginController {
	@Autowired
	private PlayerService playerService;

	@GetMapping("/maze/login")
	public String showLoginForm() {
		return "login";
	}

	@PostMapping("/maze/login")
	public String doLogin(
			@RequestParam("name") String name,
			@RequestParam("password") String password,
			Model model,
			RedirectAttributes attrs
	) {
		Player p = playerService.getPlayerByName(name);
		if (p == null || !p.getPassword().equals(password)) {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
		model.addAttribute("currentPlayer", p);
		attrs.addFlashAttribute("message", "Welcome back, " + name + "!");
		GamePlayer.username = name;
		return "redirect:/maze?direction=new";
	}
}
