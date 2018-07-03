package ui;

import java.awt.*;

public abstract class DrawableContent {

    protected int x;

    protected int y;

    protected int width;

    protected int height;

    public DrawableContent() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
    }

    public void resetDimension(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update();

    public abstract void paint(Graphics graphics);

}
