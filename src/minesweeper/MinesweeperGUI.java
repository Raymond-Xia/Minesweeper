package minesweeper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Raymond
 */
public class MinesweeperGUI {    
    JFrame gameWindow;
    JPanel mainPanel;
    JPanel gridPanel;
    JLabel timeCounter;
    JLabel bombCounter;
    JLabel[][] displayedGrid;
    JButton reset;  
    Timer timer;
//    JMenuBar menubar;
//    JMenu file, help;
//    JMenuItem save, load, quit, rules, about;    
    private Minesweeper board;
    private final int cellSize = 40;
    private int time;
    
    public MinesweeperGUI() {
        /* Game Window */
        gameWindow = new JFrame("Minesweeper");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        // Menu Bar
//        menubar = new JMenuBar();
//        gameWindow.setJMenuBar(menubar);
//
//        file = new JMenu("File");                
//        save = new JMenuItem("Save");
//        save.addActionListener(new MenuListener());
//        save.setActionCommand("Save");
//        savedPattern = new int[scale][scale];
//        file.add(save);
//        load = new JMenuItem("Load");
//        load.addActionListener(new MenuListener());
//        load.setActionCommand("Load");
//        file.add(load);
//        quit = new JMenuItem("Quit");
//        quit.addActionListener(new MenuListener());
//        quit.setActionCommand("Quit");
//        file.add(quit);
//        menubar.add(file);
//
//        help = new JMenu("Help");                
//        rules = new JMenuItem("Rules");
//        rules.addActionListener(new MenuListener());
//        rules.setActionCommand("Rules");
//        help.add(rules);
//        about = new JMenuItem("About");
//        about.addActionListener(new MenuListener());
//        about.setActionCommand("About");
//        help.add(about);
//        menubar.add(help);
    
        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Bomb Counter
        bombCounter = new JLabel("099");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        mainPanel.add(bombCounter, c);
        
        reset = new JButton(":)");
        reset.setActionCommand(":)");
        reset.addActionListener(new ResetListener());
        reset.setPreferredSize(new Dimension(62,26));
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        mainPanel.add(reset, c);
        
        // Time Counter
        timeCounter = new JLabel("999");
//        Font f = new Font("Century Gothic", Font.BOLD, 30);
//        timeCounter.setFont(f);
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        mainPanel.add(timeCounter, c);
        
        timer = new Timer(1000, new TimerListener());
        timer.setInitialDelay(0);
        timer.setRepeats(true);
        time = -1;
        
        // Grid
        gridPanel = new JPanel();
        gridPanel.setBackground(Color.lightGray);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        gridPanel.setLayout(new GridLayout(16, 30));
        gridPanel.addMouseListener(new GridListener());

        board = new Minesweeper(16, 30, 99);

        displayedGrid = new JLabel[16][30];                
        for (int row = 0; row < displayedGrid.length; row++) {
            for (int column = 0; column < displayedGrid[row].length; column++) {
                displayedGrid[row][column] = new JLabel();
                displayedGrid[row][column].setPreferredSize(new Dimension(cellSize,cellSize));
                displayedGrid[row][column].setHorizontalAlignment(JLabel.CENTER);
                displayedGrid[row][column].setBackground(Color.gray);
                displayedGrid[row][column].setOpaque(true);
                displayedGrid[row][column].setBorder(BorderFactory.createLineBorder(new Color(153,153,153)));
                gridPanel.add(displayedGrid[row][column]);
            }
        }
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1;
        mainPanel.add(gridPanel, c);

        display();

        gameWindow.setContentPane(mainPanel);
        gameWindow.pack();
        gameWindow.setResizable(false);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
    
//    class MenuListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String menuItem = e.getActionCommand();
//            switch (menuItem) {
//                case "Save":
//                    for (int row = 0; row < displayedGrid.length; row++) {
//                        for (int column = 0; column < displayedGrid[row].length; column++) {
//                            savedPattern[row][column] = Integer.parseInt(displayedGrid[row][column].getText());
//                        }
//                    }
//                    
//                    JFrame saveWindow = new JFrame("Save");         
//                    saveWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                    JPanel saveContent = new JPanel();
//                    
//                    JLabel saveText = new JLabel("Pattern saved.");
//                    saveText.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
//                    saveContent.add(saveText);
//                    
//                    saveWindow.setContentPane(saveContent);
//                    saveWindow.pack();
//                    saveWindow.setVisible(true);
//                    break;
//                case "Load":                    
//                    board.setPattern(savedPattern);
//                    display();
//                    steps = 0;
//                    stepCounter.setText(Integer.toString(steps));
//                    break;
//                case "Quit":
//                    System.exit(0);
//                    break;
//                case "Rules":
//                    JFrame rulesWindow = new JFrame("Rules");
//                    rulesWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                    JPanel rulesContent = new JPanel();
//                    
//                    JLabel rulesText = new JLabel("<html>For a space that is 'populated':<br>"
//                            + "-Each cell with one or no neighbors dies, as if by solitude.<br>"
//                            + "-Each cell with four or more neighbors dies, as if by overpopulation.<br>"
//                            + "-Each cell with two or three neighbors survives.<br><br>"
//                            + "For a space that is 'empty' or 'unpopulated':<br>"
//                            + "-Each cell with three neighbors becomes populated.</html>");
//                    rulesText.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
//                    rulesContent.add(rulesText);
//
//                    rulesWindow.setContentPane(rulesContent);
//                    rulesWindow.pack();
//                    rulesWindow.setVisible(true);
//                    break;
//                case "About":
//                    JFrame aboutWindow = new JFrame("About");
//                    aboutWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                    JPanel aboutContent = new JPanel();
//                    
//                    JLabel aboutText = new JLabel("Written by Raymond Xia. All rights reserved.");
//                    aboutText.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
//                    aboutContent.add(aboutText);
//                    
//                    aboutWindow.setContentPane(aboutContent);
//                    aboutWindow.pack();
//                    aboutWindow.setVisible(true);
//                    break;
//            }
//        }
//    
    
    }
    
    class GridListener implements MouseListener {
        int mButtonsPressed = 0;
        @Override
        public void mouseReleased(MouseEvent e) {
            if (board.gameIsOver() == 0 ) {
                int x = (e.getX() - 5) / 40;
                int y = (e.getY() - 5) / 40;
                if (!timer.isRunning()) {
                    board.generateBoard(y, x);
                }
                
                if (mButtonsPressed == 1) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        board.revealCell(y, x);
                        timer.start();
                        display();
                    }
                } else {
                    Cell cell = board.getGrid()[y+1][x+1];
                    if (cell.isRevealed() && cell.getValue() == board.countAdjFlags(y+1, x+1)) {
                        board.revealAdjCells(y, x);
                        display();
                    }
                }
                mButtonsPressed--;
            } 
//            System.out.println(mButtonsPressed);
//            System.out.println("Game is over: " + board.gameIsOver());
//            System.out.println(timer.isRunning());
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                int x = (e.getX() - 5) / 40;
                int y = (e.getY() - 5) / 40;
                board.flagCell(y, x);
                display();
                mButtonsPressed++;
            } else if (e.getButton() == MouseEvent.BUTTON1) {
                mButtonsPressed++;
            }
            
//            System.out.println(mButtonsPressed);
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            mButtonsPressed = 0;
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
        
    }
    
    class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            time++;
            if (time < 1000) {
                if (time/10 == 0) {
                    timeCounter.setText("00" + time);
                } else if (time/10 < 10) {
                    timeCounter.setText("0" + time);
                } else if (time/10 < 100) {
                    timeCounter.setText("" + time);
                }
            }
        }
    }
    
    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            board.resetGame();
            time = -1;
            timeCounter.setText("999");
            display();
        }
    }
    
    /**
     * Updates consoleGrid to board's grid and sets displayedGrid to display consoleGrid
     * pre: none
     * post: consoleGrid set to board's grid, displayedGrid set to display consoleGrid
     */
    private void display() {
        Cell[][] grid = board.getGrid();
        int numFlagged = 0;
        for (int row = 1; row < grid.length-1; row++) {
            for (int column = 1; column < grid[row].length-1; column++) {
                if (grid[row][column].isRevealed()) {
                    displayedGrid[row-1][column-1].setBackground(Color.lightGray);
                    
                    if (grid[row][column].getValue() >= 1 && grid[row][column].getValue() <= 8) {
                        displayedGrid[row-1][column-1].setText(grid[row][column].getValue() + "");
                    } else if (grid[row][column].getValue() == -1) {
                        displayedGrid[row - 1][column - 1].setText("x");
                    }

                    switch (grid[row][column].getValue()) {
                        case -1: // represents bombs
                            displayedGrid[row-1][column-1].setForeground(Color.black);
                            break;
                        case 1:
                            displayedGrid[row-1][column-1].setForeground(Color.blue);
                            break;
                        case 2:
                            displayedGrid[row-1][column-1].setForeground(Color.green);
                            break;
                        case 3:
                            displayedGrid[row-1][column-1].setForeground(Color.red);
                            break;
                        case 4:
                            displayedGrid[row-1][column-1].setForeground(Color.darkGray);
                            break;
                        case 5:
                            displayedGrid[row-1][column-1].setForeground(Color.magenta);
                            break;
                        case 6:
                            displayedGrid[row-1][column-1].setForeground(Color.cyan);
                            break;
                        case 7:
                            displayedGrid[row-1][column-1].setForeground(Color.black);
                            break;
                        case 8:
                            displayedGrid[row-1][column-1].setForeground(Color.gray);
                            break;
                        default: 
                            displayedGrid[row - 1][column - 1].setForeground(Color.black);
                            break;
                    }
                } else if (grid[row][column].isFlagged()) {
                    displayedGrid[row-1][column-1].setText("");
                    displayedGrid[row-1][column-1].setBackground(Color.red);
                    numFlagged++;
                } else {
                    displayedGrid[row-1][column-1].setText("");
                    displayedGrid[row-1][column-1].setBackground(Color.gray);
                }
            }
        }
        switch (board.gameIsOver()) {
            case 1:
                timer.stop();
                reset.setText(":(");
                break;
            case 2:
                timer.stop();
                
                JFrame winFrame = new JFrame("You won!");
                
                JPanel winPanel = new JPanel();
                winPanel.setLayout(new BoxLayout(winPanel, BoxLayout.PAGE_AXIS));
                winPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                
                JLabel winMsg = new JLabel("Congratulations!");
                winMsg.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                winPanel.add(winMsg);
                
                JLabel winTime = new JLabel("You beat the game in " + time + " seconds.");
                winTime.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                winPanel.add(winTime);
                
                winFrame.setContentPane(winPanel);
                winFrame.pack();
                winFrame.setLocationRelativeTo(null);
                winFrame.setVisible(true);                
                break;
            default:
                bombCounter.setText("0" + (99 - numFlagged));
                reset.setText(":)");
                break;
        }
    }
    
    private static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        MinesweeperGUI life = new MinesweeperGUI();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runGUI();
            }
        });
    }
    
}
