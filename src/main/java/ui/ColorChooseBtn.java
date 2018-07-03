package ui;

import javax.swing.*;
import java.awt.*;

public class ColorChooseBtn extends DrawableContent {
    private Color color;
    private JColorChooser jColorChooser;
    private LeftPanel lp;

    public ColorChooseBtn(Color color, LeftPanel lp) {
        this.color = color;
        this.lp = lp;
        jColorChooser = new JColorChooser(color);
    }

    public void hitTest(int x, int y) {
        if (this.x <= x && x <= this.x + width && this.y <= y && y <= this.y + height) {
            JDialog jDialog = new JDialog();
            jDialog.setBounds(150, 150, 400, 200);
            jDialog.setContentPane(jColorChooser);
            jDialog.setVisible(true);
        }
    }

    public JColorChooser getjColorChooser() {
        return jColorChooser;
    }

    @Override
    public void update() {
        color = jColorChooser.getColor();
        lp.setCurrentColor(color);

    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);
    }
}
