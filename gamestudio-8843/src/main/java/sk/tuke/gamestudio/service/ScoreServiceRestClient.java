package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Score;

import java.util.Arrays;
import java.util.List;

public class ScoreServiceRestClient implements ScoreService {
    private final String url = "http://localhost:8081/api/score";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addScore(Score score) {
        restTemplate.postForEntity(url, score, Score.class);
    }

    @Override
    public List<Score> getTopScores(String gameName) {
        return Arrays.asList(restTemplate.getForEntity(url + "/" + gameName, Score[].class).getBody());
    }
    
    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web service");
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
}