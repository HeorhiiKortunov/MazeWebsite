package sk.tuke.gamestudio.game;

public class GamePlayer {
    public static String username;
    int x, y;
    private MazeGenerator.Cell[][] cells;
    private int width, height;
    private final int TOP = 1, RIGHT = 2, BOTTOM = 4, LEFT = 8;
    private static int score = 0;

    public GamePlayer(int startX, int startY, MazeGenerator.Cell[][] cells, int width, int height) {
        this.x = startX;
        this.y = startY;
        this.cells = cells;
        this.width = width;
        this.height = height;
        score = 0;
    }

    public void move(char direction) {
        MazeGenerator.Cell current = cells[y][x];
        switch (direction) {
            case 'w':
                if ((current.walls & TOP) == 0) {
                    y--;
                    score ++;
                } else {
                    System.out.println("Can't go through walls");
                }
                break;
            case 's':
                if ((current.walls & BOTTOM) == 0) {
                    y++;
                    score ++;
                } else {
                    System.out.println("Can't go through walls");
                }
                break;
            case 'a':
                if ((current.walls & LEFT) == 0) {
                    x--;
                    score ++;
                } else {
                    System.out.println("Can't go through walls");
                }
                break;
            case 'd':
                if ((current.walls & RIGHT) == 0) {
                    x++;
                    score ++;
                } else {
                    System.out.println("Can't go through walls");
                }
                break;
            default:
                System.out.println("Incorrect input. Use WASD");
        }
    }

	public static int getScore() {
		return score;
	}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}