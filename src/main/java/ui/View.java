package ui;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private JPanel content;

    private MyCanvas mc;

    private LeftPanel lp;

    private MovementMenu mm;

    private Timer timer;

    /**
     * Create a new View.
     */
    public View() {
        content = new JPanel(new BorderLayout());
        mc = new MyCanvas();
        lp = new LeftPanel();
        mm = new MovementMenu(mc);
        timer = new Timer(1000 / 60, e -> {
            handleResize();
            mc.getGraph().setRgb(lp.getCurrentColor().getRGB());
            mc.getGraph().setThickness(lp.getSliderPane().getValue());
            mc.repaint();
            lp.repaint();
            mm.repaint();
        });
        // Set up the window.
        this.setTitle("Drawing");
        this.setMinimumSize(new Dimension(400, 300));
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set up the menu bar
        this.setJMenuBar(new MyMenu(mc.getGraph(), this));

        this.setContentPane(content);

        content.add(lp, BorderLayout.WEST);
        content.add(mc, BorderLayout.CENTER);
        content.add(mm, BorderLayout.SOUTH);

        mc.getGraph().addObserver(mm);

        timer.start();

        this.setVisible(true);

        // Hook up this observer so that it will be notified when the model
        // changes.

    }

    private void handleResize() {
        mc.setPreferredSize(new Dimension(getWidth() * 8 / 10, getHeight() * 9 / 10));
        lp.setPreferredSize(new Dimension(getWidth() * 2 / 10, getHeight() * 9 / 10));
        mm.setPreferredSize(new Dimension(getWidth(), getHeight() * 1 / 10));
    }

}


