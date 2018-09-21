package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.classes.Game;
import main.graphics.RootContext;

/**
 * App entry point
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Sapper");
        primaryStage.setScene(new Scene(RootContext.getInstance(), 500, 500));
        new Game().startGame(20,20,15);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
