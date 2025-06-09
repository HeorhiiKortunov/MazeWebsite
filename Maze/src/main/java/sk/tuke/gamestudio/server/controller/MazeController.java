package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Player;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.GamePlayer;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.PlayerService;
import sk.tuke.gamestudio.service.ScoreService;
import sk.tuke.gamestudio.game.MazeGenerator;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@SessionScope
public class MazeController {

	@Autowired
	private MazeGenerator mazeGenerator;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private CommentService commentService;

	@GetMapping("/maze")
	public String maze(
			@RequestParam(value = "direction", required = false) String direction,
			Model model,
			RedirectAttributes attrs
	) {
		if (direction != null) {
			if ("new".equals(direction)) {
				mazeGenerator.startNewMaze();
			} else {
				mazeGenerator.move(direction);
			}
			if (mazeGenerator.isAtFinish()) {
				attrs.addFlashAttribute("steps", mazeGenerator.getPlayer().getScore());
				return "redirect:/maze/win";
			}
		}

		model.addAttribute("cells",   mazeGenerator.getCells());
		model.addAttribute("playerX", mazeGenerator.getPlayer().getX());
		model.addAttribute("playerY", mazeGenerator.getPlayer().getY());
		model.addAttribute("finishX", mazeGenerator.getFinishX());
		model.addAttribute("finishY", mazeGenerator.getFinishY());
		model.addAttribute("scores",   scoreService.getTopScores("maze"));
		model.addAttribute("comments", commentService.getAllComments("maze"));

		return "maze";
	}

}
