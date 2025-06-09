package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.game.GamePlayer;
import sk.tuke.gamestudio.service.CommentService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@SessionScope
public class CommentController {
	@Autowired
	private CommentService commentService;

	@GetMapping("/maze/comment")
	public String showCommentForm() {
		return "mazeComment";
	}

	@PostMapping("/maze/comment")
	public String submitComment(
			@RequestParam("comment") String comment,
			RedirectAttributes attrs
	) {
		commentService.addComment(
				new Comment("maze", GamePlayer.username, comment, Timestamp.valueOf(LocalDateTime.now()))
		);
		attrs.addFlashAttribute("message",
				"Дякуємо, " + GamePlayer.username + "! Ваш коментар збережено."
		);
		return "redirect:/maze?direction=new";
	}
}
