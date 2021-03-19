package de.odin.odininventories.inventory.util;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position minus() {
        this.x -= 1;
        this.y -= 1;
        return this;
    }

    public int asSlot() {
        return x * 9 + y;
    }
}
