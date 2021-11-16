import javax.swing.*;

public class Room extends JPanel {
    int countX;
    int countY;

    boolean connectUP=false;
    boolean connectDown=false;
    boolean connectLeft=false;
    boolean connectRight=false;



    public Room(int countX,int countY){
        this.setLayout(null);
        this.countX=countX;
        this.countY=countY;
        buildFloor();
        buildWall();
        this.setVisible(true);
    }
    public void buildFloor(){
        Floor[][] floors=new Floor[countX][countY];
        for(int x=0;x<countX;x++){
            for(int y=0;y<countY;y++){
                floors[x][y]=new Floor(x,y);
                this.add(floors[x][y]);
            }
        }
    }
    public void buildWall(){
        int wallCountX=countX+2;
        int wallCountY=countY+2;
        Wall[][] walls=new Wall[wallCountX][wallCountY];
        for(int x=0;x<wallCountX;x++){//上下两边墙
            if(x==0 || x==wallCountX-1){
                walls[x][0]=new Wall(x,0,0);
                this.add(walls[x][0]);
            }
            else{
                walls[x][0]=new Wall(x,0,1);
                this.add(walls[x][0]);
            }
            walls[x][wallCountY-1]=new Wall(x,wallCountY-1,1);
            this.add(walls[x][wallCountY-1]);
        }
        for(int y=1;y<wallCountY-1;y++){//左右两边墙
            walls[0][y]=new Wall(0,y,0);
            this.add(walls[0][y]);
            walls[wallCountX-1][y]=new Wall(wallCountX-1,y,0);
            this.add(walls[wallCountX-1][y]);
        }
        if(connectUP==true){
            int connectUPBegin=wallCountX/2-2;
            int connectUpEnd=connectUPBegin+4;
            for(int i=connectUPBegin;i<=connectUpEnd;i++){
                this.remove(walls[i][0]);
            }
        }


    }




}
