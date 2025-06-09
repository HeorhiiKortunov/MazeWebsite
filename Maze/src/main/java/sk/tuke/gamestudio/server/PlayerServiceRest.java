package sk.tuke.gamestudio.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Player;
import sk.tuke.gamestudio.service.PlayerService;

@RestController
@RequestMapping("/api/player")
public class PlayerServiceRest {

	private final PlayerService playerService;

	@Autowired
	public PlayerServiceRest(PlayerService playerService) {
		this.playerService = playerService;
	}

	@GetMapping("/{name}")
	public ResponseEntity<Player> getPlayerByName(@PathVariable String name) {
		System.out.println("Calling REST: getPlayerByName for " + name);
		Player p = playerService.getPlayerByName(name);
		return (p != null)
				? ResponseEntity.ok(p)
				: ResponseEntity.notFound().build();
	}


	@PostMapping
	public ResponseEntity<Void> addPlayer(@RequestBody Player player) {
		System.out.println("Calling REST: addPlayer for " + player.getName());
		playerService.addPlayer(player);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}


	@DeleteMapping("/reset")
	public ResponseEntity<Void> reset() {
		System.out.println("Calling REST: reset players");
		playerService.reset();
		return ResponseEntity.noContent().build();
	}
}
