package main.graphics;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import main.classes.Game;
import main.classes.GameField;
import main.classes.GameLogic;
import main.enums.GameStatus;

/*
Field event handlers
 */

public class Handler {
    GameLogic gameLogic;

    public Handler(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    /**
     * Includes current gamefield to fire gameCondition
     * @param gameField
     */

    public void onMouseClick(GameField gameField){
        RootContext.getInstance().setOnMousePressed(event -> {
            double x = (event.getX() - Game.OFFSET_LEFT) / Game.SIZE_CELL;
            double y = (event.getY() - Game.OFFSET_TOP) / Game.SIZE_CELL;
            if(((event.getX() - Game.OFFSET_LEFT) > 0
                    && x <= (double)Game.SIZE_CANVAS_X
                    && (event.getY()- Game.OFFSET_TOP) > 0
                    && y <= (double)Game.SIZE_CANVAS_Y)) {
                if (Game.GAME_STATUS == GameStatus.GAME_OVER) return;

                switch (event.getButton()) {
                    case PRIMARY:
                        gameLogic.openCell((int) x, (int) y);
                        break;
                    case SECONDARY:
                        gameLogic.markCell((int) x, (int) y);
                        break;
                }

                gameField.checkGameCondition();
            }
        });


    }

    public void newGameBtnHandler(Node node, GraphicsContext gContext){
        node.setOnMouseClicked(event -> {
            RootContext.getInstance().getChildren().clear();
            gContext.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
            new Game().startGame(Game.SIZE_CANVAS_X, Game.SIZE_CANVAS_Y,Game.NUMBER_MINE);
        });
    }
}
