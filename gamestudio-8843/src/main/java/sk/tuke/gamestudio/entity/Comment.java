package sk.tuke.gamestudio.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
@NamedQueries({
        @NamedQuery(
                name = "Comment.getAllComments",
                query = "SELECT c FROM Comment c WHERE c.game = :game"
        ),
        @NamedQuery(
                name = "Comment.resetComments",
                query = "DELETE FROM Comment"
        )
})
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String game;
    private String player;
    private String comment;
    private Timestamp commentedOn;

    public Comment() {}

    public Comment(String game, String player, String comment, Timestamp commentedOn) {
        this.game = game;
        this.player = player;
        this.comment = comment;
        this.commentedOn = commentedOn;
    }

    public long getId() {
        return id;
    }

    public String getGame() {
        return game;
    }

    public String getPlayer() {
        return player;
    }

    public String getComment() {
        return comment;
    }

    public Timestamp getCommentedOn() {
        return commentedOn;
    }
}
