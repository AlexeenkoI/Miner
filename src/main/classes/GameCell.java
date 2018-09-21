package main.classes;

import javafx.scene.canvas.GraphicsContext;
import main.Interfaces.CellInteraction;
import main.enums.CellStatus;
import main.enums.CellTypes;
import main.graphics.CellDrawer;

/**
 * Implemented Cell Class
 */

public class GameCell implements CellInteraction {

    private CellTypes cellType;
    private CellStatus cellStatus;
    private int minesAround;

    public GameCell(CellTypes cellType, CellStatus cellStatus, int mineNumber) {
        this.cellType = cellType;
        this.cellStatus = cellStatus;
        if(cellType != CellTypes.MINE) {
            this.minesAround = mineNumber;
        }else{
            this.minesAround = 0;
        }
    }

    @Override
    public void drawSelf(GraphicsContext gContext, int posX, int posY) {
        CellDrawer cd = new CellDrawer(gContext);
        cd.clearCell(posX, posY);
        switch (cellStatus){
            case CLOSED:
                cd.drawDefaultCell(posX, posY);
                break;
            case OPENED:
                if(minesAround > 0){
                    cd.drawText(posX, posY, minesAround);
                }else if(cellType == CellTypes.MINE){
                    cd.drawMine(posX, posY);
                }else{
                    cd.drawOpenCell(posX, posY);
                }
                break;
            case FLAGGED_AS_MINE:
                cd.drawQuestion(posX,posY);
                break;
            default:
                cd.drawDefaultCell(posX,posY);

        }
    }

    @Override
    public CellTypes checkCellType() {
        return cellType;
    }

    @Override
    public void setCellType(CellTypes type) {
        this.cellType = type;
    }

    @Override
    public CellStatus checkCellStatus() {
        return cellStatus;
    }

    @Override
    public void setCellStatus(CellStatus status) {
        this.cellStatus = status;
    }

    @Override
    public int getCountMinesAround() {
        return minesAround;
    }

    @Override
    public void setCountMinesAround(int mines) {
        this.minesAround = mines;
    }
}
