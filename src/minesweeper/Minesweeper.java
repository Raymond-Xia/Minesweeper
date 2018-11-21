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
    private boolean gameOver;
    
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
        Random r = new Random();
        int x, y;
        for (int i = 0; i < bombs; i++) {
            do {
                x = r.nextInt(width);
                y = r.nextInt(height);
            } while (grid[y+1][x+1].getValue() == -1);    
            grid[y+1][x+1].setValue(-1);
            grid[y+1][x+1].setRevealed(false);
        }
        
        for (int row = 1; row < grid.length-1; row++) {
            for (int column = 1; column < grid[0].length-1; column++) {
                if (grid[row][column].getValue() != -1) {
                    grid[row][column].setValue(countBombs(row, column));
                    grid[row][column].setRevealed(false);
                }
            }
        }
        
        gameOver = false;
    }
    
    public void resetGame() {
        
        for (int row = 1; row < grid.length-1; row++) {
            for (int column = 1; column < grid[row].length-1; column++) {
                grid[row][column].setValue(0);
                grid[row][column].setRevealed(false);
            }
        }
        
        Random r = new Random();
        int x, y;
        for (int i = 0; i < bombs; i++) {
            do {
                x = r.nextInt(width);
                y = r.nextInt(height);
            } while (grid[y + 1][x + 1].getValue() == -1);
            grid[y+1][x+1].setValue(-1);
        }

        for (int row = 1; row < grid.length - 1; row++) {
            for (int column = 1; column < grid[0].length - 1; column++) {
                if (grid[row][column].getValue() != -1) {
                    grid[row][column].setValue(countBombs(row, column));
                }
            }
        }

        gameOver = false;
    }
    
    /**
     * Counts number of adjacent bombs
     * pre: cellRow and cellCol >= 0, < grid.length-2
     * post: number of adjacent bombs is returned
     * @param cellRow
     * @param cellCol
     * @return 
     */
    public int countBombs(int cellRow, int cellCol) {
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
        if (grid[cellRow+1][cellColumn+1].isRevealed()) {
            return;
        } else {
            grid[cellRow+1][cellColumn+1].setRevealed(true);
        }
        System.out.println((cellRow+1) + " " + (cellColumn+1));
        int number = grid[cellRow+1][cellColumn+1].getValue();
        
        if (number == -1) {
            System.out.println("gg");
            gameOver = true;
            for (int row = 1; row < grid.length - 1; row++) {
                for (int column = 1; column < grid[row].length - 1; column++) {
                    if (grid[row][column].getValue() == -1) {
                        grid[row][column].setRevealed(true);
                    }
                }
            }
        } else {
            if (number == 0) {
                revealCell(cellRow - 1, cellColumn - 1);
                revealCell(cellRow - 1, cellColumn);
                revealCell(cellRow - 1, cellColumn + 1);
                revealCell(cellRow, cellColumn - 1);
                revealCell(cellRow, cellColumn + 1);
                revealCell(cellRow + 1, cellColumn - 1);
                revealCell(cellRow + 1, cellColumn);
                revealCell(cellRow + 1, cellColumn + 1);
            } 
            gameOver = true;
            for (int row = 1; row < grid.length - 1; row++) {
                for (int column = 1; column < grid[row].length - 1; column++) {
                    if (grid[row][column].getValue() != -1 && !grid[row][column].isRevealed()) {
                        gameOver = false;
                    }
                }
            }
        }
    }
    
    public boolean gameIsOver() {
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
