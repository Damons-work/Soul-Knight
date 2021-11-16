import javax.swing.*;

public class Wall extends JLabel {
    int x;
    int y;
    ImageIcon imageIcon;
    int width=30;
    int height;
    int flag;//0为墙砖0,没有下部分,1为墙砖1,包含下部分

    public Wall(int x,int y,int flag){
        this.x=x;
        this.y=y;
        this.flag=flag;

        switch (flag){
            case 0:
                height=width;
                imageIcon=new ImageIcon("picture/墙砖0.png");
                break;
            case 1:
                height=(int)1.5*width;
                imageIcon=new ImageIcon("picture/墙砖1.png");
                break;
            default:
                System.out.println("flag数值出现错误");
        }
        imageIcon=PhotoTools.change(imageIcon,width,height);
        this.setIcon(imageIcon);
        this.setVisible(true);
        this.setBounds(x*width,y*width,width,height);
    }


}
