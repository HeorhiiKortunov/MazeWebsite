package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Score;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ScoreServiceJPA implements ScoreService {
    
   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public void addScore(Score score) {
      entityManager.persist(score);
   }

   @Override
   public List<Score> getTopScores(String game) {
       return entityManager.createNamedQuery("Score.getTopScores")
           .setParameter("game", game).setMaxResults(10).getResultList();
   }

   @Override
    public void printTopScores(String game) {
        List<Score> topScores = getTopScores(game);

        System.out.println("=== Top 10 Scores for: " + game + " ===");
        for (int i = 0; i < topScores.size(); i++) {
            Score score = topScores.get(i);
            System.out.printf("%2d. %-10s %4d points on %s%n",
                    i + 1,
                    score.getPlayer(),
                    score.getPoints(),
                    score.getPlayedOn().toLocalDateTime().toLocalDate()
            );
        }
    }

    @Override
    public void reset() {
        entityManager.createNamedQuery("Score.resetScores").executeUpdate();
    }
}