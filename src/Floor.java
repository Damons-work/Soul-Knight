import javax.swing.*;

public class Floor extends JLabel implements Size{
    ImageIcon floor = new ImageIcon("picture/红砖.jpg");
    int x;
    int y;
    int BoundsX;
    int BoundsY;


    public Floor(int x, int y) {
        this.x = x;
        this.y = y;
//        this.setSize(Size,Size);
        floor=PhotoTools.change(floor,Size,Size);
        BoundsX = (x + 1) * Size;
        BoundsY = (int) (y + 1.5) * Size;
        this.setIcon(floor);
        this.setVisible(true);
        this.setBounds(BoundsX, BoundsY, Size, Size);
    }
    public Floor(int beginX,int beginY,int x,int y){
        this.x=x;
        this.y=y;
        floor=PhotoTools.change(floor,Size,Size);
        BoundsX = (x + 1) * Size+beginX;
        BoundsY = (int) (y + 1.5) * Size+beginY;
        this.setIcon(floor);
        this.setVisible(true);
        this.setBounds(BoundsX, BoundsY, Size, Size);
    }

    public int getBoundsX() {
        return BoundsX;
    }

    public int getBoundsY() {
        return BoundsY;
    }
}
