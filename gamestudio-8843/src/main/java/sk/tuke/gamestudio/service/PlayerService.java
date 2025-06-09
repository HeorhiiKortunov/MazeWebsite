package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Player;

public interface PlayerService {
	public void addPlayer(Player player);
	public Player getPlayerByName(String name);
	public void reset();
}
