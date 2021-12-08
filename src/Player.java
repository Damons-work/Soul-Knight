import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends JLabel implements MoveDistance,Size{
    int Blood = 7;
    int shield=4;
    int Max_energy=200;
    int energy=200;

    Weapon[] weapons=new Weapon[2];
    int inHand;//当前手持武器,0和1切换

    ImageIcon imageIcon = new ImageIcon("picture/Role/knight_3.png");
    ImageIcon imageIcon2=new ImageIcon("picture/Role/knight_left.png");


    //不能是五的整数倍,因为判断碰撞时可能出现刚好重叠的情况
    int x = 151;
    int y = 301;
    int width=Size;
    int height=(int)1.5*Size;
    //玩家左上角坐标为(x,y),右下角坐标为(x+width,y+height)


    public Player() {
        imageIcon=PhotoTools.change(imageIcon,width,height);
        imageIcon2=PhotoTools.change(imageIcon2,width,height);
        this.setIcon(imageIcon);

        this.setBounds(x, y, width, height);
        this.setVisible(true);
    }
    void moveRight(){
        x+=disX;
        this.setIcon(imageIcon);
        this.setBounds(x,y,width,height);
    }
    void moveLeft(){
        x-=disX;
        this.setIcon(imageIcon2);
        this.setBounds(x,y,width,height);
    }
    void moveUp(){
        y-=disY;
        this.setBounds(x,y,width,height);
    }
    void moveDown(){
        y+=disY;
        this.setBounds(x,y,width,height);
    }

    //武器拾取
    void addWeapon(Weapon weapon){
        if(weapons[0] ==null){
            weapons[0]=weapon;
        }
        else if(weapons[1]==null){
            weapons[1]=weapon;
        }
        else{
            Weapon swap=weapon;
            weapon=weapons[inHand];
            weapons[inHand]=swap;
        }
    }

}

