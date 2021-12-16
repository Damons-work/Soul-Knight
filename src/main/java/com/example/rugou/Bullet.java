package com.example.rugou;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class Bullet extends ImageView {
    private final int mDamage;
    private final double mSpeed = 15;
    private final double mSpeedx;
    private final double mSpeedy;
    private final double mSpinSpeed = 15;
    private final Image mImage;
    private boolean isBelong = false;
    private double mSpinRotate = 0;
    private Timer mTimer;

    public Bullet(int damage, boolean belong, double sx, double sy, double dx, double dy) {
        mDamage = damage;
        isBelong = belong;

        String img = getClass().getResource("icons/W_Throw004.png").toExternalForm();
        mImage = new Image(img);
        setImage(mImage);

        double tmp = calculateLength(sx, sy, dx, dy);
        mSpeedx = mSpeed * (dx - sx) / tmp;
        mSpeedy = mSpeed * (dy - sy) / tmp;
        setX(sx);
        setY(sy);

        createTimer();
    }

    public static double calculateLength(double sx, double sy, double dx, double dy) {
        return Math.sqrt((sx - dx) * (sx - dx) + (sy - dy) * (sy - dy));
    }

    private void createTimer() {
        TimerTask timerTask =
                new TimerTask() {
                    @Override
                    public void run() {
                        double nx = getX() + mSpeedx;
                        double ny = getY() + mSpeedy;
                        move(nx, ny);
                    }
                };
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(timerTask, 0, 17);
    }

    private void move(double nx, double ny) {
        Platform.runLater(
                () -> {
                    setX(nx);
                    setY(ny);
                    mSpinRotate = (mSpinRotate + mSpinSpeed) % 360;
                    setRotate(mSpinRotate);
                    if (isBelong) {
                        Iterator<Enemy> iter = GameApplication.getEnemyList().iterator();
                        while (iter.hasNext()) {
                            Enemy e = iter.next();
                            double top = e.getY();
                            double down = e.getY() + e.HEIGHT;
                            double left = e.getX();
                            double right = e.getX() + e.WIDTH;
                            if (ny >= top && ny <= down && nx >= left && nx <= right) {
                                if (e.sufferDamage(mDamage)) {
                                    iter.remove();
                                    e.mTimer.cancel();
                                    GameApplication.removeEnemy(e);
                                }
                                GameApplication.removeBullet(this);
                                mTimer.cancel();
                                break;
                            }
                        }
                    }
                    if (nx < 0 || nx > GameApplication.G_WIDTH || ny < 0 || ny > GameApplication.G_HEIGHT) {
                        GameApplication.removeBullet(this);
                        mTimer.cancel();
                    }
                });
    }
}
