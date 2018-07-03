package logic;

import java.util.Stack;

public class Line {
    private Stack<Point> exist;
    private Stack<Point> delete;
    private int rgb;
    private int thickness;

    public Line(int rgb, int thickness) {
        this.exist = new Stack<>();
        this.delete = new Stack<>();
        this.rgb = rgb;
        this.thickness = thickness;
    }

    public Stack<Point> getExist() {

        return exist;
    }

    public Stack<Point> getDelete() {
        return delete;
    }

    public int getRgb() {
        return rgb;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public void addPoint(Point p) {
        exist.push(p);
    }

    public boolean backward() {
        if (exist.empty()) return false;
        delete.push(exist.pop());
        return true;
    }

    public boolean forward() {
        if (delete.empty()) return false;
        exist.push(delete.pop());
        return true;
    }
}
