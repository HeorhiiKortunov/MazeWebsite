package sk.tuke.gamestudio.service;


import sk.tuke.gamestudio.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CommentServiceJPA implements CommentService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addComment(Comment comment) {
		entityManager.persist(comment);
	}

	@Override
	public List<Comment> getAllComments(String game) {
		return entityManager.createNamedQuery("Comment.getAllComments")
				.setParameter("game", game).getResultList();
	}

	@Override
	public void printAllComments(String game) {
		List<Comment> comments = getAllComments(game);

		System.out.println("=== Comments for " + game + " ===");
		if (comments.isEmpty()) {
			System.out.println("No comments available yet.");
		} else {
			for (Comment comment : comments) {
				System.out.printf("Player: %s%n", comment.getPlayer());
				System.out.printf("Comment: %s%n", comment.getComment());
				System.out.printf("Date: %s%n", comment.getCommentedOn());
				System.out.println("===============================");
			}
		}
	}



	@Override
	public void reset() {
		entityManager.createNamedQuery("Comment.resetComments").executeUpdate();
	}
}
