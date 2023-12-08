import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.util.*;
import java.awt.FlowLayout;
import java.awt.Component;

public class CardPanel extends JPanel {
    private JLabel cardLabel;
    private JRadioButton optionA;
    private JRadioButton optionB;
    private Integer[] numbers = {0, 1, 2, 3};
    private JComboBox<Integer> pieceSelector = new JComboBox<>(numbers);
    private JButton confirmButton = new JButton("Confirm");
    private JComboBox<Integer> pawnSelector1 = new JComboBox<>(numbers);
    private JComboBox<Integer> pawnSelector2 = new JComboBox<>(numbers);
    private JComboBox<Integer> moveSelector1 = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7});
    private JComboBox<Integer> moveSelector2 = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7});
    private JComboBox<Integer> yourPawnSelector = new JComboBox<>(numbers);
    private JComboBox<Integer> opponentSelector = new JComboBox<>(numbers);
    private JComboBox<Integer> opponentPawnSelector = new JComboBox<>(numbers);
    private Runnable onConfirmCallback;
    private boolean selectionConfirmed = false;
    private GameManager gameManager;

    public CardPanel(String cardText) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        cardLabel = new JLabel("<html><body style='width: 150px'>" + cardText + "</body></html>");
        cardLabel.setForeground(Color.BLACK);
        this.add(cardLabel);
        this.setPreferredSize(new Dimension(200, 250)); 
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.BLACK);
    }

    public CardPanel(String cardText, GameManager gameManager) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        cardLabel = new JLabel("<html><body style='width: 150px'>" + cardText + "</body></html>");
        cardLabel.setForeground(Color.BLACK);
        this.add(cardLabel);
        this.setPreferredSize(new Dimension(200, 250)); 
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.BLACK);
        this.gameManager = gameManager;
    }

    /* 
    public void setupTurn(String newCard, Board board, List<Player> players, int currentPlayer) {
        // Update the card and input components
        UpdateForTurn(newCard, board, players, currentPlayer);

        // Reset selection flag
        selectionConfirmed = false;

        // Change the ActionListener for confirm button
        confirmButton.addActionListener(e -> {
            handleUserSelection(newCard, board, players, currentPlayer);
            selectionConfirmed = true;
            gameManager.initiateNextTurn(this, board, players, currentPlayer);
            System.out.println("FROM SETUPTURN NEXTPLAYER: " + currentPlayer);
        });
    }
    */
    public void resetSelectionConfirmed() {
        selectionConfirmed = false;
    }
    
    public void setSelectionConfirmed(boolean confirmed) {
        this.selectionConfirmed = confirmed;
    }

    public boolean isSelectionConfirmed() {
        return this.selectionConfirmed;
    }

    public void updateCard(String newText) {
        cardLabel.setText("<html><body style='width: 150px'>" + newText + "</body></html>");
    }

    public void UpdateForTurn(String newText, Board board, List<Player> players) {
        int currentPlayer = gameManager.getCurrentPlayer();
        updateCard(newText);
        removeAllInputComponents();
        if (newText.equals(Turn1())) {
            optionA = new JRadioButton("Move from Start");
            optionB = new JRadioButton("Move Forward 1");
            ButtonGroup group = new ButtonGroup();
            group.add(optionA);
            group.add(optionB);
            this.add(optionA);
            this.add(optionB);
        } else if (newText.equals(Turn2())) {
            optionA = new JRadioButton("Move from Start");
            optionB = new JRadioButton("Move Forward 2");
            ButtonGroup group = new ButtonGroup();
            group.add(optionA);
            group.add(optionB);
            this.add(optionA);
            this.add(optionB);
        } else if (newText.equals(Turn3())) {
            optionA = new JRadioButton("Move Forward 3");
            ButtonGroup group = new ButtonGroup();
            group.add(optionA);
            this.add(optionA);
        } else if (newText.equals(Turn4())) {
            optionA = new JRadioButton("Move Backwards 4");
            ButtonGroup group = new ButtonGroup();
            group.add(optionA);
            this.add(optionA);
        } else if (newText.equals(Turn5())) {
            optionA = new JRadioButton("Move Forward 5");
            ButtonGroup group = new ButtonGroup();
            group.add(optionA);
            this.add(optionA);
        } else if (newText.equals(Turn7())) {
            optionA = new JRadioButton("Move Forward 7");
            optionB = new JRadioButton("Split between 2 Pawns (7)");
            ButtonGroup group = new ButtonGroup();
            group.add(optionA);
            group.add(optionB);
            this.add(optionA);
            this.add(optionB);

            this.add(new JLabel("Pawn 1:"));
            this.add(pawnSelector1);
            this.add(new JLabel("Move spaces:"));
            this.add(moveSelector1);

            this.add(new JLabel("Pawn 2:"));
            this.add(pawnSelector2);
            this.add(new JLabel("Move spaces:"));
            this.add(moveSelector2);
            
        } else if (newText.equals(Turn8())) {
            optionA = new JRadioButton("Move Forward 8");
            ButtonGroup group = new ButtonGroup();
            group.add(optionA);
            this.add(optionA);
        } else if (newText.equals(Turn10())) {
            optionA = new JRadioButton("Move Forward 10");
            optionB = new JRadioButton("Move Backwards 1");
            ButtonGroup group = new ButtonGroup();
            group.add(optionA);
            group.add(optionB);
            this.add(optionA);
            this.add(optionB);
        } else if (newText.equals(Turn11())) {
            optionA = new JRadioButton("Move Forward 11");
            optionB = new JRadioButton("Switch with Opponent");
            ButtonGroup group = new ButtonGroup();
            group.add(optionA);
            group.add(optionB);
            this.add(optionA);
            this.add(optionB);
            opponentSelector.removeAllItems();

            for (int i = 0; i < players.size(); i++) {
                if (i != currentPlayer) {
                    opponentSelector.addItem(i); // Add player index or name
                }
            }

            this.add(new JLabel("Your Pawn:"));
            this.add(yourPawnSelector);

            this.add(new JLabel("Opponent:"));
            this.add(opponentSelector);

            this.add(new JLabel("Opponent's Pawn:"));
            this.add(opponentPawnSelector);

        } else if (newText.equals(Turn12())) {
            optionA = new JRadioButton("Move Forward 12");
            ButtonGroup group = new ButtonGroup();
            group.add(optionA);
            this.add(optionA);
        } else if (newText.equals(TurnSorry())) {
            String optionAText = "Swap home pawnnewlinewith opponent";
            optionAText = "<html>" + optionAText.replace("newline", "<br>") + "</html>"; 
            optionA = new JRadioButton(optionAText);
            ButtonGroup group = new ButtonGroup();
            group.add(optionA);
            this.add(optionA);
            opponentSelector.removeAllItems();

            for (int i = 0; i < players.size(); i++) {
                if (i != currentPlayer) {
                    opponentSelector.addItem(i); // Add player index or name
                }
            }
            this.add(new JLabel("Your Pawn:"));
            this.add(yourPawnSelector);

            this.add(new JLabel("Opponent:"));
            this.add(opponentSelector);

            this.add(new JLabel("Opponent's Pawn:"));
            this.add(opponentPawnSelector);
        }
        this.add(pieceSelector);
        this.add(confirmButton);
        System.out.println("BEFORE ACTIONLISTENER" + currentPlayer);
        for (ActionListener al : confirmButton.getActionListeners()) {
            confirmButton.removeActionListener(al);
        }
    
        // Add new action listener
        confirmButton.addActionListener(e -> {
            handleUserSelection(newText, board, players);
        });
        System.out.println("END OF ACTIONLISTENER" + currentPlayer);
        revalidate();
        repaint();
    }
    
    public void handleUserSelection(String CurrentCard, Board board, List<Player> players) {
        int currentPlayer = gameManager.getCurrentPlayer();
        Integer selectedpeice = (Integer) pieceSelector.getSelectedItem();
        if (CurrentCard.equals(Turn1())) {
            if (optionA.isSelected()) {
                players.get(currentPlayer).getPieces().get(selectedpeice).setStarted(true);
            }
            else if (optionB.isSelected()) {
                board.movePiece(players, currentPlayer, selectedpeice, 1);
            }
        } else if (CurrentCard.equals(Turn2())) {
            if (optionA.isSelected()) {
                players.get(currentPlayer).getPieces().get(selectedpeice).setStarted(true);
            }
            else if (optionB.isSelected()) {
                board.movePiece(players, currentPlayer, selectedpeice, 2);
            }
            // HANDLE DRAW AGAIN
        } else if (CurrentCard.equals(Turn3())) {
            if (optionA.isSelected()) {
                board.movePiece(players, currentPlayer, selectedpeice, 3);
            }
        } else if (CurrentCard.equals(Turn4())) {
            if (optionA.isSelected()) {
                board.movePiece(players, currentPlayer, selectedpeice, -4);
            }
        } else if (CurrentCard.equals(Turn5())) {
            if (optionA.isSelected()) {
                board.movePiece(players, currentPlayer, selectedpeice, 5);
            }
        } else if (CurrentCard.equals(Turn7())) {
            if (optionA.isSelected()) {
                board.movePiece(players, currentPlayer, selectedpeice, 7);
            } else if (optionB.isSelected()) {

                int pawn1Index = (Integer) pawnSelector1.getSelectedItem();
                int pawn1Move = (Integer) moveSelector1.getSelectedItem();

                int pawn2Index = (Integer) pawnSelector2.getSelectedItem();
                int pawn2Move = (Integer) moveSelector2.getSelectedItem();

                if (pawn1Move + pawn2Move == 7) {
                    board.movePiece(players, currentPlayer, pawn1Index, pawn1Move);
                    board.movePiece(players, currentPlayer, pawn2Index, pawn2Move);
                } else {
                    // Handle error - total moves exceed 7
                }
            }
        } else if (CurrentCard.equals(Turn8())) {
            if (optionA.isSelected()) {
                board.movePiece(players, currentPlayer, selectedpeice, 8);
            }
        } else if (CurrentCard.equals(Turn10())) {
            if (optionA.isSelected()) {
                board.movePiece(players, currentPlayer, selectedpeice, 10);
            }
            else if (optionB.isSelected()) {
                board.movePiece(players, currentPlayer, selectedpeice, -1);
            }
        } else if (CurrentCard.equals(Turn11())) {
            if (optionA.isSelected()) {
                board.movePiece(players, currentPlayer, selectedpeice, 11);
            }
            else if (optionB.isSelected()) {
                int yourPawnIndex = (Integer) yourPawnSelector.getSelectedItem();
                int opponentIndex = (Integer) opponentSelector.getSelectedItem();
                int opponentPawnIndex = (Integer) opponentPawnSelector.getSelectedItem();

                GamePiece yourPiece = players.get(currentPlayer).getPieces().get(yourPawnIndex);
                GamePiece opponentPiece = players.get(opponentIndex).getPieces().get(opponentPawnIndex);

                int OpponentLocation = opponentPiece.getIndex();
                int YourLocation = yourPiece.getIndex();

                board.movePiece(players, currentPlayer, yourPawnIndex, (61-yourPiece.getIndex())+ OpponentLocation);
                board.movePiece(players, opponentIndex, opponentPawnIndex, (61-opponentPiece.getIndex())+ YourLocation);
            }
        } else if (CurrentCard.equals(Turn12())) {
            if (optionA.isSelected()) {
                board.movePiece(players, currentPlayer, selectedpeice, 12);
            }
        } else if (CurrentCard.equals(TurnSorry())) {
            if (optionA.isSelected()) {
                int yourPawnIndex = (Integer) yourPawnSelector.getSelectedItem();
                int opponentIndex = (Integer) opponentSelector.getSelectedItem();
                int opponentPawnIndex = (Integer) opponentPawnSelector.getSelectedItem();

                GamePiece yourPiece = players.get(currentPlayer).getPieces().get(yourPawnIndex);
                GamePiece opponentPiece = players.get(opponentIndex).getPieces().get(opponentPawnIndex);

                int OpponentLocation = opponentPiece.getIndex();
                int YourLocation = yourPiece.getIndex();
                System.out.println("Opponent home: " + opponentPiece.GetHomeIndex());
                board.movePiece(players, currentPlayer, yourPawnIndex, (61-yourPiece.getIndex())+ OpponentLocation);
                board.movePiece(players, opponentIndex, opponentPawnIndex, (61-opponentPiece.getIndex())+ opponentPiece.GetHomeIndex());
            }
        }
        gameManager.initiateNextTurn(this, board, players);
    }

    public void removeAllInputComponents() {
        Component[] components = this.getComponents();
        for (Component component : components) {
            if (component != cardLabel) {
                this.remove(component);
            }
        }
    }

    public String Turn1() {
        return "Card 1 - Current Player:" + gameManager.getCurrentPlayer();
    }

    public String Turn2() {
        return "Card 2 - Current Player:" + gameManager.getCurrentPlayer();
    }

    public String Turn3() {
        return "Card 3 - Current Player:" + gameManager.getCurrentPlayer();
    }

    public String Turn4() {
        return "Card 4 - Current Player:" + gameManager.getCurrentPlayer();
    }

    public String Turn5() {
        return "Card 5 - Current Player:" + gameManager.getCurrentPlayer();
    }

    public String Turn7() {
        return "Card 7 - Current Player:" + gameManager.getCurrentPlayer();
    }

    public String Turn8() {
        return "Card 8 - Current Player:" + gameManager.getCurrentPlayer();
    }

    public String Turn10() {
        return "Card 10 - Current Player:" + gameManager.getCurrentPlayer();
    }

    public String Turn11() {
        return "Card 11 - Current Player:" + gameManager.getCurrentPlayer();
    }

    public String Turn12() {
        return "Card 12 - Current Player:" + gameManager.getCurrentPlayer();
    }

    public String TurnSorry() {
        return "Sorry Card! - Current Player:" + gameManager.getCurrentPlayer();
    }   

}

