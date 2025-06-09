package sk.tuke.gamestudio.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "score")
@NamedQuery( name = "Score.getTopScores",
        query = "SELECT s FROM Score s WHERE s.game=:game ORDER BY s.points ASC")
@NamedQuery( name = "Score.resetScores",
        query = "DELETE FROM Score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String player;
    private String game;
    private int points;
    private Timestamp playedOn;

    public Score(String player, String game, int points, Timestamp playedOn) {
        this.player = player;
        this.game = game;
        this.points = points;
        this.playedOn = playedOn;
    }

    public Score(){}

    public long getId() { return id; }
    public String getPlayer() { return player; }
    public String getGame() { return game; }
    public int getPoints() { return points; }
    public Timestamp getPlayedOn() { return playedOn; }
}