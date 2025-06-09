package sk.tuke.gamestudio.game;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;


@Component
@SessionScope
public class MazeGenerator {
    private final int width;
    private final int height;
    private Cell[][] cells;
    private GamePlayer gamePlayer;
    private final int TOP = 1, RIGHT = 2, BOTTOM = 4, LEFT = 8;
    private int finishX;
    private int finishY;

    public MazeGenerator() {
        this.width = 10;
        this.height = 10;
        startNewMaze();
    }

    public void startNewMaze() {
        initCells();
        generateMaze();
        setFinish();
        this.gamePlayer = new GamePlayer(0, 0, cells, width, height);
    }

    private void initCells() {
        cells = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x] = new Cell(x, y);
            }
        }
    }

    private void setFinish() {
        Random rand = new Random();
        do {
            finishX = rand.nextInt(width);
            finishY = rand.nextInt(height);
        } while (finishX == 0 && finishY == 0);
    }

    public void move(String direction) {
        if (direction == null || gamePlayer == null) return;
        switch (direction.toLowerCase()) {
            case "up":    gamePlayer.move('w'); break;
            case "down":  gamePlayer.move('s'); break;
            case "left":  gamePlayer.move('a'); break;
            case "right": gamePlayer.move('d'); break;
        }
    }

    public boolean isAtFinish() {
        return gamePlayer.x == finishX && gamePlayer.y == finishY;
    }

    public Cell[][] getCells() { return cells; }
    public GamePlayer getPlayer() { return gamePlayer; }
    public int getFinishX() { return finishX; }
    public int getFinishY() { return finishY; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public class Cell {
        private final int x, y;
        int walls = TOP | RIGHT | BOTTOM | LEFT;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() { return x; }
        public int getY() { return y; }

        public boolean hasTopWall()    { return (walls & TOP) != 0; }
        public boolean hasRightWall()  { return (walls & RIGHT) != 0; }
        public boolean hasBottomWall() { return (walls & BOTTOM) != 0; }
        public boolean hasLeftWall()   { return (walls & LEFT) != 0; }

        void removeWall(int wallMask) {
            walls &= ~wallMask;
        }
    }

    private class Edge {
        int x1, y1, x2, y2, weight;
        public Edge(int x1, int y1, int x2, int y2, int weight) {
            this.x1 = x1; this.y1 = y1;
            this.x2 = x2; this.y2 = y2;
            this.weight = weight;
        }
    }

    private class UnionFind {
        int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }
        public boolean union(int i, int j) {
            int ri = find(i), rj = find(j);
            if (ri == rj) return false;
            parent[rj] = ri;
            return true;
        }
    }

    public void generateMaze() {
        List<Edge> edges = new ArrayList<>();
        Random rand = new Random();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x < width - 1) edges.add(new Edge(x, y, x + 1, y, rand.nextInt(100)));
                if (y < height - 1) edges.add(new Edge(x, y, x, y + 1, rand.nextInt(100)));
            }
        }
        edges.sort(Comparator.comparingInt(e -> e.weight));
        UnionFind uf = new UnionFind(width * height);

        for (Edge edge : edges) {
            int c1 = edge.y1 * width + edge.x1;
            int c2 = edge.y2 * width + edge.x2;
            if (uf.union(c1, c2)) {
                if (edge.x1 == edge.x2) {
                    if (edge.y1 < edge.y2) {
                        cells[edge.y1][edge.x1].removeWall(BOTTOM);
                        cells[edge.y2][edge.x2].removeWall(TOP);
                    } else {
                        cells[edge.y1][edge.x1].removeWall(TOP);
                        cells[edge.y2][edge.x2].removeWall(BOTTOM);
                    }
                } else {
                    if (edge.x1 < edge.x2) {
                        cells[edge.y1][edge.x1].removeWall(RIGHT);
                        cells[edge.y2][edge.x2].removeWall(LEFT);
                    } else {
                        cells[edge.y1][edge.x1].removeWall(LEFT);
                        cells[edge.y2][edge.x2].removeWall(RIGHT);
                    }
                }
            }
        }
    }
}
