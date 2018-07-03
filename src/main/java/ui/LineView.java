package ui;

import logic.Line;

import java.awt.*;

public class LineView extends DrawableContent {

    private Line line;

    public LineView(Line line) {
        super();
        this.line = line;
    }


    @Override
    public void update() {
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(new Color(line.getRgb()));
        ((Graphics2D) graphics).setStroke(new BasicStroke(line.getThickness()));
        int size = line.getExist().size();
        if (size == 0) {
            return;
        }
        int[] xs = new int[size];
        int[] ys = new int[size];
        for (int i = 0; i < size; i++) {
            xs[i] = line.getExist().get(i).getX();
            ys[i] = line.getExist().get(i).getY();
        }
        graphics.drawPolyline(xs, ys, size);
    }

}
