package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Player;

@Service("remote")
public class PlayerServiceRestClient implements PlayerService {
	private static final String URL = "http://localhost:8081/api/player";

	private final RestTemplate restTemplate;

	@Autowired
	public PlayerServiceRestClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public void addPlayer(Player player) {
		restTemplate.postForEntity(URL, player, Void.class);
	}

	@Override
	public Player getPlayerByName(String name) {
		return restTemplate.getForObject(URL + "/" + name, Player.class);
	}

	@Override
	public void reset() {
		restTemplate.delete(URL + "/reset");
	}
}
