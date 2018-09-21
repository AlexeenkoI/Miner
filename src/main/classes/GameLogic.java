package main.classes;

import javafx.scene.canvas.GraphicsContext;
import main.Interfaces.CellInteraction;
import main.enums.CellStatus;
import main.enums.CellTypes;
import main.enums.GameStatus;

/**
 * Login for User Unteractions
 */

public class GameLogic {
    private CellInteraction[][] cellArr;
    private GraphicsContext gContext;
    private GameField gameField;

    public GameLogic(CellInteraction[][] cellArr, GraphicsContext context, GameField gameField) {
        this.cellArr = cellArr;
        this.gContext = context;
        this.gameField = gameField;
    }

    public void openCell(int posX, int posY){
        if(cellArr[posX][posY].checkCellType() == CellTypes.MINE){
            Game.GAME_STATUS = GameStatus.GAME_OVER;
            gameField.drawMines();
        }else{
            if(cellArr[posX][posY].checkCellStatus() !=CellStatus.FLAGGED_AS_MINE) {
                cellArr[posX][posY].setCellStatus(CellStatus.OPENED);
                gameField.setOpenedCells(gameField.getOpenedCells()+1);
                cellArr[posX][posY].drawSelf(gContext, posX, posY);

                if(cellArr[posX][posY].getCountMinesAround() == 0)
                    gameField.checkCellsOnOpen(posX,posY);
                if(cellArr[posX][posY].getCountMinesAround() > 0)
                    gameField.setScore(cellArr[posX][posY].getCountMinesAround());
            }
        }
    }

    public void markCell(int posX, int posY){
        if(cellArr[posX][posY].checkCellStatus() == CellStatus.FLAGGED_AS_MINE){
            cellArr[posX][posY].setCellStatus(CellStatus.CLOSED);
        }else {
            cellArr[posX][posY].setCellStatus(CellStatus.FLAGGED_AS_MINE);
        }
        cellArr[posX][posY].drawSelf(gContext, posX, posY);
    }
}
