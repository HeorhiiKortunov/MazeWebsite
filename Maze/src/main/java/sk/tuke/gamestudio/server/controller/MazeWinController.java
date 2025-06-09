package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.GamePlayer;
import sk.tuke.gamestudio.service.ScoreService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@SessionScope
public class MazeWinController {
	@Autowired
	private ScoreService scoreService;

	@GetMapping("/maze/win")
	public String showWinForm(Model model) {
		Integer steps = (Integer) model.asMap().get("steps");
		if (steps == null) {
			return "redirect:/maze?direction=new";
		}
		model.addAttribute("steps", steps);
		return "mazeWin";
	}

	@PostMapping("/maze/win")
	public String submitWin(
			@RequestParam("steps")  int steps,
			RedirectAttributes attrs
	) {
		scoreService.addScore(
				new Score(GamePlayer.username, "maze", steps, Timestamp.valueOf(LocalDateTime.now()))
		);
		attrs.addFlashAttribute("message",
				"Дякуємо, " + GamePlayer.username + "! Ваш результат збережено."
		);
		return "redirect:/maze?direction=new";
	}
}
