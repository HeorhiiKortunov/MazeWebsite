package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Score;

import java.util.List;

public interface ScoreService {
	public void addScore(Score score);
	public List<Score> getTopScores(String game);
	public void reset();
	public void printTopScores(String game);

}
