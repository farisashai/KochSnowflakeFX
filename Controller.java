package FX.Graphics.KochSnowflake;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private AnchorPane pane;

    private String bgColor = "rgb(0,40,60)";
    private List<Line> lineList = new LinkedList<>();
    private List<Line> l2 = new LinkedList<>();
    private List<Coordinate> corners = new LinkedList<>();
    private int index = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setStyle("-fx-background-color: " + bgColor);
        pane.setOnMouseClicked(this::handle);
    }

    private void handle(MouseEvent mouseEvent) {
        if (index < 2) {
            corners.add(new Coordinate((int)mouseEvent.getX(),(int)mouseEvent.getY()));
            index++;
            Circle c = new Circle((int)mouseEvent.getX(),(int)mouseEvent.getY(),3);
            c.setFill(Color.WHITE);
            pane.getChildren().add(c);
        } else if (index == 2) {
            corners.add(new Coordinate((int)mouseEvent.getX(),(int)mouseEvent.getY()));
            Coordinate one = corners.get(0);
            Coordinate two = corners.get(1);
            Coordinate three = corners.get(2);
            lineList.add(line(one.x,one.y,two.x,two.y));
            lineList.add(line(two.x,two.y,three.x,three.y));
            lineList.add(line(three.x,three.y,one.x,one.y));
            pane.getChildren().addAll(lineList);
            index++;
        } else if (index < 10) {
            List<Line> newList = new LinkedList<>();
            for (Line l : lineList) {
                double startX = l.getStartX();
                double startY = l.getStartY();
                double endX = l.getEndX();
                double endY = l.getEndY();

                double length = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));


                double theta = -Math.PI / 3;
                double x = (endX - startX) / 3;
                double y = (endY - startY) / 3;
                double xP = x * Math.cos(theta) - y * Math.sin(theta);
                double yP = y * Math.cos(theta) + x * Math.sin(theta);


                double ax = startX;
                double ay = startY;
                double bx = ax + (endX - startX) / 3;
                double by = ay + (endY - startY) / 3;
                double cx = bx + xP;
                double cy = by + yP;
                double dx = endX - ((endX - startX) / 3);
                double dy = endY - ((endY - startY) / 3);
                double ex = endX;
                double ey = endY;

                Line one = line(ax, ay, bx, by);
                newList.add(one);
                Line two = line(bx, by, cx, cy);
                newList.add(two);
                Line three = line(cx, cy, dx, dy);
                newList.add(three);
                Line four = line(dx, dy, ex, ey);
                newList.add(four);
            }
            lineList = newList;
            pane.getChildren().clear();
            pane.getChildren().addAll(lineList);
            index++;
        } /*else if (index == 10) {
            corners.clear();
            corners.add(new Coordinate((int)mouseEvent.getX(),(int)mouseEvent.getY()));
            index++;
            Circle c = new Circle((int)mouseEvent.getX(),(int)mouseEvent.getY(),3);
            c.setFill(Color.WHITE);
            pane.getChildren().add(c);
        } else if (index == 11) {
            corners.add(new Coordinate((int)mouseEvent.getX(),(int)mouseEvent.getY()));
            index++;
            Circle c = new Circle((int)mouseEvent.getX(),(int)mouseEvent.getY(),3);
            c.setFill(Color.WHITE);
            pane.getChildren().add(c);
        } else if (index == 12) {
            corners.add(new Coordinate((int)mouseEvent.getX(),(int)mouseEvent.getY()));
            Coordinate one = corners.get(0);
            Coordinate two = corners.get(1);
            Coordinate three = corners.get(2);
            l2.add(line(one.x,one.y,two.x,two.y));
            l2.add(line(two.x,two.y,three.x,three.y));
            l2.add(line(three.x,three.y,one.x,one.y));
            pane.getChildren().addAll(l2);
            index++;
        } else if (index < 20) {

            List<Line> newList = new LinkedList<>();
            for (Line l : l2) {
                double startX = l.getStartX();
                double startY = l.getStartY();
                double endX = l.getEndX();
                double endY = l.getEndY();


                double theta = -Math.PI / 3;
                double x = (endX - startX) / 3;
                double y = (endY - startY) / 3;
                double xP = x * Math.cos(theta) - y * Math.sin(theta);
                double yP = y * Math.cos(theta) + x * Math.sin(theta);


                double ax = startX;
                double ay = startY;
                double bx = ax + x;
                double by = ay + y;
                double cx = bx + xP;
                double cy = by + yP;
                double dx = endX - x;
                double dy = endY - y;
                double ex = endX;
                double ey = endY;

                Line one = line(ax, ay, bx, by);
                newList.add(one);
                Line two = line(bx, by, cx, cy);
                newList.add(two);
                Line three = line(cx, cy, dx, dy);
                newList.add(three);
                Line four = line(dx, dy, ex, ey);
                newList.add(four);
            }
            l2 = newList;
            pane.getChildren().clear();
            pane.getChildren().addAll(lineList);
            pane.getChildren().addAll(l2);
            index++;
        }*/
    }
    private Line line (double x1, double y1, double x2, double y2) {
        Line l = new Line(x1,y1,x2,y2);
        l.setStroke(Color.WHITE);
        return l;
    }
    private class Coordinate {
        private Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
        private int x, y;
    }
}
