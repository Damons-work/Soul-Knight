import javax.swing.*;

public class Role extends JLabel {
    ImageIcon icon;
    final int Size=50;


    Role(String path,Floor floor){
        icon=new ImageIcon(path);
        icon=PhotoTools.change(icon,Size,(int)1.5*Size);
        this.setIcon(icon);
        this.setBounds(floor.getBoundsX(),floor.getBoundsY(),Size,(int)1.5*Size);
        this.setVisible(true);
    }
    
}
