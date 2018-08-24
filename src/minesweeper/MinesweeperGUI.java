package minesweeper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Raymond
 */
public class MinesweeperGUI {
//    JFrame introWindow;
//    JPanel introPanel;
//    JLabel introMinesweeperTitle, scalePrompt;
//    JComboBox diffComboBox;
//    JButton play;
    
    JFrame gameWindow;
    JPanel mainPanel;
    JPanel gridPanel;
    JLabel gameLifeTitle;
//    private int steps;
    private Minesweeper board;
    JButton[][] displayedGrid;
    private int[][] consoleGrid;   
//    private int[][] savedPattern;
//    JButton next, start, clear;  
//    Timer timer;
//    JMenuBar menubar;
//    JMenu file, help;
//    JMenuItem save, load, quit, rules, about;    
    
    public MinesweeperGUI() {
//        /* Intro Window */    
//        introWindow = new JFrame("LifeGUI");
//        introWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        
//        // Intro Panel
//        introPanel = new JPanel();
//        introPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
//        introPanel.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        
//        // Intro Title
//        introMinesweeperTitle = new JLabel("Minesweeper");
//        Font f = new Font("Century Gothic", Font.PLAIN, 40);
//        introMinesweeperTitle.setFont(f);
//        c.gridx = 0;
//        c.gridy = 0;
//        c.gridwidth = 2;
//        c.gridheight = 1;
//        c.insets = new Insets(5,5,5,5);
//        introPanel.add(introMinesweeperTitle, c);
//        
//        // Difficulty Prompt
//        scalePrompt = new JLabel("Choose a difficulty: ", JLabel.TRAILING);
//        c.gridx = 0;
//        c.gridy = 1;
//        c.gridwidth = 1;
//        c.gridheight = 1;
//        c.weightx = 0.5;
//        c.anchor = GridBagConstraints.EAST;
//        introPanel.add(scalePrompt, c);
        
//        // Difficulty Combo Box
//        String[] difficulties = {"Beginner", "Intermediate", "Expert"};
//        diffComboBox = new JComboBox(difficulties);
//        diffComboBox.addActionListener(new DifficultyListener());
//        c.gridx = 1;
//        c.gridy = 1;
//        c.gridwidth = 1;
//        c.gridheight = 1;
//        c.weightx = 0.5;
//        c.anchor = GridBagConstraints.WEST;        
//        introPanel.add(diffComboBox, c);
        
//        // Play Button
//        play = new JButton("Play");
//        play.addActionListener(new PlayListener());
//        c.gridx = 0;
//        c.gridy = 2;
//        c.gridwidth = 2;
//        c.gridheight = 1;
//        c.anchor = GridBagConstraints.CENTER;
//        introPanel.add(play, c);
//        
//        introWindow.setContentPane(introPanel);
//        introWindow.pack();
//        introWindow.setVisible(true);       
//    }
    
//    class PlayListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            scale = Integer.parseInt(scaleTextInput.getText());
//            if (scale < 50){
//                scalePrompt.setText("Must be at least 50!");
//            } else {                
//                introWindow.setVisible(false);
//                introWindow.dispose();

                /* Game Window */
                gameWindow = new JFrame("LifeGUI");
                gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//                // Menu Bar
//                menubar = new JMenuBar();
//                gameWindow.setJMenuBar(menubar);
//
//                file = new JMenu("File");                
//                save = new JMenuItem("Save");
//                save.addActionListener(new MenuListener());
//                save.setActionCommand("Save");
//                savedPattern = new int[scale][scale];
//                file.add(save);
//                load = new JMenuItem("Load");
//                load.addActionListener(new MenuListener());
//                load.setActionCommand("Load");
//                file.add(load);
//                quit = new JMenuItem("Quit");
//                quit.addActionListener(new MenuListener());
//                quit.setActionCommand("Quit");
//                file.add(quit);
//                menubar.add(file);
//
//                help = new JMenu("Help");                
//                rules = new JMenuItem("Rules");
//                rules.addActionListener(new MenuListener());
//                rules.setActionCommand("Rules");
//                help.add(rules);
//                about = new JMenuItem("About");
//                about.addActionListener(new MenuListener());
//                about.setActionCommand("About");
//                help.add(about);
//                menubar.add(help);

                // Main Panel
                mainPanel = new JPanel();
                mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                mainPanel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();

                // Minesweeper Title
                gameLifeTitle = new JLabel("Minesweeper");
                Font f = new Font("Century Gothic", Font.BOLD, 30);
                gameLifeTitle.setFont(f);
                c.gridx = 0;
                c.gridy = 0;
                c.gridwidth = 6;
                c.gridheight = 1;
                mainPanel.add(gameLifeTitle, c);

                // Grid
                gridPanel = new JPanel();
                gridPanel.setBackground(Color.lightGray);
                gridPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                gridPanel.setLayout(new GridLayout(16, 30));
                gridPanel.addMouseListener(new FlagListener());
                
                board = new Minesweeper(16, 30, 99);
                
                displayedGrid = new JButton[16][30];                
                for (int row = 0; row < displayedGrid.length; row++) {
                    for (int column = 0; column < displayedGrid[row].length; column++) {
                        displayedGrid[row][column] = new JButton();
                        displayedGrid[row][column].setPreferredSize(new Dimension(20,20));
                        displayedGrid[row][column].setBackground(Color.gray);
                        displayedGrid[row][column].setOpaque(true);
                        displayedGrid[row][column].setBorder(BorderFactory.createLineBorder(new Color(153,153,153)));
                        displayedGrid[row][column].addActionListener(new GridListener());
                        displayedGrid[row][column].setActionCommand(row + "," + column);
                        gridPanel.add(displayedGrid[row][column]);
                    }
                }
                c.gridx = 0;
                c.gridy = 1;
                c.gridwidth = 6;
                c.gridheight = 1;
                mainPanel.add(gridPanel, c);
                
                display();

//                // Next Button
//                next = new JButton("Next");
//                next.addActionListener(new NextListener());
//                c.gridx = 0;
//                c.gridy = 2;
//                c.gridwidth = 1;
//                c.gridheight = 1;
//                c.insets = new Insets(10,0,0,0);
//                mainPanel.add(next, c);
//
//                // Start Button
//                start = new JButton("Start");
//                start.setActionCommand("Start");
//                start.addActionListener(new StartStopListener());
//                start.setPreferredSize(new Dimension(62,26));
//                c.gridx = 1;
//                c.gridy = 2;
//                c.gridwidth = 1;
//                c.gridheight = 1;
//                mainPanel.add(start, c);
//
//                // Timer 
//                timer = new Timer(500, new NextListener());
//                timer.setRepeats(true);
//
//                // Step Counter
//                stepCounter = new JLabel("0");
//                c.gridx = 5;
//                c.gridy = 2;
//                c.gridwidth = 1;
//                c.gridheight = 1;
//                c.anchor = GridBagConstraints.LINE_END;
//                mainPanel.add(stepCounter, c);
                
                gameWindow.setContentPane(mainPanel);
                gameWindow.pack();
                gameWindow.setResizable(false);
                gameWindow.setLocationRelativeTo(null);
                gameWindow.setVisible(true);
//            }
//        }
//    }
    
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
//    }
    }

    class GridListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] coordinates = parseCoordinates(e.getActionCommand());
            int x = coordinates[1];
            int y = coordinates[0];
            
            board.revealCell(y, x);
            if (board.gameIsOver()) {
                for (int row = 0; row < displayedGrid.length; row++) {
                    for (int column = 0; column < displayedGrid[row].length; column++) {
                        displayedGrid[row][column].setEnabled(false);
                    } 
                }
            }
            display();
        }
        
        private int[] parseCoordinates(String coords) {
            int[] coordsArray = new int[2]; 
            String[] temp = coords.split(",");
            for (int i = 0; i < temp.length; i++) {
                coordsArray[i] = Integer.parseInt(temp[i]);
            }
            return coordsArray;
        }
        
    }
    
    class FlagListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
//                if (e.)
            }
            System.out.println(e.getX() + ", " + e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
        
    class StartStopListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String eventName = e.getActionCommand();
            
        }
    }
    
    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
    /**
     * Updates consoleGrid to board's grid and sets displayedGrid to display consoleGrid
     * pre: none
     * post: consoleGrid set to board's grid, displayedGrid set to display consoleGrid
     */
    private void display() {
        Cell[][] grid = board.getGrid();
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
                        case -1:
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
                            displayedGrid[row-1][column-1].setForeground(Color.CYAN);
                            break;
                        default: 
                            displayedGrid[row - 1][column - 1].setForeground(Color.black);
                            break;
                    }
                }
            }
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
