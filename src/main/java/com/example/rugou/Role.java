package com.example.rugou;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Timer;

public class Role extends ImageView {
    public int level;
    public int maxHP;
    public int curHP;
    public int damage;
    public int defend;
    protected Image mImage;
    protected double attackRange;
    protected double speed;
    protected int WIDTH;
    protected int HEIGHT;
    protected Timer mTimer;

    public Role() {
        super();
    }

    public void createTimer() {
    }

}
