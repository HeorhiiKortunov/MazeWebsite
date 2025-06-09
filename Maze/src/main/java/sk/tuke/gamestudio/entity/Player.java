package sk.tuke.gamestudio.entity;

import javax.persistence.*;

@Entity
@Table(name = "player")
@NamedQueries({
		@NamedQuery(
				name = "Player.getPlayerByName",
				query = "SELECT s FROM Player s WHERE s.name=:name"
		),
		@NamedQuery(
				name = "Player.resetPlayers",
				query = "DELETE FROM Player"
		)
})
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String password;

	public Player(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public Player() {}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
}
