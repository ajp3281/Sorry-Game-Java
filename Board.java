import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.util.*;
import java.util.stream.Collectors;
import java.awt.Component;

public class Board  {
    private static final int BOARD_SIZE = 16;
    private static final int NUM_PLAYERS = 4;
    private JPanel boardPanel;
    private JLayeredPane layeredPane;

    public Board(List<Player> players) {
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1450, 800)); 

        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        setupBoard(boardPanel, players);
        boardPanel.setBounds(0, 0, 1450, 900); 

        layeredPane.add(boardPanel, Integer.valueOf(1));
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }



    private static void setupBoard(JPanel boardPanel, List<Player> players) {
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            JPanel square;
    
            if (i == 82) {
                square = new CirclePanel(Color.RED, "HOME (P0)");
                square.setBackground(Color.WHITE);
            } else if (i == 42) {
                square = new CirclePanel(Color.BLUE, "HOME (P1)");
                square.setBackground(Color.WHITE);
            } else if (i == 213) {
                square = new CirclePanel(Color.GREEN, "HOME (P3)");
                square.setBackground(Color.WHITE);
            } else if (i == 173) {
                square = new CirclePanel(Color.YELLOW, "HOME (P2)");
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
                int i = 0;
                for (GamePiece piece : players.get(j).getPieces()) {
                    int boardPosition = piece.getPos(); 
                    JPanel panel = (JPanel) boardPanel.getComponent(boardPosition);
                    CirclePanel pieceRepresentation = new CirclePanel(players.get(j).getColor(), Integer.toString(i++) + 'S', 21);
                    pieceRepresentation.setGamePiece(piece);
                    pieceRepresentation.setBackground(Color.BLACK);
                    panel.add(pieceRepresentation);
                }
            }
            else if (players.get(j).getColor() == Color.BLUE) {
                int i = 0;
                for (GamePiece piece : players.get(j).getPieces()) {
                    int boardPosition = piece.getPos(); 
                    JPanel panel = (JPanel) boardPanel.getComponent(boardPosition);
                    CirclePanel pieceRepresentation = new CirclePanel(players.get(j).getColor(), Integer.toString(i++) + 'S', 21);
                    pieceRepresentation.setGamePiece(piece);
                    pieceRepresentation.setBackground(Color.BLACK);
                    panel.add(pieceRepresentation);
                }
            }
            else if (players.get(j).getColor() == Color.GREEN) {
                int i = 0;
                for (GamePiece piece : players.get(j).getPieces()) {
                    int boardPosition = piece.getPos(); 
                    JPanel panel = (JPanel) boardPanel.getComponent(boardPosition);
                    CirclePanel pieceRepresentation = new CirclePanel(players.get(j).getColor(), Integer.toString(i++) + 'S', 21);
                    pieceRepresentation.setGamePiece(piece);
                    pieceRepresentation.setBackground(Color.BLACK);
                    panel.add(pieceRepresentation);
                }
            }
            else if (players.get(j).getColor() == Color.YELLOW) {
                int i = 0;
                for (GamePiece piece : players.get(j).getPieces()) {
                    int boardPosition = piece.getPos(); 
                    JPanel panel = (JPanel) boardPanel.getComponent(boardPosition);
                    CirclePanel pieceRepresentation = new CirclePanel(players.get(j).getColor(), Integer.toString(i++) + 'S', 21);
                    pieceRepresentation.setGamePiece(piece);
                    pieceRepresentation.setBackground(Color.BLACK);
                    panel.add(pieceRepresentation);
                }
            }
        }

    }

    public void RemoveStartIndex(GamePiece pieceToRemove) {
        int currentPosition = pieceToRemove.getPos();
        JPanel panel = (JPanel) boardPanel.getComponent(currentPosition);
        for (Component comp : panel.getComponents()) {
            if (comp instanceof CirclePanel) {
                CirclePanel circle = (CirclePanel) comp;
                if (circle.getGamePiece().equals(pieceToRemove)) {
                    circle.SetText(circle.GetText());
                    panel.revalidate();
                    panel.repaint();
                }
            }
        }
     
    }

    
    public void swapPlayerPiecesOnBoard(Player playerOne, Player playerTwo) {
        List<Component> playerOneComponents = new ArrayList<>();
        List<Component> playerTwoComponents = new ArrayList<>();
        List<Integer> playerOnePositions = new ArrayList<>();
        List<Integer> playerTwoPositions = new ArrayList<>();
    
        for (GamePiece piece : playerOne.getPieces()) {
            int pos = piece.getPos();
            playerOnePositions.add(pos);
            JPanel panel = (JPanel) boardPanel.getComponent(pos);
            for (Component comp : panel.getComponents()) {
                playerOneComponents.add(comp);
                panel.remove(comp);
            }
            panel.revalidate();
            panel.repaint();
        }
    
        for (GamePiece piece : playerTwo.getPieces()) {
            int pos = piece.getPos();
            playerTwoPositions.add(pos);
            JPanel panel = (JPanel) boardPanel.getComponent(pos);
            for (Component comp : panel.getComponents()) {
                playerTwoComponents.add(comp);
                panel.remove(comp);
            }
            panel.revalidate();
            panel.repaint();
        }
    
        for (int i = 0; i < playerOnePositions.size(); i++) {
            JPanel panel = (JPanel) boardPanel.getComponent(playerOnePositions.get(i));
            if (i < playerTwoComponents.size()) {
                panel.add(playerTwoComponents.get(i));
                panel.revalidate();
                panel.repaint();
            }
        }
    
        for (int i = 0; i < playerTwoPositions.size(); i++) {
            JPanel panel = (JPanel) boardPanel.getComponent(playerTwoPositions.get(i));
            if (i < playerOneComponents.size()) {
                panel.add(playerOneComponents.get(i));
                panel.revalidate();
                panel.repaint();
            }
        }
    }
    
    
    

    
    public void movePiece(List<Player> players, int currentPlayer, int pieceIndex, int newIndex) {
        GamePiece pieceToMove = players.get(currentPlayer).getPieces().get(pieceIndex);
        int currentPosition = pieceToMove.getPos(); 
        if (!pieceToMove.isStarted())
        {
            return;
        }
        if (pieceToMove.isSafe() == true)
        {
            currentPosition = pieceToMove.getSafePos();
        } 
        if (pieceToMove.isStarted() == false || pieceToMove.isHome() == true) {
            return;
        }

        
        JPanel currentPanel = (JPanel) boardPanel.getComponent(currentPosition);
        CirclePanel movingPiece = removeGamePieceFromPanel(currentPanel, pieceToMove);
        int AfterMove;
        int resIndex = pieceToMove.getIndex()+newIndex;
        if (pieceToMove.isSafe()) {
            pieceToMove.moveSafe(newIndex);
            AfterMove = pieceToMove.getSafePos();
        } else if (pieceToMove.canEnterSafe(resIndex) && currentPosition < pieceToMove.getStartLocation()){
            int stepsToSafeZone = pieceToMove.stepsToSafeZone();
            int stepsInSafeZone = newIndex - stepsToSafeZone;
            pieceToMove.moveSafe(stepsInSafeZone);
            AfterMove = pieceToMove.getSafePos();
        } else {
            pieceToMove.move(newIndex);
            AfterMove = pieceToMove.getPos();
        }
        if (movingPiece != null) {
            JPanel newPanel = (JPanel) boardPanel.getComponent(AfterMove);
            newPanel.add(movingPiece);
            
            newPanel.revalidate();
            newPanel.repaint();
        }
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