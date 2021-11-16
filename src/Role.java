import javax.swing.*;

public class Role extends JLabel {
    ImageIcon icon;
    final int Size=30;


    Role(String path,Floor floor){
        icon=new ImageIcon(path);
        this.setIcon(icon);
        this.setBounds(floor.getBoundsX(),floor.getBoundsY(),Size,2*Size);
        this.setVisible(true);
    }
    
}
