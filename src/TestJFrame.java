import javax.swing.*;
import java.awt.event.*;

public class TestJFrame extends JFrame{
    Floor f=new Floor(0,0);

    Home home=new Home();
    boolean haveLogin=false;

    JButton login=new JButton();
    ImageIcon imageIcon=new ImageIcon("picture/Login BackGround.jpg");

    Map map=new Map(1);


    public TestJFrame(){


        this.setTitle("test");

        imageIcon=PhotoTools.change(imageIcon,900,600);
        login.setIcon(imageIcon);
        login.setBounds(0,0,900,600);
        this.add(login);



        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.setVisible(false);
                TestJFrame.super.remove(login);
                TestJFrame.super.add(home);
            }
       });
//        this.add(map);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(f.Size*homePane.FloorCountX+20,f.Size*homePane.FloorCountY+50);
        this.setSize(900,600);
        this.setVisible(true);


    }



}


