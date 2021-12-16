package com.example.rugou;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameApplication extends Application {
    public static final int W_WIDTH = 680;
    public static final int W_HEIGHT = 480;
    public static final int G_WIDTH = 680;
    public static final int G_HEIGHT = 400;

    private static int ENEMY_MAX;

    private static AnchorPane rootPane;
    private static Pane gamePane;
    private static StatusPane statusPane;
    private static Scene scene;
    private static Character mCharacter;
    private static final List<Enemy> mEnemyList = new ArrayList<>();
    private static Timer mTimer;
    private static Stage mStage;

    public GameApplication() {
        super();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Character getCharacter() {
        return mCharacter;
    }

    public static void fireBullet(Bullet bullet) {
        gamePane.getChildren().add(bullet);
    }

    public static void removeBullet(Bullet bullet) {
        gamePane.getChildren().remove(bullet);
    }

    public static void removeEnemy(Enemy enemy) {
        gamePane.getChildren().removeIf(node -> node == enemy);
    }

    public static List<Enemy> getEnemyList() {
        return mEnemyList;
    }

    public static void updateEnemyMax() {
        ENEMY_MAX = 1 + (int) (mCharacter.level * 0.2);
    }

    public static void gameOver() {
        scene.removeEventHandler(KeyEvent.KEY_RELEASED, mCharacter.keyHandler);
        scene.removeEventHandler(KeyEvent.KEY_PRESSED, mCharacter.keyHandler);
        scene.removeEventHandler(MouseEvent.MOUSE_CLICKED, mCharacter.mouseHandler);
        Label go = new Label("Game over !");
        go.setFont(new Font("Arial", 100));
        go.setTextFill(Color.web("#fff"));
        gamePane.getChildren().add(go);
        mStage.setScene(scene);
    }

    public static StatusPane getStatusPane() {
        return statusPane;
    }

    @Override
    public void start(Stage stage) throws IOException {
        mStage = stage;
        rootPane = FXMLLoader.load(getClass().getResource("UI.fxml"));
        gamePane = (Pane) rootPane.getChildren().get(1);
        statusPane = (StatusPane) rootPane.getChildren().get(0);

        String bg = getClass().getResource("ui_3.png").toExternalForm();
        gamePane.setStyle("-fx-background-image: url('" + bg + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: repeat;");

        mCharacter = new Character();
        gamePane.getChildren().add(mCharacter);
        statusPane.updateStatus();
        updateEnemyMax();

        scene = new Scene(rootPane, W_WIDTH, W_HEIGHT);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, mCharacter.keyHandler);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, mCharacter.keyHandler);
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, mCharacter.mouseHandler);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Rogue");
        stage.show();

        createTimer();
    }

    private void createTimer() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (mEnemyList.size() < ENEMY_MAX) {
                    Enemy enemy = new Enemy();
                    mEnemyList.add(enemy);
                    Platform.runLater(() -> {
                        gamePane.getChildren().add(enemy);
                    });
                }
            }
        };
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(timerTask, 0, (2000 - mCharacter.level * 10) > 0 ? (2000 - mCharacter.level * 100) : 500);
    }
}
