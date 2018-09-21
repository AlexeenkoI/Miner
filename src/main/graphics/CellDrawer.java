package main.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.classes.Game;

/**
 * Cells Stylizer
 */

public class CellDrawer {
    GraphicsContext gContext;

    public CellDrawer(GraphicsContext gContext) {
        this.gContext = gContext;
    }

    public void clearCell(int x, int y) {
        gContext.clearRect(Game.OFFSET_LEFT + Game.SIZE_CELL * x, Game.OFFSET_TOP + Game.SIZE_CELL * y, Game.SIZE_CELL, Game.SIZE_CELL);

    }

    public void drawMine(int x, int y) {
        gContext.setFill(Color.RED);
        gContext.fillOval(Game.OFFSET_LEFT + Game.SIZE_CELL/4 + Game.SIZE_CELL * x, Game.OFFSET_TOP + Game.SIZE_CELL/4 + Game.SIZE_CELL * y, Game.SIZE_CELL/2, Game.SIZE_CELL/2);
    }

    public void drawText(int x, int y, int number) {
        gContext.setFill(Color.BLACK);
        gContext.fillText(String.valueOf(number), Game.OFFSET_LEFT + Game.SIZE_CELL/2.9 + Game.SIZE_CELL * x, Game.OFFSET_TOP + Game.SIZE_CELL/1.4 + Game.SIZE_CELL * y);

    }

    public void drawQuestion(int x, int y) {
        gContext.setFill(Color.DARKGREY);
        gContext.fillRoundRect(Game.OFFSET_LEFT + Game.SIZE_CELL * x, Game.OFFSET_TOP + Game.SIZE_CELL * y, Game.SIZE_CELL, Game.SIZE_CELL, 2, 2);
        gContext.setFill(Color.BLACK);
        gContext.fillText("!",Game.OFFSET_LEFT + Game.SIZE_CELL/2.9 + Game.SIZE_CELL * x, Game.OFFSET_TOP + Game.SIZE_CELL/1.4 + Game.SIZE_CELL * y);

    }

    public void drawOpenCell(int x, int y){
        gContext.setFill(Color.BEIGE);
        gContext.fillRoundRect(Game.OFFSET_LEFT + Game.SIZE_CELL * x, Game.OFFSET_TOP + Game.SIZE_CELL * y, Game.SIZE_CELL, Game.SIZE_CELL, 2, 2);
    }


    public void drawDefaultCell(int x, int y) {
        gContext.setFill(Color.DARKGREY);
        gContext.fillRoundRect(Game.OFFSET_LEFT + Game.SIZE_CELL * x, Game.OFFSET_TOP + Game.SIZE_CELL * y, Game.SIZE_CELL, Game.SIZE_CELL, 2, 2);

    }

}
