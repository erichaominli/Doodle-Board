package logic;

import java.io.Serializable;
import java.util.*;

public class Graph extends Observable implements Serializable {
    private Stack<Line> exist;
    private Stack<Line> delete;
    private int rgb;
    private int thickness;

    public Graph(int rgb, int thickness) {
        exist = new Stack<>();
        delete = new Stack<>();
        this.rgb = rgb;
        this.thickness = thickness;
    }

    public Stack<Line> getExist() {

        return exist;
    }

    public Stack<Line> getDelete() {
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

    public void addLine(Point p) {
        delete.clear();
        if(!exist.empty()){
            exist.peek().getDelete().clear();
        }
        Line newline = new Line(rgb,thickness);
        newline.addPoint(p);
        exist.push(newline);
        setChanged();
        notifyObservers("AL");
    }

    public void addPoint(Point p) {
        exist.peek().addPoint(p);
        setChanged();
        notifyObservers("AP");
    }

    public void backward() {
        if(exist.empty()) return;
        if(!exist.peek().backward()){
            delete.push(exist.pop());
        }
    }
    public void forward() {
        if(exist.empty()){
           if(!delete.empty()) {
               exist.push(delete.pop());
               exist.peek().forward();
           }
        }
        else{
            if(!exist.peek().forward()){
                if(!delete.empty()){
                    exist.push(delete.pop());
                    exist.peek().forward();
                }
            }
        }
    }

//    public List<String> saveRule(){
//        List<String> result = new ArrayList<String>();
//        for(int i = 0; i < exist.size(); i++){
//            String a = "";
//            a = a + exist.get(i).getRgb() + " " + exist.get(i).getThickness();
//            for (int j = 0; j < exist.get(i).getExist().size(); j++){
//                Line l = exist.get(i);
//                a = a + l.getExist().get(i).getX() + "," + l.getExist().get(i).getY() + " ";
//            }
//            for (int j = 0; j < exist.get(i).getDelete().size(); j++){
//                Line l = exist.get(i);
//                a = a + l.getDelete().get(i).getX() + "," + l.getDelete().get(i).getY() + " ";
//            }
//            result.add(a);
//        }
//        for(int i = 0; i < delete.size(); i++){
//            String a = "";
//            a = a + delete.get(i).getRgb() + " " + delete.get(i).getThickness();
//            for (int j = 0; j < delete.get(i).getExist().size(); j++){
//                Line l = delete.get(i);
//                a = a + l.getExist().get(i).getX() + "," + l.getExist().get(i).getY() + " ";
//            }
//            for (int j = 0; j < delete.get(i).getDelete().size(); j++){
//                Line l = delete.get(i);
//                a = a + l.getDelete().get(i).getX() + "," + l.getDelete().get(i).getY() + " ";
//            }
//            result.add(a);
//        }
//        return result;
//    }
//
//    public void loadRule(List<String> input){
//        exist.clear();
//        delete.clear();
//        for (int i = 0; i < input.size(); i++){
//            //store rgb
//
//            String line = input.get(i);
//            Line newline = new Line();
//
//            for (int j = 0; j < line.length(); j++){
//
//            }
//
//        }
//    }


}
