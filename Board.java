import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.util.*;
import java.awt.Component;

public class Board  {
    private static final int BOARD_SIZE = 16;
    private static final int NUM_PLAYERS = 4;
    private JPanel boardPanel;
    private JLayeredPane layeredPane;

    public Board(List<Player> players) {
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1450, 800)); // Set the preferred size based on your needs

        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        setupBoard(boardPanel, players);
        boardPanel.setBounds(0, 0, 1450, 800); // Set bounds for the board panel

        // Add the board panel to the layered pane
        layeredPane.add(boardPanel, Integer.valueOf(1));

        // Create and add the card panel
        CardPanel cardPanel = new CardPanel("Welcome to Sorry!");
        cardPanel.setBackground(Color.WHITE);
        // Calculate center position
        int centerX = (1450 - cardPanel.getPreferredSize().width) / 2;
        int centerY = (800 - cardPanel.getPreferredSize().height) / 2;
        cardPanel.setBounds(centerX, centerY, cardPanel.getPreferredSize().width, cardPanel.getPreferredSize().height);
        cardPanel.UpdateForTurn(cardPanel.TurnSorry(), this, players, 0);
        // FIGURE OUT HOW TO HANDLE CURRENTPLAYER


        // Add the card panel to the layered pane
        layeredPane.add(cardPanel, Integer.valueOf(2));
        //cardPanel.UpdateForTurn(cardPanel.TurnSorry(), this, players, 1);
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }



    private static void setupBoard(JPanel boardPanel, List<Player> players) {
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            JPanel square;
    
            if (i == 82) {
                square = new CirclePanel(Color.RED, "HOME");
                square.setBackground(Color.WHITE);
            } else if (i == 42) {
                square = new CirclePanel(Color.BLUE, "HOME");
                square.setBackground(Color.WHITE);
            } else if (i == 213) {
                square = new CirclePanel(Color.GREEN, "HOME");
                square.setBackground(Color.WHITE);
            } else if (i == 173) {
                square = new CirclePanel(Color.YELLOW, "HOME");
                square.setBackground(Color.WHITE);
            } else if (i == 20) {
                square = new CirclePanel(Color.RED, "START");
                square.setBackground(Color.WHITE);
                
            } else if (i == 78) {
                square = new CirclePanel(Color.BLUE, "START");
                square.setBackground(Color.WHITE);
            } else if (i == 177) {
                square = new CirclePanel(Color.GREEN, "START");
                square.setBackground(Color.WHITE);
            } else if (i == 235) {
                square = new CirclePanel(Color.YELLOW, "START");
                square.setBackground(Color.WHITE);
            } else {
                square = new JPanel();
    
                
                if (isPerimeter(i, BOARD_SIZE)) {
                    square.setBackground(Color.LIGHT_GRAY);
                    square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if (i > 16 && i <= 82 && i % 16 == 2) {
                    square.setBackground(Color.RED);
                    square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if (i >= 43 && i < 48) {
                    square.setBackground(Color.BLUE);
                    square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if (i > 208 && i < 213) {
                    square.setBackground(Color.GREEN);
                    square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if (i > 176 && i < 240 && i % 16 == 13) {
                    square.setBackground(Color.YELLOW);
                    square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                else {
                    square.setBackground(Color.WHITE);
                }
            }
            boardPanel.add(square);
        }
        for (int j = 0; j < NUM_PLAYERS; j++) {
            if (players.get(j).getColor() == Color.RED) {
                for (GamePiece piece : players.get(j).getPieces()) {
                    int boardPosition = piece.getPos(); 
                    JPanel panel = (JPanel) boardPanel.getComponent(boardPosition);
                    CirclePanel pieceRepresentation = new CirclePanel(players.get(j).getColor(), "");
                    pieceRepresentation.setGamePiece(piece);
                    pieceRepresentation.setBackground(Color.BLACK);
                    panel.add(pieceRepresentation);
                }
            }
            else if (players.get(j).getColor() == Color.BLUE) {
                for (GamePiece piece : players.get(j).getPieces()) {
                    int boardPosition = piece.getPos(); 
                    JPanel panel = (JPanel) boardPanel.getComponent(boardPosition);
                    CirclePanel pieceRepresentation = new CirclePanel(players.get(j).getColor(), "");
                    pieceRepresentation.setGamePiece(piece);
                    pieceRepresentation.setBackground(Color.BLACK);
                    panel.add(pieceRepresentation);
                }
            }
            else if (players.get(j).getColor() == Color.GREEN) {
                for (GamePiece piece : players.get(j).getPieces()) {
                    int boardPosition = piece.getPos(); 
                    JPanel panel = (JPanel) boardPanel.getComponent(boardPosition);
                    CirclePanel pieceRepresentation = new CirclePanel(players.get(j).getColor(), "");
                    pieceRepresentation.setGamePiece(piece);
                    pieceRepresentation.setBackground(Color.BLACK);
                    panel.add(pieceRepresentation);
                }
            }
            else if (players.get(j).getColor() == Color.YELLOW) {
                for (GamePiece piece : players.get(j).getPieces()) {
                    int boardPosition = piece.getPos(); 
                    JPanel panel = (JPanel) boardPanel.getComponent(boardPosition);
                    CirclePanel pieceRepresentation = new CirclePanel(players.get(j).getColor(), "");
                    pieceRepresentation.setGamePiece(piece);
                    pieceRepresentation.setBackground(Color.BLACK);
                    panel.add(pieceRepresentation);
                }
            }
        }

    }
    
    public void movePiece(List<Player> players, int currentPlayer, int pieceIndex, int newPosition) {
        GamePiece pieceToMove = players.get(currentPlayer).getPieces().get(pieceIndex);
        int currentPosition = pieceToMove.getPos();
    
        JPanel currentPanel = (JPanel) boardPanel.getComponent(currentPosition);
        CirclePanel movingPiece = removeGamePieceFromPanel(currentPanel, pieceToMove);
        System.out.println(pieceToMove.getIndex());
        pieceToMove.move(newPosition);
        System.out.println(pieceToMove.getIndex());
        newPosition = pieceToMove.getPos();
        if (movingPiece != null) {
            JPanel newPanel = (JPanel) boardPanel.getComponent(newPosition);
            newPanel.add(movingPiece);
            newPanel.revalidate();
            newPanel.repaint();
        }
        System.out.println(pieceToMove.getIndex());
    }

    
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    private CirclePanel removeGamePieceFromPanel(JPanel panel, GamePiece pieceToRemove) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof CirclePanel) {
                CirclePanel circle = (CirclePanel) comp;
                if (circle.getGamePiece().equals(pieceToRemove)) {
                    panel.remove(circle);
                    panel.revalidate();
                    panel.repaint();
                    return circle;
                }
            }
        }
        return null;
    }

    private static boolean isPerimeter(int index, int size) {
        int row = index / size;
        int col = index % size;
        return row == 0 || col == 0 || row == size - 1 || col == size - 1;
    }

}