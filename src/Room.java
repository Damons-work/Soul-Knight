import javax.swing.*;

public class Room extends JPanel implements Size{
    final int Max_countX=30;
    final int Max_countY=30;
    //区块大小
    final int BlockCountX=30;
    final int BlockCountY=30;
    final int BlockWidth=BlockCountX*Size;
    final int BlockHeight=BlockCountY*Size;
    //房间的大小
    int countX;
    int countY;
    //第几个房间(打算做成Room的二维数组储存一层地牢)
    int roomX;
    int roomY;
    //这个区块的左上角坐标(房间位于区块中央以保证通道的联通)
    int beginX;
    int beginY;
    //房间类型,1为初始房间,2为传送门房间,3为boss房间,4-10预留给商店等非战斗房间,11以后为普通战斗房间
    int flag;

    //房间左上角坐标(相对于区块)
    int roomBeginX;
    int roomBeginY;

    boolean connectUP=false;
    boolean connectDown=false;
    boolean connectLeft=false;
    boolean connectRight=false;



    public Room(int flag,int roomX,int roomY){

        this.setLayout(null);
        this.flag=flag;

        this.roomX=roomX;
        this.roomY=roomY;

        beginX=countX*BlockWidth;
        beginY=countY*BlockHeight;
        switch (flag){
            case 1:
                countX=10;
                countY=10;
                roomBeginX=(BlockWidth-countX*Size)/2;
                roomBeginY=(BlockHeight-countY*Size)/2;

                break;

        }
        buildFloor();
//        buildWall();
        this.setVisible(true);
    }

    public void buildFloor(){
        Floor[][] floors=new Floor[countX][countY];
        for(int x=0;x<countX;x++){
            for(int y=0;y<countY;y++){
                floors[x][y]=new Floor(roomBeginX,roomBeginY,x,y);
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
