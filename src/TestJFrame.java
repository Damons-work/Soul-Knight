import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TestJFrame extends JFrame implements MouseListener{
    Floor f=new Floor(0,0);
    Room r=new Room(30,20);
    Home home=new Home();
    boolean haveLogin=false;

    Login l=new Login();
    JButton login=new JButton();
    ImageIcon imageIcon=new ImageIcon("picture/Login BackGround.jpg");

    public TestJFrame(){



        this.setTitle("test");
 //       this.add(l);
        imageIcon=PhotoTools.change(imageIcon,1200,750);
        login.setIcon(imageIcon);
        login.setBounds(0,0,1200,750);
        this.add(login);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.setVisible(false);
                TestJFrame.super.remove(login);
                TestJFrame.super.add(home);


            }
       });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(f.Size*homePane.FloorCountX+20,f.Size*homePane.FloorCountY+50);
        this.setSize(1200,800);
        this.setVisible(true);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(haveLogin==false){
            haveLogin=true;
            this.remove(l);
            this.add(home);
        }
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
