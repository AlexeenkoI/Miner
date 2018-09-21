package main.classes;

import main.Interfaces.CellInteraction;
import main.enums.CellStatus;
import main.enums.CellTypes;

import java.util.Random;

/**
 * Initializer fields components
 */

public class GameCanvas {
    private int height;
    private int width;
    private int numberMines;
    private CellInteraction[][] cellArray;

    public GameCanvas(int x, int y, int numberMines) {
        this.width = x;
        this.height = y;
        this.numberMines = numberMines;
        this.cellArray = new CellInteraction[x][y];
        initCellArray();
        insertMines();
        insertNumbers();
    }

    private void initCellArray(){
        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height ; j++) {
                cellArray[i][j] = new GameCell(CellTypes.EMPTY_CELL, CellStatus.CLOSED,0);
            }
        }
    }


    private void insertMines(){
        for (int i = 0; i < numberMines; i++) {
            Random rng = new Random();
            int posX, posY;
            do {
                posX = rng.nextInt(cellArray[0].length);
                posY = rng.nextInt(cellArray[1].length);
            }while (cellArray[posX][posY].checkCellType() == CellTypes.MINE);
            cellArray[posX][posY].setCellType(CellTypes.MINE);

        }
    }

    public CellInteraction[][] getCellArray() {
        return cellArray;
    }

    private void insertNumbers(){
        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height ; j++) {
                if(cellArray[i][j].checkCellType() != CellTypes.MINE) {
                    int[] coord = createCheckMatrix(i, j);
                    int size = countMinesAround(coord);
                    cellArray[i][j].setCountMinesAround(size);
                }
            }
        }
    }



    private int countMinesAround(int[] matrix){
        int mines = 0;
        for (int i = matrix[0]; i <= matrix[1]; i++) {
            for (int j = matrix[2]; j <= matrix[3] ; j++) {
                if(cellArray[i][j].checkCellType() == CellTypes.MINE) mines++;
            }
        }
        return mines;
    }

    /**
     * Builds an coord array of nearest cells to check
     * @return Array[]
     */

    public int[] createCheckMatrix(int x, int y){
        int xLeft = x - 1 < 0 ? 0 : x - 1;
        int xRight = x + 1 >= cellArray[0].length ? cellArray[0].length - 1 : x + 1;
        int yTop = y - 1 < 0 ? 0 : y - 1;
        int yBottom = y + 1 >= cellArray[1].length ? cellArray[1].length - 1 : y + 1;
        return new int[] {xLeft, xRight, yTop, yBottom};
    }
}
