import java.awt.Color;
import java.lang.Math;
import java.sql.Array;
import java.util.ArrayList;


public class GamePiece {
    private ArrayList<Integer> pos;
    private ArrayList<Integer> SafePos;
    private int index;
    private Color color;
    private boolean isSafe;
    private boolean isHome;
    private boolean Started;

    public GamePiece(int index, Color color) {
        this.pos = new ArrayList<Integer>(64);
        for (int i = 0; i < 16; i++) {
            this.pos.add(i);
        }
        
        for (int i = 1; i < 16; i++) {
            pos.add(16 * i - 1);
        }
        
        for (int i = 255; i > 240; i--) {
            pos.add(i);
        }
        
        for (int i = 15; i > 0; i--) {
            pos.add(16 * i);
        }
        this.color = color;
        this.isSafe = false;
        this.isHome = false;
        this.index = index;
        this.Started = true; // false
    }

    public void AddSafeSlots(Color color) {
        this.SafePos = new ArrayList<Integer>(5);
        if (color == Color.RED) {
            for (int i = 1; i < 6; i++) {
                System.out.println(i*16+2);
                this.SafePos.add(i*16+2);
            }
        }
    }

    public boolean canEnterSafe(int index,Color color) {
        if (index >= 2 && index <= 6 && color == Color.RED) {
            this.setSafe(true);
            return true;
        }
        return false;
    }

    public void setHome(boolean isHome) {
        this.isHome = isHome;
    }

    public boolean isHome() {
        return this.isHome;
    }

    public int GetHomeIndex() {
        if (this.color == Color.RED) {
            return 4;
        } else if (this.color == Color.BLUE) {
            return 20;
        } else if (this.color == Color.GREEN) {
            return 50;
        } else {
            return 35;
        }
    }

    public void setHomePos() {
        if (this.color == Color.RED) {
            this.pos.set(this.index, 4);
        } else if (this.color == Color.BLUE) {
            this.pos.set(this.index, 20);
        } else if (this.color == Color.GREEN) {
            this.pos.set(this.index, 50);
        } else {
            this.pos.set(this.index, 35);
        }
        this.setHome(true);
    }

    public void setSafe(boolean isSafe) {
        this.isSafe = isSafe;
    }

    public boolean isSafe() {
        return this.isSafe;
    }

    public void setStarted(boolean Started) {
        this.Started = Started;
    }

    public boolean isStarted() {
        return this.Started;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void move(int x) {
        this.index = (this.index + x) % 61;
    }

    public int getPos() {
        return this.pos.get(this.index);
    }

    public void setPos(int pos) {
        this.pos.set(this.index, pos);
    }


    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public ArrayList<Integer> getPosList() {
        return this.pos;
    }


}