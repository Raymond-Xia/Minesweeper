package minesweeper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Raymond
 */
public class Cell {
    private int value;
    private int x, y;
    private boolean revealed, flagged;
    
    public Cell(int x, int y) {
        value = 0;
        this.x = x;
        this.y = y;
        revealed = true;
        flagged = false;
    }
    
    public int getValue() {
        return value;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public boolean isRevealed() {
        return revealed;
    }
    
    public boolean isFlagged() {
        return flagged;
    }
    
    public void setValue(int v) {
        value = v;
    }
    
    public void setRevealed(boolean r) {
        revealed = r;
    }
    
    public void setFlagged(boolean f) {
        flagged = f;
    }
    
}
