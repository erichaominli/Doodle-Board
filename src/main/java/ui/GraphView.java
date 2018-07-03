package ui;

import logic.Graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphView extends DrawableContent {

    private List<LineView> lineViewList;

    private Graph graph;

    public GraphView(Graph graph) {
        lineViewList = new ArrayList<>();
        this.graph = graph;
    }

    @Override
    public void update() {
        lineViewList.clear();
        graph.getExist().forEach(line -> lineViewList.add(new LineView(line)));
    }

    @Override
    public void paint(Graphics graphics) {
        lineViewList.forEach(lineView -> lineView.paint(graphics));
    }

}
