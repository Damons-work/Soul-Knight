import javax.swing.*;


public class Map extends JPanel {
    final int MaxCountX=5;
    final int MaxCountY=5;
    Room[][] rooms=new Room[MaxCountX][MaxCountY];

    Map(int layer){
        switch (layer){
            case 1:
            rooms[0][0]=new Room(1,0,0);
            rooms[0][0].connectLeft=true;
            break;
        }
    }




}
