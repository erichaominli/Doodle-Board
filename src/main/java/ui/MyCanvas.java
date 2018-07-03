package ui;

import logic.Graph;
import logic.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyCanvas extends JPanel {

    private Graph graph;

    private GraphView graphView;

    private boolean pressed;

    public MyCanvas() {
        graph = new Graph(0, 5);
        graphView = new GraphView(graph);
        pressed = false;
        requestFocusInWindow();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                graph.addLine(new Point(e.getX(), e.getY()));
                pressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                graph.addPoint(new Point(e.getX(),e.getY()));
            }
        });
    }

    public Graph getGraph() {
        return graph;
    }

    public boolean isPressed() {
        return pressed;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        graphView.update();
        graphView.paint(g);
    }
}
