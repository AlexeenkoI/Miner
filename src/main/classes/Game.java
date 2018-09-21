package main.classes;

import main.Interfaces.CellInteraction;
import main.enums.GameStatus;

/**
 * Main Game Class
 * Contains Constants needed for start game
 * and for further usage
 */

public class Game {
    public static GameStatus GAME_STATUS;
    public final static int OFFSET_LEFT = 20;
    public final static int OFFSET_RIGHT = 20;
    public final static int OFFSET_TOP = 60;
    public final static int OFFSET_BOTTOM = 20;
    public final static int SIZE_CELL = 20;
    public static int HEIGHT = 0;
    public static int WIDTH = 0;
    public static int SIZE_CANVAS_X;
    public static int SIZE_CANVAS_Y;
    public static int NUMBER_MINE;

    private GameCanvas gameCanvas;
    private GameField gameField;
    CellInteraction[][] cellArray;


    public void startGame(int x, int y, int mineCount){
        Game.WIDTH = x * Game.SIZE_CELL + Game.OFFSET_LEFT + Game.OFFSET_RIGHT;
        Game.HEIGHT = y * Game.SIZE_CELL + Game.OFFSET_BOTTOM + Game.OFFSET_TOP;
        Game.SIZE_CANVAS_X = x;
        Game.SIZE_CANVAS_Y = y;
        Game.NUMBER_MINE = mineCount;
        Game.GAME_STATUS = GameStatus.IN_PROCESS;

        gameCanvas = new GameCanvas(x,y,mineCount);
        cellArray = gameCanvas.getCellArray();
        gameField = new GameField(cellArray, gameCanvas);
    }
}
