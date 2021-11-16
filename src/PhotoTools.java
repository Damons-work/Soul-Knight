import javax.swing.*;
import java.awt.*;

public class PhotoTools {
    public static ImageIcon change(ImageIcon image, int width, int height){
        Image img=image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);//第三个值可以去查api是图片转化的方式
        ImageIcon image2=new ImageIcon(img);
        return  image2;
    }
}
