import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.Color;


public class GameManager extends JFrame {
    private int currentPlayer = -1;

    public GameManager() {
        super("Sorry!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 1200);

        List<Player> players = InitializePlayers();
        Board board = new Board(players);
        JLayeredPane layeredpanel = board.getLayeredPane();
        add(layeredpanel);
        pack();
        
        CardPanel cardPanel = new CardPanel("Welcome to Sorry!", this);
        cardPanel.setBackground(Color.WHITE);
        int centerX = (1450 - cardPanel.getPreferredSize().width) / 2;
        int centerY = (800 - cardPanel.getPreferredSize().height) / 2;
        cardPanel.setBounds(centerX, centerY, cardPanel.getPreferredSize().width, cardPanel.getPreferredSize().height);
        layeredpanel.add(cardPanel, Integer.valueOf(2));

        setVisible(true);

        initiateNextTurn(cardPanel, board, players);
    }
    public static void main(String[] args) {
        new GameManager();
    }

    public static List<Player> InitializePlayers(){
        List<Player> players = new ArrayList<Player>();
        players.add(new Player(Color.RED, 4));
        players.add(new Player(Color.BLUE, 20));
        players.add(new Player(Color.YELLOW, 35));
        players.add(new Player(Color.GREEN, 50));
        return players;
    }

    public void initiateNextTurn(CardPanel cardPanel, Board board, List<Player> players) {
        currentPlayer = (currentPlayer + 1) % 4;
        System.out.println("NEW TURN");
        GetNextCard(cardPanel, board, players, currentPlayer);
        cardPanel.setBackground(players.get(currentPlayer).getColor()); // Update background color
    }


    

    private boolean GameOver(List<Player> players) {
        int count = 0;
        for (Player player : players) {
            if (player.isFinished()) {
                count++;
            }
        }
        return count == 3;
    }

    private void GetNextCard(CardPanel cardPanel, Board board, List<Player> players, int currPlayer) {
        System.out.println("Currentplayer" + currentPlayer);
        System.out.println("currPlayer" + currPlayer);
        System.out.println("Currentplayer" + players.get(currentPlayer).getColor());
        int x = (int)(Math.random() * 11) + 1;
        if (x == 1) {
            cardPanel.UpdateForTurn(cardPanel.Turn1(), board, players);
        } else if (x == 2) {
            cardPanel.UpdateForTurn(cardPanel.Turn2(), board, players);
        } else if (x == 3) {
            cardPanel.UpdateForTurn(cardPanel.Turn3(), board, players);
        } else if (x == 4) {
            cardPanel.UpdateForTurn(cardPanel.Turn4(), board, players);
        } else if (x == 5) {
            cardPanel.UpdateForTurn(cardPanel.Turn5(), board, players);
        } else if (x == 6) {
            cardPanel.UpdateForTurn(cardPanel.Turn7(), board, players);
        } else if (x == 7) {
            cardPanel.UpdateForTurn(cardPanel.Turn8(), board, players);
        } else if (x == 8) {
            cardPanel.UpdateForTurn(cardPanel.Turn10(), board, players);
        } else if (x == 9) {
            cardPanel.UpdateForTurn(cardPanel.Turn11(), board, players);
        } else if (x == 10) {
            cardPanel.UpdateForTurn(cardPanel.Turn12(), board, players);
        } else if (x == 11){
            cardPanel.UpdateForTurn(cardPanel.TurnSorry(), board, players);
        }
    }

    public GameManager getInstance() {
        return this;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }



}
