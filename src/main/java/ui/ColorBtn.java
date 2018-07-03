package ui;

import java.awt.*;

public class ColorBtn extends DrawableContent {

    private Color color;

    private LeftPanel leftPanel;

    public ColorBtn(Color color, LeftPanel leftPanel) {
        this.color = color;
        this.leftPanel = leftPanel;
    }

    public void hitTest(int x, int y) {
        if (this.x <= x && x <= this.x + width && this.y <= y && y <= this.y + height) {
            leftPanel.setCurrentColor(color);
            leftPanel.getColorChooseBtn().getjColorChooser().setColor(color);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);
    }
}
