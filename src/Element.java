import javax.swing.*;

public class Element extends JLabel {
    ImageIcon imageIcon;
    int size=30;



    public Element(String path,int x,int y,int width,int height){
        imageIcon=new ImageIcon(path);
        imageIcon=PhotoTools.change(imageIcon,width,height);
        this.setIcon(imageIcon);

        setVisible(true);
        this.setBounds(x,y,width,height);
    }

}
