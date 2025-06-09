package sk.tuke.gamestudio.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.service.*;
import sk.tuke.gamestudio.game.MazeGenerator;



@SpringBootApplication
@EntityScan("sk.tuke.gamestudio.entity")
@EnableTransactionManagement
public class SpringClient {
	public static void main(String[] args) {
		SpringApplication.run(SpringClient.class, args);
	}

	@Bean
	public CommandLineRunner runner(MazeGenerator ui) {    ui.generateMaze();
		return args -> ui.startNewMaze();}

	@Bean
	public MazeGenerator mazeGenerator(){
		return new MazeGenerator();
	}

	@Bean
	public ScoreService scoreService() {
		return new ScoreServiceJPA();
	}

	@Bean
	public CommentService commentService() {
		return new CommentServiceJPA();
	}

	@Bean
	public PlayerService playerService() {
		return new PlayerServiceJpa();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}