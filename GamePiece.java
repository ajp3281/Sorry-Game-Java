import java.awt.Color;
import java.lang.Math;
import java.sql.Array;
import java.util.ArrayList;


public class GamePiece {
    private ArrayList<Integer> pos;
    private ArrayList<Integer> SafePos;
    private int index;
    private int safeindex;
    private int startLocation;
    private Color color;
    private boolean isSafe;
    private boolean isHome;
    private boolean Started;

    public GamePiece(int index, Color color) {
        this.pos = new ArrayList<Integer>(64);
        for (int i = 0; i < 16; i++) {
            this.pos.add(i);
        }
        
        for (int i = 1; i < 15; i++) {
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
        this.startLocation = index;
        this.safeindex = -1;
        this.Started = false; // false
    }

    public void AddSafeSlots(Color color) {
        this.SafePos = new ArrayList<Integer>(5);
        if (color == Color.RED) {
            for (int i = 1; i < 6; i++) {
                this.SafePos.add(i*16+2);
            }
        } else if (color == Color.BLUE) {
            for (int i = 47; i >= 42; i--) {
                this.SafePos.add(i);
            }
        } else if (color == Color.YELLOW) {
            for (int i = 173 + 16*4; i >= 173; i -= 16) {
                this.SafePos.add(i);
            }
        } else {
            for (int i = 209; i < 214; i++) {
                this.SafePos.add(i);
            }
        }
    }

    public boolean canEnterSafe(int resultIndex) {
        int stepsToSafeZone = this.stepsToSafeZone();
        if (this.color == Color.RED && resultIndex > 2 && resultIndex <= 7) {
            return true;
        } else if (this.color == Color.BLUE && resultIndex > 18 && resultIndex <= 23) {
            return true;
        } else if (this.color == Color.YELLOW && resultIndex > 33 && resultIndex <= 38) {
            return true;
        } else if (this.color == Color.GREEN && resultIndex > 48 && resultIndex <= 53) {
            return true;
        } else {
            return false;
        }
    }

    public int getSafeIndex() {
        return this.safeindex;
    }

    public int getStartLocation() {
        return this.startLocation;
    }

    public int stepsToSafeZone() {
        int totalPos = 60;
        int safeZoneIndex;
        if (this.color == Color.RED) {
            safeZoneIndex = 2;
        } else if (this.color == Color.BLUE) {
            safeZoneIndex = 17;
        } else if (this.color == Color.YELLOW) {
            safeZoneIndex = 33;
        } else {
            safeZoneIndex = 47;
        }

        int steps = safeZoneIndex - this.index;
        if (steps < 0) {
            steps += totalPos;
        }

        return steps;
    }


    public void moveSafe(int x) {
        this.safeindex += x;
        this.setSafe(true);
    }

    public int getSafePos() {
        return this.SafePos.get(this.safeindex);
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
        if (this.index + x < 0) {
            this.index = 60 + (this.index + x);
            return;
        }
        this.index = (this.index + x) % 60;
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