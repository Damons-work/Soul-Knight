import javax.swing.*;

public class Element extends JLabel {
    ImageIcon imageIcon;
    int size=50;
//左上角跟右下角坐标,用于判断是否阻止移动
    int beginX;
    int beginY;
    int endX;
    int endY;



    public Element(String path,int x,int y,int width,int height){
        //记录左上角跟右下角坐标
        beginX=x;
        beginY=y;
        endX=x+width;
        endY=y+height;

        imageIcon=new ImageIcon(path);
        imageIcon=PhotoTools.change(imageIcon,width,height);
        this.setIcon(imageIcon);

        setVisible(true);
        this.setBounds(x,y,width,height);
    }
    //判断能否通过,能通过返回true,x1y1为左上角坐标,
//    public boolean check(int x1,int y1,int x2,int y2){
//        if(x1>beginX && x1<endX && y1>beginY && y1<endY){//玩家左上角不在范围内
//            return false;
//        }
//        else if(x2>beginX && x2<endX && y2>beginY && y2<endY){//玩家右下角
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

}
