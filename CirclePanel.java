import java.util.Scanner;
import javax.swing.*;

import java.awt.*;
import java.util.*;

public class CirclePanel extends JPanel {
        private Color circleColor;
        private String text;
        private GamePiece gamepiece;

        public CirclePanel(Color circleColor, String text) {
            this.circleColor = circleColor;
            this.text = text;
        }

        public CirclePanel(Color circleColor, String text, int diameter) {
            this(circleColor, text);
            setPreferredSize(new Dimension(diameter,diameter));
        }

        public void SetText(String text) {
            this.text = text.substring(0, text.length()-1);
        }

        public String GetText() {
            return this.text;
        }

        public void setGamePiece(GamePiece gamePiece) {
            this.gamepiece = gamePiece;
        }

        public GamePiece getGamePiece() {
            return this.gamepiece;
        }

        public void setCircleColor(Color circleColor) {
            this.circleColor = circleColor;
        }

        public Color getCircleColor() {
            return this.circleColor;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(circleColor);
            g.fillOval(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 12)); 
            FontMetrics fm = g.getFontMetrics();
            int textX = (getWidth() - fm.stringWidth(text)) / 2;
            int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g.drawString(text, textX, textY); 
        }
    }
