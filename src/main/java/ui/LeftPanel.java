package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LeftPanel extends JPanel {

    private List<ColorBtn> colors;

    private ColorChooseBtn colorChooseBtn;

    private JSlider sliderPane;

    private Color currentColor;


    LeftPanel() {
        colors = new ArrayList<>();
        colorChooseBtn = new ColorChooseBtn(Color.BLACK, this);
        sliderPane = new JSlider(1, 10, 5);
        currentColor = Color.BLACK;

        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(100, 100));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                colors.forEach(colorBtn -> colorBtn.hitTest(e.getX(), e.getY()));
                colorChooseBtn.hitTest(e.getX(), e.getY());
            }
        });
        add(sliderPane, BorderLayout.SOUTH);

        colors.add(new ColorBtn(Color.RED, this));
        colors.add(new ColorBtn(Color.ORANGE, this));
        colors.add(new ColorBtn(Color.YELLOW, this));
        colors.add(new ColorBtn(Color.GREEN, this));
        colors.add(new ColorBtn(Color.CYAN, this));
        colors.add(new ColorBtn(Color.BLUE, this));
        colors.add(new ColorBtn(Color.PINK, this));
        colors.add(new ColorBtn(Color.BLACK, this));
        colors.add(new ColorBtn(Color.WHITE, this));
        colors.add(new ColorBtn(Color.GRAY, this));
        colors.add(new ColorBtn(Color.DARK_GRAY, this));
        colors.add(new ColorBtn(Color.LIGHT_GRAY, this));
    }

    public ColorChooseBtn getColorChooseBtn() {
        return colorChooseBtn;
    }

    public JSlider getSliderPane() {
        return sliderPane;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        for (int i = 0; i < 12; i++) {
            if (i % 2 == 0) {
                colors.get(i).resetDimension(getWidth() * 2 / 100, (getHeight() * 2 / 100 + getHeight() * 1 / 10) * (i / 2) + getHeight() * 2 / 100, getWidth() * 9 / 10 / 2, getHeight() * 1 / 10);
            } else {
                colors.get(i).resetDimension(getWidth() * 98 / 100 - getWidth() * 9 / 10 / 2, (getHeight() * 2 / 100 + getHeight() * 1 / 10) * (i / 2) + getHeight() * 2 / 100, getWidth() * 9 / 10 / 2, getHeight() * 1 / 10);
            }
        }
        colorChooseBtn.resetDimension(getWidth() * 2 / 100, getHeight() * 72 / 100, getWidth() * 96 / 100, getHeight() * 18 / 100);

        colors.forEach(colorBtn -> colorBtn.paint(graphics));
        colorChooseBtn.update();
        colorChooseBtn.paint(graphics);
    }
}