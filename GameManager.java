import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.Color;

public class GameManager extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorry!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        List<Player> players = InitializePlayers();
        Board board = new Board(players);
        JLayeredPane layeredpanel = board.getLayeredPane();
        frame.add(layeredpanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static List<Player> InitializePlayers(){
        List<Player> players = new ArrayList<Player>();
        players.add(new Player(Color.RED, 4));
        players.add(new Player(Color.BLUE, 20));
        players.add(new Player(Color.GREEN, 50));
        players.add(new Player(Color.YELLOW, 35));
        return players;
    }


}
