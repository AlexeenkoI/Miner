package main.Interfaces;

import javafx.scene.canvas.GraphicsContext;
import main.enums.CellStatus;
import main.enums.CellTypes;

/*
Cell Interface
Provides core functionality in-game interaction
 */

public interface CellInteraction {

    void drawSelf(GraphicsContext gContext, int posX, int posY);

    CellTypes checkCellType();

    void setCellType(CellTypes type);

    CellStatus checkCellStatus();

    void setCellStatus(CellStatus status);

    int getCountMinesAround();

    void setCountMinesAround(int mines);
}
