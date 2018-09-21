package main.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * GUI Elements creator
 */

public class Elements {

    public Canvas createCanvas(double width, double height){
        return new Canvas(width, height);
    }

    public Label createLabel(String text){
        Label label = new Label();
        label.setText(text);
        return label;
    }

    public Button createButton(String btnName){
        Button btn = new Button();
        btn.setText(btnName);
        return btn;
    }
}
