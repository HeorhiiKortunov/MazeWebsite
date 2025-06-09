package sk.tuke.gamestudio.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentServiceRest {

	@Autowired
	private CommentService commentService;

	@GetMapping("/{game}")
	public List<Comment> getAllComments(String game){return commentService.getAllComments(game);}


	@PostMapping
	public void addComment(@RequestBody Comment comment) {
		commentService.addComment(comment);
	}
}