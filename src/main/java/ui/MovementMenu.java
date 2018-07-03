package ui;

import logic.Graph;
import logic.Line;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MovementMenu extends JPanel implements Observer {
    private JPanel sliderPane;
    private JSlider slider;
    private JButton backward;
    private JButton forward;
    private JButton start;
    private JButton end;

    private MyCanvas myCanvas;
    private Graph graph;
    private List<Integer> linePoints;

    private int lastValue;
    private Timer forwardTimer;
    private Timer backwardTimer;

    private boolean dirtyValue;

    public MovementMenu(MyCanvas myCanvas) {
        sliderPane = new JPanel(new BorderLayout());
        slider = new JSlider(0, 1, 1);
        ImageIcon backImage = new ImageIcon("src/main/java/icon/backBtn.png");
        backward = new JButton();
        backward.setIcon(backImage);

        ImageIcon forwardImage = new ImageIcon("src/main/java/icon/playBtn.png");
        forward = new JButton();
        forward.setIcon(forwardImage);

        ImageIcon startImage = new ImageIcon("src/main/java/icon/startBtn.png");
        start = new JButton();
        start.setIcon(startImage);

        ImageIcon endImage = new ImageIcon("src/main/java/icon/endBtn.png");
        end = new JButton();
        end.setIcon(endImage);

        this.myCanvas = myCanvas;
        graph = myCanvas.getGraph();
        lastValue = 0;
        linePoints = new ArrayList<>();

        forwardTimer = new Timer(50, e -> {
            graph.forward();
            if (slider.getValue() == slider.getMaximum()) {
                forwardTimer.stop();
            }
        });

        backwardTimer = new Timer(50, e -> {
            graph.backward();
            if (slider.getValue() == slider.getMinimum()) {
                backwardTimer.stop();
            }
        });

        dirtyValue = false;

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(sliderPane);
        this.add(start);
        this.add(backward);
        this.add(forward);
        this.add(end);


        sliderPane.add(slider, BorderLayout.CENTER);

        slider.addChangeListener(e -> {
            if (myCanvas.isPressed() || dirtyValue) return;
            if (slider.getValue() < lastValue) {
                for (int i = 0; i < calPoint(lastValue) - calPoint(slider.getValue()); i++) {
                    graph.backward();
                }
            } else if (slider.getValue() > lastValue) {
                for (int i = 0; i < calPoint(slider.getValue()) - calPoint(lastValue); i++) {
                    graph.forward();
                }
            }

            lastValue = slider.getValue();
        });

        start.addActionListener(e -> {
            backwardTimer.stop();
            forwardTimer.stop();
            slider.setValue(0);
        });

        end.addActionListener(e -> {
            backwardTimer.stop();
            forwardTimer.stop();
            slider.setValue(slider.getMaximum());
        });

        forward.addActionListener(e -> {
            backwardTimer.stop();
            forwardTimer.start();
        });

        backward.addActionListener(e -> {
            forwardTimer.stop();
            backwardTimer.start();
        });

    }

    private int calPoint(int val) {
        int totalPoint = 0;
        int i = 0;
        while (val > 1000) {
            totalPoint += linePoints.get(i);
            i++;
            val = val - 1000;
        }
        totalPoint = totalPoint + (val * linePoints.get(i) / 1000);
        return totalPoint;

    }


    @Override
    protected void paintComponent(Graphics g) {
        slider.setMaximum((graph.getExist().size() + graph.getDelete().size()) * 1000);

        dirtyValue = true;
        if (!graph.getExist().empty()) {
            Line topline = graph.getExist().peek();
            slider.setValue((graph.getExist().size() - 1) * 1000 + (topline.getExist().size() * 1000 / (topline.getExist().size() + topline.getDelete().size())));
        }
        dirtyValue = false;

        sliderPane.setMaximumSize(new Dimension(getWidth() - 4 * getHeight(), getHeight()));
        start.setMaximumSize((new Dimension(getHeight(), getHeight())));
        backward.setMaximumSize(new Dimension(getHeight(), getHeight()));
        forward.setMaximumSize(new Dimension(getHeight(), getHeight()));
        end.setMaximumSize(new Dimension(getHeight(), getHeight()));


        updateUI();
    }

    @Override
    public void update(Observable o, Object arg) {
        String s = (String) arg;
        if (s.equals("AL") || s.equals("AP")) {
            linePoints.clear();
            for (Line line : graph.getExist()) {
                linePoints.add(line.getExist().size() + line.getDelete().size());
            }
            for (Line line : graph.getDelete()) {
                linePoints.add(line.getExist().size() + line.getDelete().size());
            }
            Line topline = graph.getExist().peek();
            slider.setValue((graph.getExist().size() - 1) * 1000 + (topline.getExist().size() * 1000 / (topline.getExist().size() + topline.getDelete().size())));
            lastValue = slider.getValue();
        }
    }
}
