package com.example.rugou;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class Character extends Role {

    private static final List<KeyCode> mKeyList = new Vector<>();
    public EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            KeyCode kc = event.getCode();
            if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                mKeyList.remove(kc);
                mKeyList.add(kc);
            } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                mKeyList.remove(kc);
            }
        }
    };
    public EventHandler<MouseEvent> mouseHandler =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
//            System.out.println("sx="+getX()+" sy="+getY()+" dx="+event.getX()+" dy="+event.getY());
                        Bullet b = new Bullet(damage, true, getX(), getY(), event.getX(), event.getY() - 45);
                        GameApplication.fireBullet(b);
                    }
                }
            };
    private int exp = 0;

    public Character() {
        super();

        String character = getClass().getResource("characters/c03_4.png").toExternalForm();
        mImage = new Image(character);
        setImage(mImage);
        level = 1;
        maxHP = level * 100;
        curHP = maxHP;
        damage = level * 10;
        defend = level * 10;
        attackRange = -1;
        speed = 6 + level * 0.5;
        WIDTH = (int) mImage.getWidth();
        HEIGHT = (int) mImage.getHeight();
        createTimer();
    }

    @Override
    public void createTimer() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (mKeyList.isEmpty()) {
                    return;
                }
                KeyCode c = mKeyList.get(0);
                double nx = -1;
                double ny = -1;
                switch (c) {
                    case UP:
                    case W:
                        ny = getY() - speed;
                        break;
                    case DOWN:
                    case S:
                        ny = getY() + speed;
                        break;
                    case LEFT:
                    case A:
                        nx = getX() - speed;
                        break;
                    case RIGHT:
                    case D:
                        nx = getX() + speed;
                        break;
                }
                move(nx, ny);
            }
        };
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(timerTask, 0, 17);
    }

    private void move(double nx, double ny) {
        Platform.runLater(() -> {
            if (nx > 0 && nx < GameApplication.G_WIDTH - GameApplication.getCharacter().WIDTH) setX(nx);
            if (ny > 0 && ny < GameApplication.G_HEIGHT - GameApplication.getCharacter().HEIGHT) setY(ny);
        });
    }

    public void sufferDamage(int d) {
        if (curHP < 0) return;
        defend -= d;
        curHP += defend < 0 ? defend : 0;
        if (defend < 0) defend = 0;
        GameApplication.getStatusPane().updateStatus();
        if (curHP < 0) {
            Platform.runLater(() -> {
                GameApplication.gameOver();
            });
        }


    }

    public void addExp(int amout) {
        exp += amout;
        if (exp > level * level * 10) {
            exp -= level * level * 10;
            level += 1;
            maxHP = level * 100;
            curHP = maxHP;
            damage = level * 10;
            defend = level * 10;
            attackRange = -1;
            speed = 6 + level * 0.5;
            GameApplication.getStatusPane().updateStatus();
            GameApplication.updateEnemyMax();
        }
    }
}
