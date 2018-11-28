package minesweeper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;

/**
 *
 * @author Raymond
 */
public class Minesweeper {
    private final Cell[][] grid;
    private int bombs;
    private int height, width;
    private int gameOver;
    
    public Minesweeper(int h, int w, int b) {
        height = h;
        width = w;
        grid = new Cell[height+2][width+2];
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                grid[row][column] = new Cell(column, row);
            }
        }
        bombs = b;
        
        for (int row = 1; row < grid.length-1; row++) {
            for (int column = 1; column < grid[row].length-1; column++) {
                grid[row][column].setRevealed(false);
            }
        }
        
        gameOver = 0;
    }
    
    public void resetGame() {
        for (int row = 1; row < grid.length-1; row++) {
            for (int column = 1; column < grid[row].length-1; column++) {
                grid[row][column].setValue(0);
                grid[row][column].setRevealed(false);
                grid[row][column].setFlagged(false);
            }
        }
        gameOver = 0;
    }
    
    public void generateBoard(int startY, int startX) {
//        System.out.println("X: " + startX + "; Y: " + startY);
        Random r = new Random();
        int x, y;
        boolean adjacent;
        for (int i = 0; i < bombs; i++) {
            do {
                x = r.nextInt(width);
                y = r.nextInt(height);
                adjacent = x >= startX-1 && x <= startX+1 && y >= startY-1 && y <= startY+1;
            } while (grid[y + 1][x + 1].getValue() == -1 || adjacent);
//            System.out.println("x=" + x + "; y=" + y);
            grid[y + 1][x + 1].setValue(-1);
        }

        for (int row = 1; row < grid.length - 1; row++) {
            for (int column = 1; column < grid[0].length - 1; column++) {
                if (grid[row][column].getValue() != -1) {
                    grid[row][column].setValue(countAdjBombs(row, column));
                }
            }
        }

    }
    
    /**
     * Counts number of adjacent bombs
     * pre: cellRow and cellCol >= 0, < grid.length-2
     * post: number of adjacent bombs is returned
     * @param cellRow
     * @param cellCol
     * @return 
     */
    private int countAdjBombs(int cellRow, int cellCol) {
        int adjBombs = 0;
        
        if (grid[cellRow-1][cellCol-1].getValue() == -1) {
            adjBombs++;
        }
        if (grid[cellRow-1][cellCol].getValue() == -1) {
            adjBombs++;
        }
        if (grid[cellRow-1][cellCol+1].getValue() == -1) {
            adjBombs++;
        }
        if (grid[cellRow][cellCol-1].getValue() == -1) {
            adjBombs++;
        }
        if (grid[cellRow][cellCol+1].getValue() == -1) {
            adjBombs++;
        }
        if (grid[cellRow+1][cellCol-1].getValue() == -1) {
            adjBombs++;
        }
        if (grid[cellRow+1][cellCol].getValue() == -1) {
            adjBombs++;
        }
        if (grid[cellRow+1][cellCol+1].getValue() == -1) {
            adjBombs++;
        }
        return adjBombs;
    }
    
    public void revealCell(int cellRow, int cellColumn) {
        if (grid[cellRow+1][cellColumn+1].isRevealed() || grid[cellRow+1][cellColumn+1].isFlagged()) {
            return;
        } else {
            grid[cellRow+1][cellColumn+1].setRevealed(true);
        }
//        System.out.println((cellRow+1) + " " + (cellColumn+1));
        int number = grid[cellRow+1][cellColumn+1].getValue();
        
        // Revealing a bomb ends the game
        if (number == -1) {
            System.out.println("gg");
            gameOver = 1;
            for (int row = 1; row < grid.length - 1; row++) {
                for (int column = 1; column < grid[row].length - 1; column++) {
                    if (grid[row][column].getValue() == -1 && !grid[row][column].isFlagged()) {
                        grid[row][column].setRevealed(true);
                    }
                }
            }
        } else {
            if (number == 0) { 
                revealAdjCells(cellRow, cellColumn);
            } 
            
            // If there are no more unrevealed non-bomb cells, the game is won
            if (gameOver == 0) {
                gameOver = 2;
                for (int row = 1; row < grid.length-1; row++) {
                    for (int column = 1; column < grid[row].length-1; column++) {
                        if (grid[row][column].getValue() != -1 && !grid[row][column].isRevealed()) {
                            gameOver = 0;
                        }
                    }
                }
            }
        }
    }
    
        
    public void revealAdjCells(int cellRow, int cellColumn) {
        revealCell(cellRow - 1, cellColumn - 1);
        revealCell(cellRow - 1, cellColumn);
        revealCell(cellRow - 1, cellColumn + 1);
        revealCell(cellRow, cellColumn - 1);
        revealCell(cellRow, cellColumn + 1);
        revealCell(cellRow + 1, cellColumn - 1);
        revealCell(cellRow + 1, cellColumn);
        revealCell(cellRow + 1, cellColumn + 1);
    }
    
    public void flagCell(int cellRow, int cellColumn) {
        if (grid[cellRow+1][cellColumn+1].isRevealed()) {
            return;
        }
        
        if (grid[cellRow+1][cellColumn+1].isFlagged()) {
            grid[cellRow+1][cellColumn+1].setFlagged(false);
        } else {
            grid[cellRow+1][cellColumn+1].setFlagged(true);
        }
    }
    
    public int countAdjFlags(int cellRow, int cellColumn) {
        int adjFlags = 0;

        if (grid[cellRow - 1][cellColumn - 1].isFlagged()) {
            adjFlags++;
        }
        if (grid[cellRow - 1][cellColumn].isFlagged()) {
            adjFlags++;
        }
        if (grid[cellRow - 1][cellColumn + 1].isFlagged()) {
            adjFlags++;
        }
        if (grid[cellRow][cellColumn - 1].isFlagged()) {
            adjFlags++;
        }
        if (grid[cellRow][cellColumn + 1].isFlagged()) {
            adjFlags++;
        }
        if (grid[cellRow + 1][cellColumn - 1].isFlagged()) {
            adjFlags++;
        }
        if (grid[cellRow + 1][cellColumn].isFlagged()) {
            adjFlags++;
        }
        if (grid[cellRow + 1][cellColumn + 1].isFlagged()) {
            adjFlags++;
        }
        return adjFlags;
    }
    
    public int gameIsOver() {
        return gameOver;
    }
    
    /**
     * Creates a string representation of the grid
     * pre: none
     * post: string representing grid is returned
     * @return 
     */
    @Override
    public String toString() {
        String gameString = "";
        
        for (int row = 1; row < grid.length-1; row++) {
            for (int column = 1; column < grid[row].length-1; column++) {
                gameString += (grid[row][column] + "  ");
            }
            gameString += "\n";
        }        
        
        return gameString;
    }
    
    /**
     * Returns grid
     * pre: none
     * post: grid is returned
     * @return 
     */
    public Cell[][] getGrid() {
        return grid;
    }
    
}
