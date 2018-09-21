package main.classes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.Interfaces.CellInteraction;
import main.Interfaces.FieldInteraction;
import main.enums.CellStatus;
import main.enums.CellTypes;
import main.enums.GameStatus;
import main.graphics.Elements;
import main.graphics.Handler;
import main.graphics.RootContext;

/**
 * GameFeild
 * contains all methods to manipulate the field
 */

public class GameField implements FieldInteraction {

    private CellInteraction[][] cellArr;
    private Canvas canvas;
    private GraphicsContext gContext;
    private GameLogic gameLogic;
    private GameCanvas gameCanvas;
    private Handler handler;

    private Button mainGameButton;
    private Label scoreLabel;
    private Label timeLabel;

    public static Thread timerThread;
    private Timer timer;

    private int score;
    private int baseScoreApplyer;
    private int openedCells;


    public GameField(CellInteraction[][] cellArr, GameCanvas gameCanvas) {
        this.cellArr = cellArr;
        this.gameCanvas = gameCanvas;
        this.score = 0;
        this.baseScoreApplyer = 100;
        this.openedCells = 0;
        createGraphics();
        gameLogic = new GameLogic(cellArr, gContext, this);
        handler = new Handler(gameLogic);
        addEventHandlers();
        draw();
        timer = new Timer(timeLabel);
        GameField.timerThread = new Thread(timer);

        if(!GameField.timerThread.isAlive()){
            GameField.timerThread.start();
        }
    }

    @Override
    public void draw() {
        for (int i = 0; i < cellArr.length; i++) {
            for (int j = 0; j < cellArr[i].length; j++) {
                cellArr[i][j].drawSelf(gContext, i, j);

            }
        }
    }

    public void checkCellsOnOpen(int x, int y){
        int[] matrix = gameCanvas.createCheckMatrix(x, y);
        for (int i = matrix[0]; i <= matrix[1] ; i++) {
            for (int j = matrix[2]; j <= matrix[3] ; j++) {
                if(cellArr[i][j].getCountMinesAround() == 0 && cellArr[i][j].checkCellStatus() == CellStatus.CLOSED
                        && cellArr[i][j].checkCellStatus() != CellStatus.FLAGGED_AS_MINE
                        && cellArr[i][j].checkCellType() != CellTypes.MINE){
                    cellArr[i][j].setCellStatus(CellStatus.OPENED);
                    setOpenedCells(getOpenedCells()+1);
                    cellArr[i][j].drawSelf(gContext, i , j);
                        checkCellsOnOpen(i, j);
                }
                if(cellArr[i][j].getCountMinesAround() > 0
                        && cellArr[i][j].checkCellStatus() == CellStatus.CLOSED
                        && cellArr[i][j].checkCellStatus() != CellStatus.FLAGGED_AS_MINE
                        && cellArr[i][j].checkCellType() != CellTypes.MINE){
                    cellArr[i][j].setCellStatus(CellStatus.OPENED);
                    setOpenedCells(getOpenedCells()+1);
                    cellArr[i][j].drawSelf(gContext, i, j);
                }
            }
        }
    }

    @Override
    public void drawMines() {
        for (int i = 0; i < cellArr[0].length ; i++) {
            for (int j = 0; j < cellArr[1].length; j++) {
                if(cellArr[i][j].checkCellType() == CellTypes.MINE) {
                    cellArr[i][j].setCellStatus(CellStatus.OPENED);
                    cellArr[i][j].drawSelf(gContext, i, j);
                }
            }
        }
        setBtnText("You lose!");
        timer.cancel();
    }

    public void setOpenedCells(int openedCells) {
        this.openedCells = openedCells;
    }

    public int getOpenedCells() {
        return openedCells;
    }

    @Override
    public void checkGameCondition() {
        int OpenCountToWin = (Game.SIZE_CANVAS_X * Game.SIZE_CANVAS_Y) - Game.NUMBER_MINE;
        if(openedCells == OpenCountToWin) {
            Game.GAME_STATUS = GameStatus.GAME_OVER;
            setBtnText("You Win!");
            timer.cancel();
        }
    }

    public void setBtnText(String text){
        this.mainGameButton.setText(text);
    }

    public void setScore(int multiply){
        this.score = this.score + (this.baseScoreApplyer * multiply);
        scoreLabel.setText("Score: " + this.score);
    }


    private void createGraphics() {
        Elements elm = new Elements();
        canvas = elm.createCanvas(Game.WIDTH, Game.HEIGHT);
        mainGameButton = elm.createButton("New game");
        mainGameButton.setTranslateX(Game.WIDTH/2);
        scoreLabel = elm.createLabel("Score: " + this.score);
        timeLabel = elm.createLabel("Time: " + 0);
        timeLabel.setTranslateX(Game.WIDTH - Game.OFFSET_RIGHT);
        gContext = canvas.getGraphicsContext2D();

        RootContext.getInstance().getChildren().addAll(canvas,scoreLabel,mainGameButton,timeLabel);
    }

    private void addEventHandlers() {
        handler.onMouseClick(this);
        handler.newGameBtnHandler(mainGameButton,gContext);
    }
}
