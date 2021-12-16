package com.example.rugou;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StatusPane extends VBox {

    private static Label hplabel;
    private static Label damageLable;
    private static Label defendLabel;
    private static Label levelLabel;

    private static HBox hpPane;
    private static HBox defendPane;
    private static HBox labelPane;

    public StatusPane() {
        super();

        String bg = getClass().getResource("UI/ui_16.png").toExternalForm();
        setStyle("-fx-background-image: url('" + bg + "');" +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: repeat;" +
                "-fx-padding: 2px;");

        hpPane = new HBox();
        getChildren().add(hpPane);

        defendPane = new HBox();
        getChildren().add(defendPane);

        labelPane = new HBox();
        getChildren().add(labelPane);

        damageLable = new Label(" Damage: 0");
        damageLable.setFont(new Font("Arial", 10));
        damageLable.setTextFill(Color.WHITE);
        labelPane.getChildren().add(damageLable);

        levelLabel = new Label(" Level: 0");
        levelLabel.setFont(new Font("Arial", 10));
        levelLabel.setTextFill(Color.WHITE);
        labelPane.getChildren().add(levelLabel);
    }

    public void updateStatus() {
        Character c = GameApplication.getCharacter();
        Platform.runLater(() -> {
            hpPane.getChildren().clear();
            if (c.curHP < 50 && c.curHP > 0) {
                String im_png = getClass().getResource("UI/ui_33.png").toExternalForm();
                Image im = new Image(im_png);
                ImageView iv = new ImageView(im);
                hpPane.getChildren().add(iv);
            } else {
                for (int i = 0; i < c.curHP / 50; i++) {
                    String im_png = getClass().getResource("UI/ui_33.png").toExternalForm();
                    Image im = new Image(im_png);
                    ImageView iv = new ImageView(im);
                    hpPane.getChildren().add(iv);
                }
            }
            defendPane.getChildren().clear();
            if (c.defend < 50 && c.defend > 0) {
                String im_png = getClass().getResource("UI/ui_34.png").toExternalForm();
                Image im = new Image(im_png);
                ImageView iv = new ImageView(im);
                defendPane.getChildren().add(iv);
            } else {
                for (int i = 0; i < c.defend / 50; i++) {
                    String im_png = getClass().getResource("UI/ui_34.png").toExternalForm();
                    Image im = new Image(im_png);
                    ImageView iv = new ImageView(im);
                    defendPane.getChildren().add(iv);
                }
            }

            damageLable.setText(" Damage: " + c.damage);
            levelLabel.setText(" Level: " + c.level);
        });
    }
}
