package com.example.rugou;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.*;

public class Enemy extends Role {

    private static final Random mRand = new Random(System.currentTimeMillis());
    private static final short[] dirX = {1, 0, -1, 0};
    private static final short[] dirY = {0, 1, 0, -1};
    private final double attractRange;
    private final Queue<Point2D> mSteps = new LinkedList<>();
    private int attackInterval = 1000;
    private long lastAttackTime;

    public Enemy() {
        super();
        int clevel = GameApplication.getCharacter().level;
        level = clevel + mRand.nextInt(5);
        maxHP = level * 50;
        curHP = maxHP;
        damage = 50 + level + mRand.nextInt(level);
        defend = 0 + level + mRand.nextInt(level);
        attackRange = GameApplication.getCharacter().WIDTH;
        attractRange = GameApplication.G_HEIGHT;
        attackInterval = 1500 > 10 * level ? 1500 - 10 * level : 250;
        speed = 1 + 1 * level;

        int tid = mRand.nextInt(16);
        String img = getClass().getResource("enemy/boss_" + tid + ".png").toExternalForm();
        mImage = new Image(img);
        setImage(mImage);
        WIDTH = (int) mImage.getWidth();
        HEIGHT = (int) mImage.getHeight();
        setX(mRand.nextInt(GameApplication.G_WIDTH - WIDTH));
        setY(mRand.nextInt(GameApplication.G_HEIGHT - HEIGHT));
        //        System.out.println("w="+WIDTH+" h="+HEIGHT+" x="+getX()+" y="+getY());
        createTimer();
    }

    public boolean sufferDamage(int damage) {
        curHP -= damage > defend ? damage - defend : 1;
        if (curHP < 0) {
            GameApplication.getCharacter().addExp(level * 10);
            return true;
        }
        return false;
    }

    @Override
    public void createTimer() {
        TimerTask timerTask =
                new TimerTask() {
                    @Override
                    public void run() {
                        Character c = GameApplication.getCharacter();
                        if (Bullet.calculateLength(getX(), getY(), c.getX(), c.getY()) <= attractRange
                                && Bullet.calculateLength(getX(), getY(), c.getX(), c.getY()) > attackRange) {
                            findWay(getX(), getY(), c.getX(), c.getY());
                        }
                        if (!mSteps.isEmpty()) {
                            Point2D t = mSteps.poll();
                            Platform.runLater(
                                    () -> {
                                        setX(t.getX());
                                        setY(t.getY());
                                    });
                        }
                        if (Bullet.calculateLength(getX(), getY(), c.getX(), c.getY()) <= attackRange
                                && System.currentTimeMillis() - lastAttackTime > attackInterval) {
                            lastAttackTime = System.currentTimeMillis();
                            GameApplication.getCharacter().sufferDamage(damage);
                        }
                    }
                };
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(timerTask, 0, 17);
    }

    private void findWay(double sx, double sy, double dx, double dy) {
        mSteps.clear();
        int dir = -1;
        if (Math.abs(sx - dx) > Math.abs(sy - dy)) {
            dir = dx - sx > 0 ? 0 : 2;
        } else {
            dir = dy - sy > 0 ? 1 : 3;
        }
        double nx = getX() + dirX[dir] * speed;
        double ny = getY() + dirY[dir] * speed;

        mSteps.add(new Point2D(nx, ny));
        // if (check(nx, ny, dx, dy)) {
        //     mSteps.add(new Point2D(nx, ny));
        //     if (Bullet.calculateLength(nx, ny, dx, dy) <= attackRange) {
        //         return;
        //     }
        // } else {
        //     dir = (dir + 1) % 4;
        //     nx = getX() + dirX[dir] * speed;
        //     ny = getY() + dirY[dir] * speed;
        //     if (check(nx, ny, dx, dy)) {
        //         mSteps.add(new Point2D(nx, ny));
        //         findWay(nx, ny, dx, dy);
        //     }
        //     dir = (dir + 2) % 4;
        //     nx = getX() + dirX[dir] * speed;
        //     ny = getY() + dirY[dir] * speed;
        //     if (check(nx, ny, dx, dy)) {
        //         mSteps.add(new Point2D(nx, ny));
        //         findWay(nx, ny, dx, dy);
        //     }
        // }
    }

    private boolean check(double nx, double ny, double dx, double dy) {
        if (nx < 0 || nx > GameApplication.G_WIDTH || ny < 0 || ny > GameApplication.G_HEIGHT) {
            return false;
        }
        return !(Bullet.calculateLength(nx, ny, dx, dy) > attractRange);
    }
}
