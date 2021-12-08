import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Role extends JLabel implements MouseListener,Size {
    ImageIcon icon;

    //左上角跟右下角坐标,用于判断是否阻止移动
    int beginX;
    int beginY;
    int endX;
    int endY;


    Role(String path,Floor floor){
        //记录左上角跟右下角坐标
        beginX= floor.getBoundsX();
        beginY=floor.getBoundsY();
        endX=floor.getBoundsX()+Size;
        endY=floor.getBoundsY()+(int)1.5*Size;

        icon=new ImageIcon(path);
        icon=PhotoTools.change(icon,Size,(int)1.5*Size);
        this.setIcon(icon);
        this.setBounds(floor.getBoundsX(),floor.getBoundsY(),Size,(int)1.5*Size);
        this.setVisible(true);
    }
    //判断能否通过,能通过返回true,x1y1为左上角坐标,
//    public boolean check(int x1,int y1,int x2,int y2){
//        if(x1>beginX && x1<endX && y1>beginY && y1<endY){
//            return false;
//        }
//        else if(x2>beginX && x2<endX && y2>beginY && y2<endY){
//            return false;
//        }
//        else {
//            return true;
//        }
//    }
    public boolean check(int x,int y,int width,int height){
        if(x>beginX && x<endX && y>beginY && y<endY){//玩家左上角不在范围内
            return false;
        }
        else if((x+width)>beginX && (x+width)<endX && (y+height)>beginY && (y+height)<endY){//玩家右下角
            return false;
        }
        else if(x>beginX && x<endX && (y+height)>beginY && (y+height)<endY){//左下角
            return false;
        }
        else if((x+width)>beginX && (x+width)<endX && y>beginY && y<endY){//右上角
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
