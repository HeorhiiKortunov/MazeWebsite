package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;

import java.util.List;

public interface CommentService {
	public void addComment(Comment comment);
	public List<Comment> getAllComments(String game);
	public void reset();
	public void printAllComments(String game);
}
